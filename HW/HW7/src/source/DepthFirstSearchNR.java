package source;

import java.util.*;

/** Class to implement the depth-first search algorithm.
 *   @author Koffman and Wolfgang
 **/
public class DepthFirstSearchNR {

    // Data Fields
    /** A reference to the graph being searched. */
    private Graph graph;
    /** Array of parents in the depth-first search tree. */
    private int[] parent;
    /** Flag to indicate whether this vertex has been visited. */
    private boolean[] visited;
    /** The array that contains each vertex in discovery order. */
    private int[] discoveryOrder;
    private int discoverIndex = 0;

    // Constructors

    public DepthFirstSearchNR(Graph graph) {
        this.graph = graph;
        initialize();
        for (int i = 0; i < graph.getNumV(); i++) {
            if (!visited[i]) {
                depthFirstSearch(i);
            }
        }
    }

    private void initialize(){
        int n = graph.getNumV();
        parent = new int[n];
        visited = new boolean[n];
        discoveryOrder = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            visited[i] = false;
            discoveryOrder[i] = -1;
        }
        discoverIndex = 0;
    }

    public boolean depthFirstSearch(int current){
        initialize();
        Stack<Integer> parents = new Stack<>();
        parents.push(current);
        int tempCurrent = current;
        boolean isAcyclic = true;
        while(!parents.empty()){
            int postCurrent = tempCurrent;
            tempCurrent = parents.pop();
            if(!visited[tempCurrent]){
                visited[tempCurrent] = true;
                discoveryOrder[discoverIndex++] = tempCurrent;
            }
            Iterator<Edge> itr = graph.edgeIterator(tempCurrent);
            while(itr.hasNext()){
                Edge tmp = itr.next();
                int neighbor = tmp.getDest();
                if(!visited[neighbor]){
                    parent[neighbor] = tempCurrent;
                    parents.push(neighbor);
                }
                else {
                    if(parent[tempCurrent] != neighbor && discoverIndex < graph.getNumV()){
                        isAcyclic = false;
                    }
                }
            }
        }
        return isAcyclic;
    }

    /** Get the discovery  order
     *  @return discovery order
     */
    public int[] getDiscoveryOrder() {
        return discoveryOrder;
    }

    /** Get the parent
     *  @return the parent
     */
    public int[] getParent() {
        return parent;
    }
}
