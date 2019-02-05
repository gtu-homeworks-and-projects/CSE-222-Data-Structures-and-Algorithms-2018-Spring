package gtu;

public interface SearchTree<E> {

    /**
     * Adds item to tree
     * @param item item to be inserted
     * @return success condition
     */
    boolean add(E item);

    /**
     * Checks whether target data in tree or not
     * @param target data to be checked
     * @return check conditions
     */
    boolean contains(E target);

    /**
     * Finds given data in tree
     * @param target data to be searched
     * @return Found data or null if not found
     */
    E find(E target);

    /**
     * Removes given item from tree
     * @param target data to be removed
     * @return removed data or null if not found
     */
    E delete(E target);

    /**
     * Removes given item from tree
     * @param target data to be removed
     * @return success condition
     */
    boolean remove(E target);
}
