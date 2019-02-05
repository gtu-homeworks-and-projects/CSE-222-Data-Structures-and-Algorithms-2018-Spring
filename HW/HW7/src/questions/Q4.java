package questions;

import source.*;

public class Q4 {
    public static void main(String[] args){
        Graph g = new ListGraph(7, false);
        g.insert(new Edge(0,0));
        g.insert(new Edge(0,1));
        g.insert(new Edge(0,5));
        g.insert(new Edge(1,1));
        g.insert(new Edge(1,2));
        g.insert(new Edge(2,2));
        g.insert(new Edge(2,4));
        g.insert(new Edge(2,5));
        g.insert(new Edge(2,6));
        g.insert(new Edge(3,3));
        g.insert(new Edge(3,4));
        g.insert(new Edge(3,5));
        g.insert(new Edge(4,4));
        g.insert(new Edge(4,5));
        g.insert(new Edge(5,5));
        g.insert(new Edge(6,6));

        MyFunctions.plot_graph(g);

        DepthFirstSearchNR DFS = new DepthFirstSearchNR(g);
        DFS.depthFirstSearch(0);
        System.out.print("Depth First Search Traversal Spanning Tree:");
        for(int tmp :
                DFS.getDiscoveryOrder()){
            System.out.printf("[%d] ",tmp);
        }
        System.out.println();

        BreadthFirstSearch BFS = new BreadthFirstSearch(g);
        BFS.breadthFirstSearch(0);
        System.out.print("Breadth First Search Traversal Spanning Tree:");
        for(int tmp :
                BFS.getDiscoveryOrder()){
            System.out.printf("[%d] ",tmp);
        }
    }
}
