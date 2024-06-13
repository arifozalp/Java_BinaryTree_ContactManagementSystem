import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;


public class ContactManagementSystem {
    BinaryTree tree=new BinaryTree();
    LinkedList list=new <contact>LinkedList();
    //This is the constructor of the ContactManagementSystem class.
    ContactManagementSystem(){
        System.out.println("Welcome to the Contact Management System!");
         //In this part, file reading is done. The read line is saved into a string array and this array is used to create the contact class. Finally, a new node is added to the tree.
         try{
            File file = new File("contacts.csv");
            Scanner scanner = new Scanner(file);
            System.out.println("Reading contact details from file...\n");
            while(scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                if(newLine.trim().isEmpty())
                    continue;
                String[] line = newLine.trim().split(",");
                if(line.length==4&&isName(line[0].trim())&&isName(line[1].trim())&&isNumber(line[2].trim())){
                    contact element=new contact(line[0].trim(),line[1].trim(),line[2].trim(),line[3].trim());
                    tree.setRoot(tree.addNode(tree.getRoot(), element));
                    list.add(element);
                }else{
                    System.out.println("One line cannot add in list");
                }
            }
        System.out.println("Contact details loaded successfully!\n");
        userIteration();
        }
        catch(Exception e){
            System.out.println("Your file not found");
        }
        
    }
    //This function gives the user a looping menu and receives input. It takes action based on the input it receives.
    void userIteration(){
        int input=0;
        String tempInput;
        Scanner scanner=new Scanner(System.in);
        while(input!=6){
            System.out.println("Please select an option:\n" +
"1. Display contacts (Preorder)\n" +
"2. Display contacts (Inorder)\n" +
"3. Display contacts (Postorder)\n" +
"4. Search for a contact\n" +
"5. Delete a contact\n" +
"6. Exit\n");
            System.out.print("Your choice: ");
            tempInput=scanner.next();
            if(isNumber(tempInput)){
                input=Integer.parseInt(tempInput);
                switch(input){
                case(1):
                    System.out.println("Displaying contacts (Preorder):\n");
                    preorderTraversal(tree.getRoot());
                    System.out.println("");
                    break;
                case(2):
                    System.out.println("Displaying contacts (Inorder):\n");
                    inorderTraversal(tree.getRoot());
                    System.out.println("");
                    break;
                case(3):
                    System.out.println("Displaying contacts (Postorder):\n");
                    postorderTraversal(tree.getRoot());
                    System.out.println("");
                    break;
                case(4):
                    System.out.print("Enter the last name of the contact you want to search for: ");
                    String lastName=scanner.next();
                    Node searchingNode=searchContact(tree.getRoot(),lastName);
                    if(searchingNode!=null){
                        System.out.println("\nContact found:");
                        System.out.println("- "+searchingNode.getElement());
                    }else{
                        System.out.println("Contact not found!");
                    }
                    break;
                case(5):
                    System.out.print("Enter the last name of the contact you want to delete: ");
                    lastName=scanner.next();
                    searchingNode=searchContact(tree.getRoot(),lastName);
                    if(searchingNode!=null){
                        list.remove(searchingNode.getElement());
                        deleteContact(searchingNode.getElement().lastName);
                        System.out.println("\nContact deleted successfully!");
                    }else{
                        System.out.println("\nContact not found!");
                    }
                    break;
                case(6):
                    System.out.println("Exiting and saving contact details to file...");
                    write();
                    break;
                default:
                    System.out.println("Please enter numbers between 1 and 6");
            }
            }else{
                System.out.println("Please enter number");
            }}
    }
    //This function traverses and prints the tree according to the preorder rule.
    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.println("- "+node.getElement());
            preorderTraversal(node.getLeftChild());
            preorderTraversal(node.getRightChild());
        }
    }
    //This function traverses and prints the tree according to the inorder rule.
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.getLeftChild());
            System.out.println("- "+node.getElement());
            inorderTraversal(node.getRightChild()); 
        }
    }
    //This function traverses and prints the tree according to the postorder rule.
    private void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.getLeftChild());
            postorderTraversal(node.getRightChild());
            System.out.println("- "+node.getElement());
        }
    }
    //This function calls the remove function on the binary tree class using the last name parameter it receives.
    private void deleteContact(String lastName) {
        tree.setRoot(tree.removeNode(tree.getRoot(),lastName)); 
    }
    //This function searches the tree using the last name. If it finds it in the tree, it returns the node. If it can't find it, it returns null.
    private Node searchContact(Node root,String lastName) {
        if(convertToEnglish(root.getElement().lastName.toUpperCase()).equals(convertToEnglish(lastName.toUpperCase()))){
            return root;
        }else if(convertToEnglish(root.getElement().lastName.toUpperCase()).compareTo(convertToEnglish(lastName.toUpperCase()))>0&&root.getLeftChild()!=null){
            return searchContact(root.getLeftChild(),lastName);
        }else if(convertToEnglish(root.getElement().lastName.toUpperCase()).compareTo(convertToEnglish(lastName.toUpperCase()))<0&&root.getRightChild()!=null){
            return searchContact(root.getRightChild(),lastName);
        }else{
            return null;
        }
    }
    //This function is used to save the operations performed on contact.csv when the program is closed.
    private void write(){
        try {
            FileWriter fileWriter = new FileWriter("contacts.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i=0;i<list.size();i++){
                contact element=(contact)list.get(i);
                bufferedWriter.write(element.firstName+","+element.lastName+","+element.ID+","+element.mail);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Saved successfully ");
        }catch (Exception e) {
            System.out.println("File write error");
        }
    }
    //checks if the word is a name
    public boolean isName(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLetter(c)) {
                return false; 
            }
        }
        return true; 
    }
    //checks if the word is a number
    public boolean isNumber(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isDigit(c)) {
                return false; 
            }
        }
        return true; 
    }
    //This function translates the word it receives into English.
    String convertToEnglish(String word){
        return word.replace("İ", "I")
                   .replace("Ğ", "G")
                   .replace("Ü", "U")
                   .replace("Ş", "S")
                   .replace("Ö", "O")
                   .replace("Ç", "C")
                   .replace("ı", "i")  
                   .replace("ğ", "g") 
                   .replace("ü", "u")
                   .replace("ş", "s")
                   .replace("ö", "o")
                   .replace("ç", "c");
    }
}
