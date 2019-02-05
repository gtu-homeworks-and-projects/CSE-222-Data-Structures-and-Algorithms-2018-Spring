// Insert solution to programming exercise 1, section 2, chapter 10 here
package source;

public class Edge implements Comparable<Edge> {
    private int source;
    private int dest;
    private double weight;

    public Edge(int source, int dest){
        this(source,dest,1);
    }

    public Edge(int source, int dest, double weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public int getSource(){
        return source;
    }

    public int getDest(){
        return dest;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Edge o){
        return Double.compare(this.getWeight(), o.getWeight());
    }
}


