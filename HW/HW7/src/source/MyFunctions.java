package source;

import java.util.*;

/**
 * Class for keeping requested functions over graphs.
 */
public class MyFunctions {

    /**
     * Finds dijkstras algorithm finds all possible paths from source vertex.
     * @param g Graph to operate on
     * @param start start vertex
     * @param parents output array of parents
     * @return distance array
     */
    private static double[] dijkstraAlgorithm(Graph g, int start, Vector<Integer> parents){
        int numV = g.getNumV();
        double[] distances = new double[numV];
        int[] parent = new int[numV];
        Vector<Integer> remainingVertices = new Vector<>();
        for(int i = 0; i < numV; i++){
            distances[i] = Double.POSITIVE_INFINITY;
            remainingVertices.add(i);
            parent[i] = -1;
        }
        
        distances[start] = 0;
        while(!remainingVertices.isEmpty()){
            int v = remainingVertices.firstElement();
            for(Integer tmp :
                    remainingVertices){
                if(distances[tmp] < distances[v]) v = tmp;
            }
            remainingVertices.remove((Integer) v);

            Iterator<Edge> itr = g.edgeIterator(v);
            while(itr.hasNext()){
                Edge e = itr.next();
                double distance = distances[v] + e.getWeight();

                if(distance < distances[e.getDest()]) {
                    distances[e.getDest()] = distance;
                    parent[e.getDest()] = v;
                }
            }
        }

        for(Integer a :
                parent){
            parents.add(a);
        }
        return distances;
    }

    /**
     * Finds shortest path between v1 and v2
     * @param g which graph to be searched
     * @param v1 start vertex
     * @param v2 end vertex
     * @param path vector of path
     * @return total distance of path
     */
    public static double shortest_path(Graph g, int v1, int v2, Vector<Integer> path){
        Vector<Integer> parents = new Vector<>();
        Stack<Integer> reversePath = new Stack<>();
        double[] distances = dijkstraAlgorithm(g, v1, parents);
        if (distances[v2] == Double.POSITIVE_INFINITY) return distances[v2];
        int index = v2;
        reversePath.add(v2);
        while(parents.get(index) != -1){
            index = parents.get(index);
            reversePath.add(index);
        }
        while(!reversePath.empty()) path.add(reversePath.pop());
        return distances[v2];
    }

    /**
     * Checks if there is a path between v1 and v2.
     * @param g Graph to operate on.
     * @param v1 Vertex 1
     * @param v2 Vertex 2
     * @return Connected or not
     */
    public static boolean is_connected(Graph g, int v1, int v2){
        int numV = g.getNumV();
        if(v1 >= numV || v2 >= numV) throw new Error("Label(s) are incorrect");
        Vector<Integer> myVec = new Vector<>();
        return shortest_path(g, v1, v2, myVec) < Double.POSITIVE_INFINITY;
    }

    /**
     * Checks if a given graph is undirected or directed
     * @param g Graph to operate on
     * @return TRUE if undirected
     */
    public static boolean is_undirected(Graph g){
        int numV = g.getNumV();
        boolean hasComplement;
        for(int i = 0; i < numV; i++){
            Iterator<Edge> itr = g.edgeIterator(i);
            while(itr.hasNext()){
                Edge temp = itr.next();
                Iterator<Edge> iter = g.edgeIterator(temp.getDest());
                hasComplement = false;
                while(iter.hasNext() && !hasComplement){
                    Edge tmp = iter.next();
                    if(temp.getSource() == tmp.getDest() && temp.getWeight() == tmp.getWeight()) hasComplement = true;
                }
                if(!hasComplement) return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given graph is acyclic or not
     * @param g Graph to operate on
     * @return TRUE if graph is acyclic
     */
    public static boolean is_acyclic(Graph g){
        int numV = g.getNumV();
        int isAcyclic = 0;
        DepthFirstSearchNR search = new DepthFirstSearchNR(g);
        for(int i = 0; i < numV; i++) if(search.depthFirstSearch(i)) isAcyclic++;

        return numV == isAcyclic;
    }

    /**
     * Prints given graphs edges in order.
     * @param g Graph to print
     */
    public static void plot_graph(Graph g){
        int numV = g.getNumV();
        boolean isDirected = !is_undirected(g);
        System.out.println("Graph:");
        for(int i = 0; i < numV; i++){
            Iterator<Edge> iterator = g.edgeIterator(i);
            while(iterator.hasNext()){
                Edge tmp = iterator.next();
                System.out.printf("Source [%d]:",tmp.getSource());
                for(int j = 0; j < tmp.getWeight(); j++) System.out.print("-");
                System.out.printf(isDirected? "-> %.0f ->" : "- %.0f -", tmp.getWeight());
                for(int j = 0; j < tmp.getWeight(); j++) System.out.print("-");
                System.out.printf(":Destination [%d]", tmp.getDest());
                System.out.println();
                if(!iterator.hasNext()) System.out.println();
            }

        }
    }

    /**
     * Test function for main calls. Tests 3 different paths.
     * @param g Graph to operate on.
     */
    public static void shortest_path_test(Graph g){
        Random rndm = new Random();
        for(int i = 0; i < 3; i++){
            int start = rndm.nextInt(10), end = rndm.nextInt(10);
            Vector<Integer> p = new Vector<>();
            double d = shortest_path(g, start, end, p);
            System.out.println("Shortest path of " + start + " to " + end + ":");
            System.out.println("    Path weight is " + d + " units");
            System.out.print("    Path: ");
            for(int j = 0; j < p.size(); j++){
                System.out.printf("[%d] ", p.get(j));
                if(j != p.size() - 1) System.out.print("-> ");
            }
            System.out.println();
        }
    }
}
