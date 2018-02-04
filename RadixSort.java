/*
 * Description:
 *      Java program to implement Radix sort using LSD
 * Author:
 *      Kavitha Rajendran - kxr161830
 */
import java.util.*;
public class RadixSort{
    
    public static void radixSort(int input[]){
        //There are radix number of buckets
        int radix=10;
        List<Integer>[] bucket = new ArrayList[radix];

        //Every bucket is itself a list
        for (int i = 0; i < bucket.length; i++)
            bucket[i] = new ArrayList<Integer>();

        //Find maximum number in array 
        int max = input[0];
        for(int i =1;i<20;i++){
            if(input[i]>max)
                max = input[i];
        }

        //Number of passes = number of digit in largest number 
        int base = 1;
        int count=1;
        while(max/base >0) {
            //add the input numbers to respective buckets, based on LSD
            System.out.println("Pass No:"+count);
            for(int num : input){
                int index = (num/base)%10;
                bucket[index].add(num);
            }
            //Update input array in same order as bucket lists 
            int inputIndex = 0;
            for(int buckIndex =0 ; buckIndex<10; buckIndex++){
                for(int no: bucket[buckIndex])
                    input[inputIndex++]=no;
                //flush the buckets after every pass
                bucket[buckIndex].clear();
            }
            //Printing input array after every pass
            System.out.println("Input array after pass:"+count);
            for(int i =0; i <20; i++)
                System.out.printf("%d ",input[i]); 
 
            System.out.println("\n");
            base = base * 10;
            count++; 
        }
    }
    //Main Function
    public static void main(String[] args){
        //input of 20 integers
        int[] input = new int[]{22233,2059,56,322,768,4,5431,660,8765,537109,7,97562,98,14,6,110,985271,1113,1015,677};

        System.out.println("Unsorted input of 20 integers:");
        // print unsorted input
        for(int i =0; i <20; i++)
            System.out.printf("%d ",input[i]);
        System.out.println("\n");
        radixSort(input);
    
        System.out.println("Final Sorted array:");
        // print sorted output
        for(int i =0; i <20; i++)
            System.out.printf("%d ",input[i]);
        System.out.println("\n");
    }
}
