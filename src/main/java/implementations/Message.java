package implementations;

public class Message {
    public String content;
    private String timestamp;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
