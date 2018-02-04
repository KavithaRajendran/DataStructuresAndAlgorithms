/******************************************************************************
 * File Name : HeapSort.java
 * Author    : Kavitha Rajendran
 * Description : This file uses an array of 15 elements as input
 *               heapify() - This function builds max heap from given input 
 *               heapSort() - This function produces sorted array from heap
 * ****************************************************************************/

public class HeapSort<T extends Comparable<T>> {
    
    /*Recursive funtion to build heap */
    public void heapify(T[] inputArray, int n, int root) {
        int largest = root;
        int left = (root*2)+1;
        int right = (root*2)+2;
        //Find max with in -- root,leftchild,rightchild 
        if ((left < n)&&(inputArray[left].compareTo(inputArray[largest])>0))
            largest = left;
        if ((right < n)&&(inputArray[right].compareTo(inputArray[largest])>0))
            largest = right;
        //If child is greater than root- swap
        if(largest != root){
            T temp = inputArray[root];
            inputArray[root]= inputArray[largest];
            inputArray[largest]=temp;
            heapify(inputArray,n,largest);
        }
    }

    /* Produces sorted array from given heap */
    public void heapSort(T[] inputArray) {
        int arrLength = inputArray.length;
        for (int index=arrLength-1; index>0; index--) {
            //swap first & last element
            T temp = inputArray[0];
            inputArray[0] = inputArray[index];
            inputArray[index] = temp;
            //Reheapify after swap
            heapify(inputArray,index,0);
        }
    }
    
    /* Main Function */
    public static void main(String[] args) {

        //An array of 15 numbers which is NOT in heap structure
        Integer[] inputArray = {42,83,21,90,55,93,81,97,37,73,84,80,95,62,17};
        int arrLength = inputArray.length;
        HeapSort<Integer> heap = new HeapSort<>();

        //Printing Array before making it as heap
        System.out.println("Before heapified");
        for(int k=0; k<arrLength; k++){
            System.out.printf(" "+inputArray[k]+" ");
        }

        //Build max heap from given input array
        for (int i = arrLength/2-1; i>=0; i--){
            heap.heapify(inputArray,arrLength,i);
        }
        
        //Printing Array after making it as heap
        System.out.println("\nAfter heapified");
        for(int j=0; j<arrLength; j++){
            System.out.printf(" "+inputArray[j]+" ");
        }

        //Sorting heap
        heap.heapSort(inputArray);

        //Printing sorted array
        System.out.println("\nAfter sorted");
        for(int a=0; a<arrLength; a++){
            System.out.printf(" "+inputArray[a]+" ");
        }
        System.out.println("\n");
    }
}
