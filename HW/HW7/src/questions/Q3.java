package questions;

import source.*;

public class Q3 {
    public static void main(String[] args){
        Graph g = new ListGraph(10,false);
        g.insert(new Edge(0,1));
        g.insert(new Edge(0,2));
        g.insert(new Edge(0,3));
        g.insert(new Edge(0,5));
        g.insert(new Edge(1,9));
        g.insert(new Edge(1,3));
        g.insert(new Edge(1,4));
        g.insert(new Edge(2,7));
        g.insert(new Edge(5,6));
        g.insert(new Edge(5,8));
        g.insert(new Edge(6,7));
        g.insert(new Edge(7,9));
        g.insert(new Edge(8,9));
        g.insert(new Edge(9,0));

        /* Plot Graph */
        MyFunctions.plot_graph(g);

        /* Undirected - Directed check */
        System.out.println("Graph is" + (MyFunctions.is_undirected(g) ? " un" : " ") + "directed");

        /* Cyclic - Acyclic check */
        System.out.print("Graph is ");
        System.out.println(MyFunctions.is_acyclic(g) ? "acyclic" : "cyclic");

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
        System.out.println();
    }
}
