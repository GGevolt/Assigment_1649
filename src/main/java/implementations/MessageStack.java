package implementations;


public class MessageStack<E> {

    Node head;

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public void push(Message msg) {
        if (head == null) {
            head = new Node(msg);
        } else {
            Node temp = new Node(msg);
            temp.next = head;
            head = temp;
        }
    }

    public void listAll() {
        if(isEmpty()){
            System.out.println("Stack is empty!");
            return;
        }
            System.out.println("Processed messages:");
            printFromNode(head);
    }

    public void listFiltered(String filter) {
        if(isEmpty()){
            System.out.println("Stack is empty!");
            return;
        }
        printFromNode(head, filter);
    }

    private void printFromNode(Node node, String filter) {
        if (node == null) {
            return;
        }
        printFromNode(node.next, filter);
        if(node.data.getContent().contains(filter)) {
            System.out.println(node.data.getContent());
        }
    }

    private void printFromNode(Node node) {
        if (node == null) {
            return;
        }
        printFromNode(node.next);
        System.out.println(node.data.getContent());
    }
}
