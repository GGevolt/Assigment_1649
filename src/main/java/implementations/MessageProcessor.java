package implementations;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
