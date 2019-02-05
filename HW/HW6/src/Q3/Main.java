package Q3;

import trees.AVLTree;
import trees.BinarySearchTree;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        AVLTree<Integer> aTree = new AVLTree<>();
        Random rndm = new Random();
        for(int i = 0; i < 10; i++){
            aTree.add(rndm.nextInt(20));
        }
        aTree.add(4);
        aTree.add(7);
        System.out.println(aTree);
        if(AVLTree.checkAVL(aTree)) System.out.println("AVL check of AVLTree successfull");

        BinarySearchTree<Integer> cTree = new BinarySearchTree<>();
        cTree.add(5);

        cTree.add(7);
        cTree.add(6);
        cTree.add(8);
        cTree.add(9);
        if(!AVLTree.checkAVL(cTree)) System.out.println("AVL check of Binary Tree unSuccessfull");
        // unBalanced if these 2 aren't added.
        cTree.add(3);
        cTree.add(4);
        if(AVLTree.checkAVL(cTree)) System.out.println("AVL check of Binary Tree Successfull");
        AVLTree<Integer> testTree = new AVLTree<>(cTree);
    }
}
