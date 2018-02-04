/**********************************************************************************
 * Bubble sort:
 *  This programs sort the given input 
 *  Output is printed for every pass
 **********************************************************************************/
public class BubbleSort{
    public static void bSort(int[] inputArray){
        int i;
        int j;
        int swap;
        for(i=0;i<inputArray.length;i++){
            swap = 0;
            for(j=0;j<inputArray.length-1;j++){
                if(inputArray[j]>inputArray[j+1]){
                    //Swap
                    int tmp = inputArray[j];
                    inputArray[j] = inputArray[j+1];
                    inputArray[j+1] = tmp;
                    swap = 1;
                }
            }
            System.out.printf("\n Pass %d completed: ",i+1);
            int k;
            for(k=0;k<inputArray.length;k++){
                System.out.printf(" %d ",inputArray[k]);
            }
            if(swap == 0){
                //There was no swapping done which means array is sorted
                System.out.printf("\n");
                System.out.printf("\n Output - Sorted Array:\n");
                for(k=0;k<inputArray.length;k++){
                    System.out.printf(" %d ",inputArray[k]);
                }
                break;
            }
        }
    }
    public static void main(String args[]){
        int[] inputArr = {35,98,143,45,121,67,24,88,264,12,76,54,43,101,199};
        System.out.printf("Unsorted array input:\n");
        int k;
        for(k=0;k<inputArr.length;k++){
            System.out.printf(" %d ",inputArr[k]);
        }
        System.out.printf("\n");
        bSort(inputArr);
    }
}
