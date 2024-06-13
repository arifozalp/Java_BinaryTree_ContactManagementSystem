public class Node {
    private contact element;
    private Node leftChild;
    private Node rightChild;
    //This is the constructor of the node class.
    Node(Node leftChild, Node rightChild, contact element){
        this.element=element;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }
    //encapsulation functions
    Node getRightChild(){
        return rightChild;
    }
    Node getLeftChild(){
        return leftChild;
    }
    void setRightChild(Node node){
        this.rightChild=node;
    }
    void setLeftChild(Node node){
        this.leftChild=node;
    }
    contact getElement(){
        return element;
    }
    void setElement(contact element){
        this.element=element;
    }
}
