package questions;

import source.*;

import java.util.Random;
import java.util.Vector;

import static source.MyFunctions.shortest_path_test;

public class Q2 {
    public static void main(String[] args){
        Graph g = new ListGraph(15,false);
        g.insert(new Edge(0,1));
        g.insert(new Edge(0,5));
        g.insert(new Edge(0,10));
        g.insert(new Edge(0,14));
        g.insert(new Edge(1,2));
        g.insert(new Edge(1,4));
        g.insert(new Edge(2,3));
        g.insert(new Edge(5,6));
        g.insert(new Edge(5,8));
        g.insert(new Edge(6,7));
        g.insert(new Edge(8,9));
        g.insert(new Edge(13,10));
        g.insert(new Edge(10,11));
        g.insert(new Edge(13,12));

        /* Plot Graph */
        MyFunctions.plot_graph(g);

        /* Undirected - Directed check */
        System.out.println("Graph is" + (MyFunctions.is_undirected(g) ? " un" : " ") + "directed");

        /* Cyclic - Acyclic check */
        System.out.print("Graph is ");
        System.out.println(MyFunctions.is_acyclic(g) ? "acyclic" : "cyclic");

        /* Paths */
        shortest_path_test(g);
    }
}
