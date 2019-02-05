package gtu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MDSearchTreeTest {
    MDSearchTree<Integer> testTree = new MDSearchTree<>();

    private boolean fillTree(){
        int flag = 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{18,27,44})) ? 1 : 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{13,30,32})) ? 1 : 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{21,37,45})) ? 1 : 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{5,10,15})) ? 1 : 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{1,31,3})) ? 1 : 0;
        flag += testTree.add(new MultiNode<>(new Integer[]{8,4,6})) ? 1 : 0;
        return flag == 6;
    }

    @Test
    void add() {
        assertTrue(fillTree());
    }

    @Test
    void find() {
        fillTree();
        assertEquals(testTree.find(new MultiNode<>(new Integer[]{5,10,15})).dataPoints[1],(Integer) 10);
        assertEquals(testTree.find(new MultiNode<>(new Integer[]{21,37,45})).dataPoints[0],(Integer) 21);
        assertEquals(testTree.find(new MultiNode<>(new Integer[]{13,30,32})).dataPoints[2],(Integer) 32);
        assertEquals(testTree.find(new MultiNode<>(new Integer[]{8,4,6})).dataPoints[1],(Integer) 4);
    }

    @Test
    void delete() {
        fillTree();
        testTree.delete(new MultiNode<>(new Integer[]{8,4,6}));
        assertNull(testTree.find(new MultiNode<>(new Integer[]{8, 4, 6})));
    }
}