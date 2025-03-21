package com.github.dsa;


public class Solution {

    public static void main(String[] args) {
        try{
            int x = 1534236469;
            System.out.println(reverse(x));
        }catch(Exception e){
            e.printStackTrace();
        }
    }


        public static int reverse(int x) {
            int reverse = 0;
            boolean isNegative = x < 0;

            if (x < 0 && x <= Integer.MIN_VALUE) {
                return 0;
            } else if (x > 0 && x > Integer.MAX_VALUE -x) {
                return 0;
            }

            // Copy of the original number so that the original
            // number remains unchanged while finding the reverse
            int temp = Math.abs(x);
            while (temp != 0) {
                reverse = (reverse * 10) + (temp % 10);
                temp = temp / 10;
            }

            return isNegative ? -reverse : reverse;

        }

}
