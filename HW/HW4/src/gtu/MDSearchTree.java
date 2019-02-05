package gtu;

public class MDSearchTree<E extends Comparable<E>> extends BinaryTree<MultiNode<E>> implements SearchTree<MultiNode<E>>{
    private int dimension;

    /**
     * Constructs 3 dimensional Tree
     */
    public MDSearchTree(){
        root = null;
        dimension = 3; //Default dimension size is 3.
    }

    /**
     * Constructs multidimensional tree according to variable
     * @param dimension Dimension count
     */
    public MDSearchTree(int dimension){
        root = null;
        this.dimension = dimension;
    }

    /**
     * Constructs tree from an existing root node
     * @param root root node that contains initial data
     */
    public MDSearchTree(Node<MultiNode<E>> root){
        this(root.data.dimension);
        this.root = root;
    }

    private Node<MultiNode<E>> insert(Node<MultiNode<E>> parentNode, MultiNode<E> item, int depth){
        if(parentNode == null) return new Node<>(item);

        int operatingDimension = depth % dimension;

        if(item.dataPoints[operatingDimension].compareTo(parentNode.data.dataPoints[operatingDimension]) <= 0)
            parentNode.left = insert(parentNode.left, item,depth+1);
        else
            parentNode.right = insert(parentNode.right, item,depth+1);

        return parentNode;
    }

    @Override
    public boolean add(MultiNode<E> item) {
        if(!isValidDimension(item)) return false;

        root = insert(root, item, 0);
        return root != null;
    }

    @Override
    public boolean contains(MultiNode<E> target) {
        return find(target) != null;
    }

    @Override
    public MultiNode<E> find(MultiNode<E> target) {
        Node<MultiNode<E>> result = search(root, target,0);
        return result == null ? null : result.data;
    }

    @Override
    public MultiNode<E> delete(MultiNode<E> target) {
        Node<MultiNode<E>> result = recursiveDelete(root, target,0);
        return result == null ? null : result.data;
    }

    @Override
    public boolean remove(MultiNode<E> target) {
        return delete(target) != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    @Override
    protected void preOrderTraverse(Node<MultiNode<E>> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth && node != null; i++) {
            sb.append("  ");
        }
        if (node != null) {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }


    private boolean isValidDimension(MultiNode<E> item){ return item.dimension == dimension; }

    private Node<MultiNode<E>> search(Node<MultiNode<E>> parentNode, MultiNode<E> target, int depth){
        if(parentNode == null) return null;
        if(parentNode.data.equals(target)) return parentNode;

        int operatingDimension = depth % dimension;

        if(target.dataPoints[operatingDimension].compareTo(parentNode.data.dataPoints[operatingDimension]) <= 0)
            return search(parentNode.left, target,depth+1);

        return search(parentNode.right, target,depth+1);
    }

    private Node<MultiNode<E>> recursiveDelete(Node<MultiNode<E>> parentNode, MultiNode<E> target, int depth){
        if(parentNode == null) return null;
        int operatingDimension = depth % dimension;

        if(parentNode.data.equals(target)){
            if(parentNode.right != null){
                parentNode.data = findMin(parentNode.right, operatingDimension, depth + 1).data;
                parentNode.right = recursiveDelete(parentNode.right, parentNode.data, depth + 1);
            }
            else if(parentNode.left != null){
                parentNode.data = findMin(parentNode.left, operatingDimension, depth+ 1).data;
                parentNode.left = recursiveDelete(parentNode.left, parentNode.data, depth +1);
            }
            else parentNode = null;
        }

        else if(parentNode.data.dataPoints[operatingDimension].compareTo(target.dataPoints[operatingDimension]) > 0)
            parentNode.left = recursiveDelete(parentNode.left, target, depth + 1);
        else
            parentNode.right = recursiveDelete(parentNode.right, target, depth + 1);

        return parentNode;
    }

    private Node<MultiNode<E>> findMin(Node<MultiNode<E>> parentNode, int dimension, int currentDepth){
        if(parentNode == null) return null;
        int operatingDimension = currentDepth % this.dimension;

        if(operatingDimension == dimension){
            if(parentNode.left == null) return parentNode;
            else return findMin(parentNode.left, dimension, currentDepth + 1);
        }
        else {
            Node<MultiNode<E>> resultLeft = findMin(parentNode.left, dimension, currentDepth+ 1);
            Node<MultiNode<E>> resultRight = findMin(parentNode.right, dimension, currentDepth+ 1);
            if(resultLeft != null && resultRight != null)
                if(resultLeft.data.dataPoints[dimension].compareTo(resultRight.data.dataPoints[dimension]) < 0) return resultLeft;
                else return resultRight;
            else return parentNode;
        }
    }
}
