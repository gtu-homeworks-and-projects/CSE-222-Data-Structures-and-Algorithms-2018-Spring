package Q1;

import trees.RedBlackTree;

public class Main {
    /**
     * Constructs a Red Black Tree with height of 6 which is in one of possible worst scenarios.
     * I think this is possible by making one side (left/right or even both) all black.
     */
    public static void main(String[] args){
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for(int i = 0; i < 20; i++) tree.add(i);
        System.out.println("First Worst Red Black: (Height = 6)");
        System.out.println(tree);
        System.out.println();
        tree = new RedBlackTree<>();
        for(int i = 0; i < 20; i++) tree.add(20-i);
        System.out.println("Second Worst Red Black: (Height = 6)");
        System.out.println(tree);
        System.out.println();
    }
}
