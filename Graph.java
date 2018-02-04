/*
* Description: Implementation of DFS & BFS (Graph traversal algorithms)
*              Network is represented using Incidence matrix
* Author:
*      Kavitha Rajendran
*/

import java.util.*;

public class Graph {
    int numberOfNodes;
    int numberOfEdges;
    String[] color = new String[10];
    Integer[] visited = new Integer[10];
    LinkedList<Integer> adjacentList[];
    
    //Incidence matrix with 10 nodes & 15 edges - undirected connected graph
    int[][] incidenceMatrix =  { {1,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
                                 {0,0,0,1,1,0,1,0,0,0,0,0,0,0,0},
                                 {0,1,1,0,0,0,0,1,0,0,0,0,0,0,0},
                                 {1,0,0,0,1,0,0,0,1,0,0,0,0,0,0},
                                 {0,0,1,1,0,0,0,0,0,1,0,0,0,0,0},
                                 {0,0,0,0,0,1,0,0,0,0,1,0,0,0,1},
                                 {0,0,0,0,0,0,1,0,0,0,1,1,0,0,0},
                                 {0,0,0,0,0,0,0,1,0,0,0,1,1,0,0},
                                 {0,0,0,0,0,0,0,0,1,0,0,0,1,1,0},
                                 {0,0,0,0,0,0,0,0,0,1,0,0,0,1,1},
                               };

    //Incidence matrix with 10 nodes & 15 edges - directed connected graph
    int[][] incidenceMatrix1 = { {-1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 { 0, 0, 0,-1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                 { 0,-1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                 { 1, 0, 0, 0, -1,0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                 { 0, 0,-1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                 { 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 1, 0, 0, 0,-1},
                                 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 1, 0, 0, 0},
                                 { 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 1, 0, 0},
                                 { 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 1, 0},
                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 1},
                               };

    //Incidence matrix with 10 nodes & 15 edges - undirected unconnected graph
    int[][] incidenceMatrix2 = {
            {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
            {1,1,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {0,1,1,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,1,1,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {0,0,0,0,0,0,0,0,0,0,0,1,0,1,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
            
    };

    // Constructor
    Graph(int v,int e)
    {
        numberOfNodes = v;
        numberOfEdges = e;
        adjacentList = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adjacentList[i] = new LinkedList();
        //Initialize color array as white
        for(int i=0;i<v; i++) 
            color[i]="white";
        //Initialize visited as zero for all nodes
        for(int i=0;i<v; i++) 
            visited[i]=0;
    }
     
    void findNeighbours_undirected(int[][] incidenceMatrix) {
        for(int i=0;i<numberOfNodes;i++) {
            //System.out.println(adjacentList[i]);
            for(int j=0;j<numberOfEdges;j++) {
                if(incidenceMatrix[i][j]==1){
                    int temp=0;
                    while(temp<numberOfNodes) {
                        if(incidenceMatrix[temp][j]==1 && temp!=i){
                            //System.out.println("Adding "+temp+" to "+i);
                            adjacentList[i].add(temp);
                            break; 
                        }
                        temp++;
                    }
                }
            }
            System.out.println("Adjacency List of Node "+i+" "+adjacentList[i]);
        }
    }

    void findNeighbours_directed(int[][] incidenceMatrix) {
        for(int i=0;i<numberOfNodes;i++) {
            for(int j=0;j<numberOfEdges;j++) {
                if(incidenceMatrix[i][j]==1){
                    int temp=0;
                    while(temp<numberOfNodes) {
                        if(incidenceMatrix[temp][j]==-1 && temp!=i){
                            //System.out.println("Adding "+temp+" to "+i);
                            adjacentList[i].add(temp);
                            break; 
                        }
                        temp++;
                    }
                }
            }
            System.out.println("Adjacency List of Node "+i+" "+adjacentList[i]);
        }
    }
    void BFS(int startingNode) {
        LinkedList<Integer> bfsQ = new LinkedList<Integer>();
        visited[startingNode] = 1;
        bfsQ.add(startingNode);
        while(bfsQ.size()!=0) {
            int bfs = bfsQ.remove();
            System.out.printf(bfs+" ");  
            Iterator<Integer> listTraversal = adjacentList[bfs].listIterator();
            while(listTraversal.hasNext()) {
                int nextNode = listTraversal.next();
                //System.out.println("nextNode "+nextNode);
                if ( visited[nextNode]==0) {
                    visited[nextNode]=1;
                    bfsQ.add(nextNode);
                }
            }
        }
        //Handling Unconnected Graph
        System.out.println("\n");
        int a;
        for(a=0;a<10;a++) {
           if(visited[a]!=1) {
            break; 
            }
        }
        if(a!=10) {
            BFS(a);      
        } 
    }

    void DFSrec(int vertex) {
        System.out.printf(vertex+" ");
        color[vertex] = "grey";
        Iterator<Integer> listTraversal = adjacentList[vertex].listIterator();
        while(listTraversal.hasNext()) {
            int nextNode = listTraversal.next();
            if ( color[nextNode]=="white") {
                DFSrec(nextNode);
            }
        }
        //Handling unconnected graph
        int a;
        for(a=0;a<10;a++) {
           if(color[a]=="white") {
            break;
            }
        }
        if(a!=10) {
            System.out.println("\n");
            DFSrec(a);
        }
    }

    public static void main(String args[]) {
        
        System.out.println("Connected Undirected Graph:");
        Graph g = new Graph(10,15);
        g.findNeighbours_undirected(g.incidenceMatrix);
        System.out.println("\n");
        System.out.println("BFS Tree:");
        g.BFS(1);
        System.out.println("\n");
        System.out.println("DFS Tree:");
        g.DFSrec(1);
        System.out.println("\n");

        System.out.println("Directed Graph:");
        Graph g1 = new Graph(10,15);
        g1.findNeighbours_directed(g1.incidenceMatrix1);
        System.out.println("\n");
        System.out.println("BFS Tree:");
        g1.BFS(1);
        System.out.println("\n");
        System.out.println("DFS Tree:");
        g1.DFSrec(1);
        System.out.println("\n");
        
        System.out.println("UnConnected Undirected Graph:");
        Graph g2 = new Graph(10,15);
        g2.findNeighbours_undirected(g2.incidenceMatrix2);
        System.out.println("\n");
        System.out.println("BFS Tree:");
        g2.BFS(1);
        System.out.println("\n");
        System.out.println("DFS Tree:");
        g2.DFSrec(1);
    }
}
