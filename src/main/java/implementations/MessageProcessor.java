package implementations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MessageProcessor {
    public static void process(Message message) {
        if (!isValid(message)) {
            System.out.println("Invalid message content: " + message.getContent());
            return;
        }
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        message.setTimestamp(timestamp);
        message.setContent(message.getContent() + " Processed at " + message.getTimestamp());
    }

    private static boolean isValid(Message message) {
        return message.getContent() != null && !message.getContent().trim().isEmpty();
    }
    public static void inputMessage(Scanner scanner, MessageQueue queue){
        int n =0;
        while(true) {
            System.out.println("Enter how many time you want to input your message: ");
            try {
                n = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException r) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        for (int i =0; i <n; i++){
            System.out.println("Enter your message:");
            String content = scanner.nextLine();
            if (content.trim().isEmpty()) {
                System.out.println("Message cannot be empty.");
            } else if(content.trim().length() > 250){
                System.out.println("Message cannot exceed 250 characters.");
            } else {
                queue.enqueue(new Message(content.trim()));
                System.out.println("Message sent!");
            }
        }
    }

    public static void messageProcess(MessageStack stack, MessageQueue queue){
        if(queue.isEmpty()){
            System.out.println("Queue is empty!");
        }else{
            System.out.println("All messages processed!");
        }
        Node dequeuedNode = queue.dequeue();
        while (dequeuedNode != null) {
            MessageProcessor.process(dequeuedNode.data);
            stack.push(dequeuedNode.data);
            dequeuedNode = queue.dequeue();
        }
    }
}
