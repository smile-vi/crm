package com.t248.lhd.crm;

public class BinaryTest
{
    public static int binary(int[] array, int value)
    {
        int low = 0;
        int high = array.length -1;
        while(low <= high)
        {
            int middle = (low + high) / 2;
            if(value == array[middle])
            {
                return middle;
            }
            if(value > array[middle])
            {
                low = middle + 1;
            }
            if(value < array[middle])
            {
                high = middle - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] a = {1,2,3,4,5,6,7,8,9,12,13,45,67,89,99,101,111,123,134,565,677};
        int value = binary(a, 45);
        System.out.println(a[value]);
        System.out.println(value);
    }


}