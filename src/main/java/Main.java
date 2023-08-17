import implementations.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        MessageStack stack = new MessageStack();
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Send a message.");
                System.out.println("2. Process all messages.");
                System.out.println("3. View all processed messages.");
                System.out.println("4. Filter processed messages.");
                System.out.println("5. Exit.");
                String selection = scanner.nextLine();
                int option = 0;
                try {
                    option = Integer.parseInt(selection);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    continue;
                }
                switch (option) {
                    case 1:
                        inputMessage(scanner, queue);
                        break;
                    case 2:
                        messageProcess(stack,queue);
                        break;
                    case 3:
                        stack.listAll();
                        break;
                    case 4:
                        System.out.println("Enter a keyword to filter by:");
                        String filter = scanner.nextLine();
                        stack.listFiltered(filter);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        if(scanner != null) {
                            scanner.close();
                        }
                        System.exit(0);

                    default:
                        System.out.println("Invalid option. Please choose an option between 1 and 5.");
                }
            }
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
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
        Node dequeuedNode = queue.dequeue();
        while (dequeuedNode != null) {
            MessageProcessor.process(dequeuedNode.data);
            stack.push(dequeuedNode.data);
            dequeuedNode = queue.dequeue();
        }
        System.out.println("All messages processed!");
    }
}

