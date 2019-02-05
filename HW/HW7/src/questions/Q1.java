package questions;

import source.*;

import java.util.Random;

import static source.MyFunctions.shortest_path_test;

public class Q1 {
    public static void main(String[] args){
        Graph g = new ListGraph(10,true);
        Random rndm = new Random();
        g.insert(new Edge(0,1,rndm.nextInt(20) + 1));
        g.insert(new Edge(0,2,rndm.nextInt(20) + 1));
        g.insert(new Edge(0,3,rndm.nextInt(20) + 1));
        g.insert(new Edge(0,5,rndm.nextInt(20) + 1));
        g.insert(new Edge(1,2,rndm.nextInt(20) + 1));
        g.insert(new Edge(1,3,rndm.nextInt(20) + 1));
        g.insert(new Edge(1,4,rndm.nextInt(20) + 1));
        g.insert(new Edge(2,5,rndm.nextInt(20) + 1));
        g.insert(new Edge(2,7,rndm.nextInt(20) + 1));
        g.insert(new Edge(3,5,rndm.nextInt(20) + 1));
        g.insert(new Edge(3,6,rndm.nextInt(20) + 1));
        g.insert(new Edge(3,9,rndm.nextInt(20) + 1));
        g.insert(new Edge(4,6,rndm.nextInt(20) + 1));
        g.insert(new Edge(4,9,rndm.nextInt(20) + 1));
        g.insert(new Edge(5,6,rndm.nextInt(20) + 1));
        g.insert(new Edge(6,7,rndm.nextInt(20) + 1));
        g.insert(new Edge(7,9,rndm.nextInt(20) + 1));
        g.insert(new Edge(7,9,rndm.nextInt(20) + 1));
        g.insert(new Edge(8,1,rndm.nextInt(20) + 1));
        g.insert(new Edge(8,5,rndm.nextInt(20) + 1));
        g.insert(new Edge(8,9,rndm.nextInt(20) + 1));

        /* Plot Graph */
        MyFunctions.plot_graph(g);

        /* Undirected - Directed check */
        System.out.println("Graph is" + (MyFunctions.is_undirected(g) ? " un" : " ") + "directed");

        /* Cyclic - Acyclic check */
        System.out.print("Graph is ");
        System.out.println(MyFunctions.is_acyclic(g) ? "acyclic" : "cyclic");

        /* Path */
        shortest_path_test(g);
    }
}
