package gtu;

import java.util.Arrays;
import java.util.Objects;

public class MultiNode<E extends Comparable<E>> {
    public int dimension;
    public E[] dataPoints;

    public MultiNode(E dataPoints[]){
        dimension = dataPoints.length;
        this.dataPoints = dataPoints;
    }

    @Override
    public String toString() {
        return Arrays.toString(dataPoints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultiNode)) return false;
        MultiNode<?> multiNode = (MultiNode<?>) o;
        return dimension == multiNode.dimension &&
                Arrays.equals(dataPoints, multiNode.dataPoints);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(dimension);
        result = 31 * result + Arrays.hashCode(dataPoints);
        return result;
    }
}
