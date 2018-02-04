/*
* Description: Implementation of Prims Algorithm
* Author     : Kavitha Rajendran
*
*/
import java.util.*;

public class Prims {
    //Initialization
    int numberOfNodes;
    int numberOfEdges;
    Boolean[] isInMst  = new Boolean[10];
    Integer[] parent = new Integer[10];
    Integer[] edgeWeight = new Integer[10];

    // Constructor
    Prims(int vertices,int edges)
    {
        numberOfNodes = vertices;
        numberOfEdges = edges;
        for(int i=0;i<numberOfNodes; i++) { 
            isInMst[i]=false;
            edgeWeight[i]=Integer.MAX_VALUE;
        }
    }
    //Find Minimum weight outgoing edge & returns the vertex which is not in MST
    public int findMinWeightEdge() {
        int minEdge = Integer.MAX_VALUE;
        int minEdgeVertex = -1;
        for(int j=0;j<numberOfNodes;j++) {
            if(isInMst[j]==false && edgeWeight[j]<minEdge) {
                minEdge=edgeWeight[j];
                minEdgeVertex = j;
            }
        }
        return minEdgeVertex;
    } 
         
    //Update edge weights of adjacent nodes of node-i after adding node-i in MST  
    public void updateEdgeWeight(int v,int[][] incidenceMatrix) {
        for(int k=0; k<numberOfNodes; k++) {
            if(incidenceMatrix[v][k]!=0 && isInMst[k]==false && edgeWeight[k]>incidenceMatrix[v][k]) {
                edgeWeight[k]=incidenceMatrix[v][k];
                parent[k]=v;
            }
        }
    }

    //Print input
    public void printInputGraph(int[][] incidenceMatrix) {
        System.out.println("Input:");
        System.out.println("From    To  EdgeWeight");
        for(int a=0; a<numberOfNodes; a++) {
            for(int b=0; b<numberOfNodes; b++){
                if(incidenceMatrix[a][b]!=0){
                    System.out.println(a+"      "+b+"       "+incidenceMatrix[a][b]);
                }
            }
        }
    }
 
    //Print MST
    public void primsOutputMST(int[][] incidenceMatrix) {
        System.out.println("Output MST:");
        System.out.println("Parent  Child   EdgeWeight");
        System.out.println("-1      0      0");
        for(int k=1; k<numberOfNodes; k++) {
            int p = parent[k];
            System.out.println(parent[k]+"      "+k+"       "+incidenceMatrix[p][k]);
        }
    }
    //Prim Algo
    public void primsMST(int startingNode,int[][] incidenceMatrix) {
        int noOfNodesInMST=0;
        edgeWeight[startingNode]=0;
        parent[startingNode]=-1;
        while(noOfNodesInMST<numberOfNodes) {
            //Finding Minimum Edge
            int nextEdge = findMinWeightEdge();
            //Adding Minimum edge Vertex to MST
            isInMst[nextEdge]=true;
            noOfNodesInMST++;
            //Updating edge weight based on new vertex added in MST
            updateEdgeWeight(nextEdge,incidenceMatrix);
        }
        primsOutputMST(incidenceMatrix);
    }     
    
    public static void main(String args[]) {
        //Edge weight matrix for 10 nodes with 15 edges
        //If there is no edge between two vertex - weight is 0 

        //Undirected graph
        int[][] weightMatrix1 = { {0,1,0,0,7,9,0,0,0,0},
                                  {1,0,4,0,0,0,2,0,0,0},
                                  {0,4,0,3,0,0,0,2,0,0},
                                  {0,0,3,0,6,0,0,0,10,0},
                                  {7,0,0,6,0,0,0,0,0,1},
                                  {9,0,0,0,0,0,0,1,3,0},
                                  {0,2,0,0,0,0,0,0,4,11},
                                  {0,0,2,0,0,1,0,0,0,10},
                                  {0,0,0,10,0,3,4,0,0,0},
                                  {0,0,0,0,1,0,11,10,0,0}
                                }; 
        //Directed graph
        int[][] weightMatrix2 = { {0,1,0,0,0,0,0,0,0,0},
                                  {0,0,4,0,0,0,2,0,0,0},
                                  {0,0,0,3,0,0,0,2,0,0},
                                  {0,0,0,0,6,0,0,0,10,0},
                                  {7,0,0,0,0,0,0,0,0,1},
                                  {9,0,0,0,0,0,0,0,3,0},
                                  {0,0,0,0,0,0,0,0,0,11},
                                  {0,0,0,0,0,1,0,0,0,0},
                                  {0,0,0,0,0,0,4,0,0,0},
                                  {0,0,0,0,0,0,0,10,0,0}
                                };
 
        System.out.println("Undirected Connected Graph:");        
        Prims p1 = new Prims(10,15);
        p1.printInputGraph(weightMatrix1);
        System.out.println("\n");
        p1.primsMST(0,weightMatrix1);
        System.out.println("\n");

        System.out.println("Directed Connected Graph:");        
        Prims p2 = new Prims(10,15);
        p2.printInputGraph(weightMatrix2);
        System.out.println("\n");
        p2.primsMST(0,weightMatrix2);
        System.out.println("\n");
    }
}
