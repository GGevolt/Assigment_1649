package implementations;

public class MessageQueue<E>{

    Node front, rear;

    public MessageQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(Message msg) {
        Node temp = new Node(msg);
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }
        this.rear.next = temp;
        this.rear = temp;
    }

    public Node dequeue() {
        if (this.front == null){
            return null;
        }
        Node temp = this.front;
        this.front = this.front.next;

        if (this.front == null)
            this.rear = null;
        return temp;
    }
}
