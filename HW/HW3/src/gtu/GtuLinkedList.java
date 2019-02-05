package gtu;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A custom linked list extension from java.util.LinkedList class
 * @param <E> Type parameter.
 */
public class GtuLinkedList<E> extends LinkedList<E>  {
    private LinkedList<Integer> disabledIndexes;

    /**
     * Constructs the list
     */
    public GtuLinkedList(){
        super();
        disabledIndexes = new LinkedList<>();
    }

    /**
     * Constructs the list from current one
     * @param c Another list
     */
    public GtuLinkedList(Collection<? extends E> c){
        super(c);
        disabledIndexes = new LinkedList<>();
    }

    /**
     * Disables an item at given location
     * @param index Item index
     * @throws InvalidParameterException When item is already disabled or not in present
     */
    public void disable(int index) throws InvalidParameterException {
        if(index >= this.size() || index < 0 || isDisabled(index)) throw new InvalidParameterException("Index out of range or already disabled item.");
        else disabledIndexes.add(index);
    }

    /**
     * Enables a disabled item at given location
     * @param index Item index
     * @throws InvalidParameterException When item is already enabled or not in present
     */
    public void enable(int index) throws InvalidParameterException {
        if(isDisabled(index)) disabledIndexes.removeFirstOccurrence(index);
        else throw new InvalidParameterException(" Given index isn't disabled.");
    }

    /**
     * Prints disabled items
     * @return a new linkedlist that contains disabled items.
     */
    public GtuLinkedList<E> showDisabled() {
        GtuLinkedList<E> disabledItems = new GtuLinkedList<>();
        for (int index : disabledIndexes) {
            E tmp = super.get(index);
            System.out.println(tmp);
            disabledItems.add(tmp);
        }
        return disabledItems;
    }

    /**
     * Checks if given item in the index disabled or not
     * @param index item index
     * @return is it disabled or not
     */
    private boolean isDisabled(int index){
        return disabledIndexes.contains(index);
    }

    @Override
    public int size() {
        return super.size() - disabledIndexes.size();
    }

    @Override
    public E get(int index) throws InvalidParameterException {
        if(isDisabled(index)) throw new InvalidParameterException("Element Disabled. Can't proceed. ");
        return super.get(index);
    }

    @Override
    public E set(int index, E element) throws InvalidParameterException {
        if(isDisabled(index)) throw new InvalidParameterException("Element Disabled. Can't proceed.");
        return super.set(index, element);
    }

    @Override
    public E remove(int index) throws InvalidParameterException {
        if(isDisabled(index)) throw new InvalidParameterException("Element Disabled. Can't proceed.");
        return super.remove(index);
    }

    @Override
    public ListIterator<E> listIterator(int index) throws InvalidParameterException {
        if(isDisabled(index)) throw new InvalidParameterException("Element Disabled. Can't proceed.");
        return super.listIterator(index);
    }
}
