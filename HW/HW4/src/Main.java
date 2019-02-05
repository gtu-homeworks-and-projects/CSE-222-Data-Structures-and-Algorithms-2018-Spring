import gtu.*;

public class Main {

    public static void main(String[] args) {
        // PART 1 Test START
        System.out.println("PART 1 Tests:");
        GeneralTree<Integer> a = new GeneralTree<>();
        if(a.add(null,5)) System.out.println("Successful parent insert.");
        a.add(5,6);
        a.add(5,8);
        a.add(6,4);
        a.add(5,10);
        a.add(5,15);
        a.add(6,1);
        a.add(15,14);
        a.add(6,7);
        if(!a.add(6,7)) System.out.println("Can't add 7 to parent 6 because it already exists.");
        a.add(14,13);
        System.out.println( "Node: " + a.find(15));
        System.out.println(a);

        GeneralTree<String> b = new GeneralTree<>();
        b.add(null,"Onur");
        b.add("Onur", "Ahmet");
        b.add("Onur", "Dilara");
        b.add("Ahmet", "Aslı");
        b.add("Onur", "Yasir");
        b.add("Yasir", "Fatih");
        System.out.println(b);
        // PART 1 Test END
        System.out.println("PART 2 Tests:");
        // PART 2 Test START
        MDSearchTree<Integer> c = new MDSearchTree<>();
        c.add(new MultiNode<>(new Integer[]{18,27,44}));
        c.add(new MultiNode<>(new Integer[]{13,30,32}));
        c.add(new MultiNode<>(new Integer[]{21,37,45}));
        c.add(new MultiNode<>(new Integer[]{5,10,15}));
        c.add(new MultiNode<>(new Integer[]{1,31,3}));
        c.add(new MultiNode<>(new Integer[]{8,4,6}));
        System.out.println("Before delete");
        System.out.println(c);
        if(c.contains(new MultiNode<>(new Integer[]{1,31,3})))
            c.delete(new MultiNode<>(new Integer[]{1,31,3}));
        System.out.println("After delete");
        System.out.println(c);
        // PART 2 Test END
    }
}
