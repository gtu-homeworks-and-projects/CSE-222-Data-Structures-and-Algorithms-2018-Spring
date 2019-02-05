package gtu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTreeTest {
    GeneralTree<Integer> testTree = new GeneralTree<>(new BinaryTree.Node<>(10));

    private boolean fillTree(){
        int flag = 0;
        flag += testTree.add(10,15) ? 1 : 0;
        flag += testTree.add(10,20) ? 1 : 0;
        flag += testTree.add(10,25) ? 1 : 0;
        flag += testTree.add(20,5) ? 1 : 0;
        flag += testTree.add(5,30) ? 1 : 0;
        flag += testTree.add(5,35) ? 1 : 0;
        return flag == 6;
    }

    @Test
    void find() {
        fillTree();
        assertEquals(testTree.find(35).data,(Integer)35);
        assertEquals(testTree.find(10).data,(Integer)10);
        assertEquals(testTree.find(25).data,(Integer)25);

    }

    @Test
    void add() {
        assertTrue(fillTree());
    }
}