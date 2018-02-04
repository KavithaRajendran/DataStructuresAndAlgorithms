/*************************************************************
* Recursive Binary Search implementation
* Author:
*      Kavitha Rajendran
*************************************************************/
public class BinarySearch{
    //Recursive Function to do Binary Search
    public static int bSearch(int[] input_array, int searchKey, int low, int high) {
        while(low <= high) {//Condition to comeout of recursion
            System.out.printf("Low: %d \n",low);
            System.out.printf("High: %d \n",high);
            int mid;
            mid = (low+high)/2;
            System.out.printf("Mid: %d \n",mid);
            //Base case
            if (input_array[mid] == searchKey)
                return mid; 
            else {
                //Recusrive call
                if(input_array[mid] > searchKey)
                    return bSearch(input_array,searchKey,low,mid-1);
                else
                    return bSearch(input_array,searchKey,mid+1,high);
            }
        }
        //Return -1 when key not found
        return -1;
    }
    public static void main(String[] args){
        //Binary Search in an array with odd number of elements
        int[] oddArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int startingIndex = 0;
        int endingIndex = oddArray.length-1;
        //Sunny day scenaio: Find a number in array - which is present in array
        //Number to be searched is 1
        
        int resIndex = bSearch(oddArray,7,startingIndex,endingIndex);
        if (resIndex!= -1)
            System.out.printf("Number found in array at index %d \n",resIndex);
        else
            System.out.println("Number not found in array");
        //Rainy day scenario: Number not found in an array with odd number of elements
        resIndex = bSearch(oddArray,16,startingIndex,endingIndex);
        if (resIndex!= -1)
            System.out.printf("Number found in array at index %d \n",resIndex);
        else
            System.out.println("Number not found in array");
        
        int[] evenArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        endingIndex = evenArray.length-1;
        //Sunny day scenaio: Find a number in array - which is present in array
        //Number to be searched is 1
        resIndex = bSearch(evenArray,8,startingIndex,endingIndex);
        if (resIndex!= -1)
            System.out.printf("Number found in array at index %d \n",resIndex);
        else
            System.out.println("Number not found in array");
        //Rainy day scenario: Number not found in an array with even number of elements
        resIndex = bSearch(evenArray,17,startingIndex,endingIndex);
        if (resIndex!= -1)
            System.out.printf("Number found in array at index %d \n",resIndex);
        else
            System.out.println("Number not found in array");

    }
}      
