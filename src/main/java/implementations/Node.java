package implementations;

public class Node {
    public Message data;
    Node next;

    Node(Message data) {
        this.data = data;
        next = null;
    }
}
