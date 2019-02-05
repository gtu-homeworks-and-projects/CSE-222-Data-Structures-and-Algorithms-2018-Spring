package Q2;

import trees.BTree;

public class Main {
    public static void main(String[] args){
        BTree<Integer> bTree = new BTree<>(3);
        for(int i = 1; i < 12; i++) if ( i != 5) bTree.add(i); //BTree successfully adds by providing a correct binarySearch method.
        //System.out.println(bTree);
        bTree.add(5);
        if(bTree.binarySearch(5)) System.out.println("5 is in the bTree");
        if(bTree.binarySearch(9)) System.out.println("9 is in the bTree");
        bTree = new BTree<>(4);
        for(int i = 20; i < 31; i++) bTree.add(i);
        if(!bTree.binarySearch(15)) System.out.println("15 isn't in the bTree");
        if(bTree.binarySearch(25)) System.out.println("25 is in the bTree");
        //System.out.println(bTree);
    }
}
