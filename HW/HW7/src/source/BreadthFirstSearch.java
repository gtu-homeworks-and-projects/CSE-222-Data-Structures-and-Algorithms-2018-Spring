package source;

import java.util.*;

/** Class to implement the breadth-first search algorithm.
 *  @author Koffman and Wolfgang
 * */

public class BreadthFirstSearch {
    private Graph g;
    private int[] discoveryOrder;
    private int discoveryIndex;

    public BreadthFirstSearch(Graph graph){
        g = graph;
        initialize();
        for(int i = 0; i < g.getNumV(); i++) breadthFirstSearch(i);

    }

    private void initialize(){
        int n = g.getNumV();
        discoveryOrder = new int[n];
        for (int i = 0; i < n; i++) discoveryOrder[i] = -1;
        discoveryIndex = 0;
    }

    /** Perform a breadth-first search of a graph.
     post: The array parent will contain the predecessor
     of each vertex in the breadth-first
     search tree.
     @param start The start vertex
     @return The array of parents
     */
    public int[] breadthFirstSearch(int start) {
        Queue < Integer > theQueue = new LinkedList < Integer > ();
        initialize();
        // Declare array parent and initialize its elements to –1.
        int[] parent = new int[g.getNumV()];
        for (int i = 0; i < g.getNumV(); i++) {
            parent[i] = -1;
        }
        // Declare array identified and
        // initialize its elements to false.
        boolean[] identified = new boolean[g.getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
        identified[start] = true;
        theQueue.offer(start);
        /* While the queue is not empty */
        while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
            int current = theQueue.remove();
            discoveryOrder[discoveryIndex++] = current;
            /* Examine each vertex, neighbor, adjacent to current. */
            Iterator < Edge > itr = g.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                // If neighbor has not been identified
                if (!identified[neighbor]) {
                    // Mark it identified.
                    identified[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor)
             into the tree. */
                    parent[neighbor] = current;
                }
            }
            // Finished visiting current.
        }
        return parent;
    }

    public int[] getDiscoveryOrder(){ return discoveryOrder; }

}
