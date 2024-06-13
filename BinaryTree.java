public class BinaryTree {
    //this node is the root of the tree
    private Node root=null;
    //This variable stores the number of nodes in the tree.
    private int size=0;
    //checks if the tree is empty
    boolean isEmpty(){
        return root==null;
    }
    
    int getsize(){
        return size;
    }
    Node getRoot(){
        return root;
    }
    void setRoot(Node node){
        root=node;
    }
    
    //This function adds a new node to the tree. When adding, it takes into account the last name of the contact object to be added and places it in the tree.
    Node addNode(Node node,contact element){
        if (node==null) {
            node= new Node(null,null,element);
            size++;
            return node;
        }
        if (node.getElement().lastName.compareToIgnoreCase(element.lastName)>0)
            node.setLeftChild(addNode(node.getLeftChild(), element));
        else if (node.getElement().lastName.compareToIgnoreCase(element.lastName)<0)
            node.setRightChild(addNode(node.getRightChild(), element));
        size++;
        return node;
    }
    //This function performs deletion within the tree.
    Node removeNode(Node node,String lastName){
        //Here, it first finds the node that needs to be deleted using the last name parameter it received. 
        //After, the deleted node finds the value it needs to replace. Using the MinElement function, it finds the minimum node and replaces it.
        //If the deleted node has no children, it directly equalizes the node to null.
        if(node==null){
            return null;
        }
        if (node.getElement().lastName.compareTo(lastName)>0)
            node.setLeftChild(removeNode(node.getLeftChild(), lastName));
        else if (node.getElement().lastName.compareTo(lastName)<0)
            node.setRightChild(removeNode(node.getRightChild(), lastName));
        else {
            if (node.getLeftChild() == null)
                return node.getRightChild();
            else if (node.getRightChild() == null)
                return node.getLeftChild();

            node.setElement(minElement(node.getRightChild()));
            node.setRightChild(removeNode(node.getRightChild(), node.getElement().lastName)) ;
            
        }
        size--;
        return node;
    }
    //This function reaches the smallest left child of the node it receives and returns the contact class held by this node.
    public contact minElement(Node node) {
        contact minValue = node.getElement();
        while (node.getLeftChild() != null) {
            minValue = node.getLeftChild().getElement();
            node = node.getLeftChild();
        }
        return minValue;
    }
}
