/* Following stpes are done
* List with 10 elements created
* List travel & printing
* List is sorted with selection sort algorithm
* Swapping is done by exchanging node
****************************************************************************************************/
import java.util.Scanner;

//Define a single linked list My_Node
class My_Node{
    protected int value;
    protected My_Node next;
    //Constructor
    public My_Node(){
        this.value = 0;
        this.next = null;
    }
    public My_Node(int val, My_Node ref){
        this.value = val;
        this.next = ref;
    }
}

//Linked list functions
class MyList{
    protected int listSize = 0;
    protected My_Node head = null;

    //Create a list with values in random order & return head pointer
    public My_Node createList(){
        System.out.println("Enter 10 values to be inserted in a list, in random order");
        My_Node temp = null;
        for (int count = 1; count <= 5; count++){
            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();
            My_Node newMy_Node = new My_Node(input, null);
            if (head == null){
                head = newMy_Node;
                temp = head;
            }
            else
            {
                temp.next = newMy_Node;
                temp = newMy_Node;
            }
            listSize++;
        }
        //return head of the list
        return head;
    }

    //Traverse & print the list
    public void printList(My_Node head){
       My_Node temp = head;
        for (int i=1; i<=listSize;i++){
            if (temp != null){
                System.out.printf(" %d ",temp.value);
                if (i != listSize)
                    System.out.printf("-->");
                temp = temp.next;
            }
        }
        System.out.printf("\n");
    }

}
public class SelectionSort {

    /* Just for handson */
    //Selection sort implementation by exchanging values
    public static void sortList(My_Node head){
        while(head!=null){
            My_Node temp = head.next;
            My_Node small = head;
            while(temp!=null){
                if(temp.value < small.value)
                    small = temp;
                temp = temp.next;
            }
            if(small!=head){
                //Swap the values if new small value was found
                int tmp = head.value;
                head.value = small.value;
                small.value = tmp;
            }
            //Traverse the list for next small number
            head = head.next;
        }
    }

    //Selection sort implementation - exchanging Nodes
    public static My_Node sortListBySwappingMy_Nodes(My_Node head){
        My_Node tempHead = head;
        My_Node headPrev = null;
        My_Node smallPrev = null;
        My_Node itrTempHead = head; 
        while(itrTempHead!=null){
            My_Node temp = tempHead.next;
            My_Node small = tempHead;
            System.out.printf("Small is %d\n",small.value);
            while(temp!=null){
                if(temp.value < small.value){
                    smallPrev = small;
                    small = temp;
                    System.out.printf("New Small is %d\n",small.value);
                }
                temp = temp.next;
            }
            if(small!=tempHead){
                System.out.printf("case: small prev is same as temphead & head is changing\n");
                if((smallPrev == tempHead) && (headPrev == null)){
                    itrTempHead = tempHead;
                    head = small;
                    tempHead.next = small.next;
                    small.next = tempHead;
                }
                else if ((smallPrev == tempHead)&&(headPrev != null)){
                    System.out.printf("case: small prev is same as temphead & head is not changing\n");
                    itrTempHead = tempHead;
                    tempHead.next = small.next;
                    small.next = tempHead;
                }
                else
                {
                    System.out.printf("I am hear\n");
                    itrTempHead = tempHead.next;
                    My_Node tmp1 = tempHead.next;
                    if(headPrev!=null)
                        headPrev = small;
                    smallPrev.next = tempHead;
                    small.next = tmp1;
                }
            }
        }
        return head;
    }

    public static void main(String args[]){
        MyList singleLL = new MyList();
        My_Node head = singleLL.createList();
        System.out.printf("Original List before sorting:\n");
        singleLL.printList(head);
        My_Node modifiedHead = sortListBySwappingMy_Nodes(head);
        System.out.printf("Original List after sorting:\n");
        singleLL.printList(modifiedHead);
        //Sorting by swaping values
        //sortList(head);
        //singleLL.printList(head);
    }
}

