package com.github.dsa.non.linear.graph;

import java.util.*;

public class DAGExpressionEvaluator {
    // Node class for the DAG
    static class Node {
        String value; // Either a number or operator
        double result; // Cached result after evaluation
        boolean evaluated; // Flag to track if computed
        List<Node> children; // Operands for operators

        Node(String value) {
            this.value = value;
            this.children = new ArrayList<>();
            this.evaluated = false;
        }

        void addChild(Node child) {
            children.add(child);
        }
    }

    // Build DAG from infix expression
    public static Node buildDAG(String expression) {
        Stack<Node> stack = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String cleanedExpr = expression.replaceAll("\\s+", "");
        Map<String, Node> subExprCache = new HashMap<>(); // For common sub-expression elimination

        int i = 0;
        while (i < cleanedExpr.length()) {
            char c = cleanedExpr.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < cleanedExpr.length() &&
                        (Character.isDigit(cleanedExpr.charAt(i)) || cleanedExpr.charAt(i) == '.')) {
                    num.append(cleanedExpr.charAt(i++));
                }
                Node numNode = new Node(num.toString());
                stack.push(numNode);
                continue;
            }
            else if (c == '(') {
                operators.push(c);
            }
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    processOperator(stack, operators.pop(), subExprCache);
                }
                operators.pop(); // Remove '('
            }
            else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    processOperator(stack, operators.pop(), subExprCache);
                }
                operators.push(c);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            processOperator(stack, operators.pop(), subExprCache);
        }

        return stack.pop();
    }

    private static void processOperator(Stack<Node> stack, char operator, Map<String, Node> cache) {
        Node opNode;
        String opStr = String.valueOf(operator);

        Node b = stack.pop();
        Node a = stack.pop();
        String exprKey = a.value + opStr + b.value;

        // Check if this sub-expression already exists
        if (cache.containsKey(exprKey)) {
            opNode = cache.get(exprKey);
        } else {
            opNode = new Node(opStr);
            opNode.addChild(a);
            opNode.addChild(b);
            cache.put(exprKey, opNode);
        }
        stack.push(opNode);
    }

    // Evaluate the DAG
    public static double evaluateDAG(Node root) {
        if (root.evaluated) {
            return root.result;
        }

        if (root.children.isEmpty()) { // Leaf node (number)
            root.result = Double.parseDouble(root.value);
            root.evaluated = true;
            return root.result;
        }

        // Operator node
        double a = evaluateDAG(root.children.get(0));
        double b = evaluateDAG(root.children.get(1));

        root.result = applyOperator(a, b, root.value);
        root.evaluated = true;
        return root.result;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            default: return 0;
        }
    }

    private static double applyOperator(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    // Utility to print DAG (for visualization)
    public static void printDAG(Node node, String indent, Set<Node> visited) {
        if (node == null || visited.contains(node)) {
            System.out.println(indent + "CYCLE/REUSED NODE");
            return;
        }
        visited.add(node);
        System.out.println(indent + node.value);
        for (Node child : node.children) {
            printDAG(child, indent + "  ", visited);
        }
    }

    public static void main(String[] args) {
        String[] expressions = {
                "5 + 3 * 2",              // 11
                "(4 * 5) + (4 * 5)",     // 40, with reused sub-expression
                "10 / (2 + 3) * 2"       // 4
        };

        for (String expr : expressions) {
            try {
                System.out.println("\nExpression: " + expr);
                Node root = buildDAG(expr);
                System.out.println("DAG structure:");
                printDAG(root, "", new HashSet<>());
                double result = evaluateDAG(root);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error evaluating " + expr + ": " + e.getMessage());
            }
        }
    }
}

/*
How It Works
DAG Construction:
Uses two stacks: one for nodes (operands) and one for operators

Builds the DAG bottom-up while respecting operator precedence

Caches sub-expressions to avoid duplication (e.g., "4 * 5" appears once)

Evaluation:
Recursively evaluates the DAG from leaves to root

Caches results in nodes to prevent recomputation

Time complexity: O(n) where n is the number of unique nodes

Advantages of DAG:
Common Sub-expression Elimination: Reuses nodes for repeated expressions (e.g., "4 * 5")

Memory Efficiency: Shared nodes reduce space usage

Single Pass Evaluation: Each node is evaluated exactly once

Key Features:
Handles operator precedence and parentheses

Supports multi-digit numbers

Detects and reuses identical sub-expressions

Visualizable structure via printDAG

When to Use DAG
When expressions have repeated sub-expressions (e.g., "(x + y) * (x + y)")

For optimization in compilers or mathematical software

When you need to analyze or modify the expression structure

Limitations
More complex than stack-based evaluation

Higher initial memory overhead for simple expressions

Less intuitive for linear processing

This DAG approach shines in scenarios where expressions are complex or reused multiple times, unlike the stack-based approach which is better for one-time linear evaluation. You can extend this by adding more operators or supporting variables by mapping them to values before evaluation.

*/
