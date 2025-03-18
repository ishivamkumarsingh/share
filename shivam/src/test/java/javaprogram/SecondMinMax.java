package javaprogram;

public class SecondMinMax {
    public static void main(String[] args) {
        int[] arr = {21, 4, 67, 41, 9, 57, 88};
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        
        for (int num : arr) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2 && num != min1) {
                min2 = num;
            }
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2 && num != max1) {
                max2 = num;
            }
        }
        
        System.out.println("2nd Min Number: " + min2);
        System.out.println("2nd Max Number: " + max2);
    }
}
