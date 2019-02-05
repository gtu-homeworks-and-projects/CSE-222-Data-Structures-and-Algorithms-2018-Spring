package gtu;

/**
 * Binary Tree representation of General Tree
 * @param <E> Type of Tree data
 */
public class GeneralTree<E> extends BinaryTree<E> {

    public GeneralTree(){
        super();
    }

    protected GeneralTree(Node<E> root) {
        super(root);
    }

    /**
     * Finds the node that contains given data
     * @param data data to be searched
     * @return Found Node or null if not found
     */
    public Node<E> find(E data){
        return levelOrderSearch(root,new Node<>(data));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root,1,sb);
        return sb.toString();
    }

    /**
     * Constructs a new Node from childData and adds it to the right of the parent Node which contains parentData
     * @param parentData Parent Nodes data
     * @param childData data to be inserted
     * @return success condition
     */
    public boolean add(E parentData, E childData){
        if(parentData == null) return insert(null, new Node<>(childData));
        else if(levelOrderSearch(root,new Node<>(childData)) != null) return false;
        return insert(new Node<>(parentData), new Node<>(childData));
    }

    private Node<E> levelOrderSearch(Node<E> parentNode, Node<E> nodeItem) {
        if(parentNode != null) {
            Node<E> searchItem = null;
            if (nodeItem.data == parentNode.data) return parentNode; // checks first node first
            if (parentNode.right != null) searchItem = levelOrderSearch(parentNode.right, nodeItem); //Searches same level
            if (parentNode.left != null && searchItem == null) searchItem = levelOrderSearch(parentNode.left, nodeItem); //Searches one level down
            return searchItem;
        }
        else return null;
    }

    private Node<E> postOrderSearch(Node<E> parentNode, Node<E> nodeItem){
        if(parentNode != null) {
            Node<E> searchItem = null;
            if (parentNode.left != null) searchItem = postOrderSearch(parentNode.left, nodeItem); //Searches one level down
            if (parentNode.right != null && searchItem == null) searchItem = postOrderSearch(parentNode.right, nodeItem); //Searches same level
            if (nodeItem.data == parentNode.data) return parentNode; // checks node at last
            return searchItem;
        }
        else return null;
    }

    @Override
    protected void preOrderTraverse(Node<E> parentNode, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (parentNode == null) {
            sb.append("\n");
        } else {
            sb.append(parentNode.toString());
            sb.append("\n");

            if(parentNode.left != null) preOrderTraverse(parentNode.left, depth + 1, sb); // Traverse child before moving to the siblings
            if(parentNode.right != null) preOrderTraverse(parentNode.right, depth, sb);
        }
    }

    private boolean insert(Node<E> parentNode, Node<E> childNode){
        if(root == null && parentNode == null) {
            root = childNode;
            return true;
        }
        Node<E> nodeItem = levelOrderSearch(root, parentNode);
        if(nodeItem == null) return false;
        else{
            if(nodeItem.left == null) nodeItem.left = childNode;
            else {
                nodeItem = nodeItem.left;
                while (nodeItem.right != null) nodeItem = nodeItem.right;
                nodeItem.right = childNode;
            }
            return true;
        }
    }


}
