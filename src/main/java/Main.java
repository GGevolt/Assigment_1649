import implementations.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        MessageStack stack = new MessageStack();
        MessageProcessor processor = new MessageProcessor();
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
                        processor.inputMessage(scanner, queue);
                        break;
                    case 2:
                        processor.messageProcess(stack,queue);
                        break;
                    case 3:
                        stack.listAll();
                        break;
                    case 4:
                        stack.listFiltered(scanner);
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
        }catch (InputMismatchException e) {
            System.out.println("Invalid input type: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred: " + ex.getMessage());
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }
    }
}

