package implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageQueueTest {
    private MessageQueue<String> messageQueue;

    @Before
    public void setUp() {
        this.messageQueue = new MessageQueue<>();
        for (int i = 0; i < 100; i++) {
            messageQueue.offer(String.valueOf(i));
        }
    }

    @Test
    public void testPushShouldAddAtTheEnd() {
        messageQueue.offer("Slayer");
        assertEquals("0", messageQueue.peek());
    }

    @Test
    public void testPopShouldRemoveAndReturnTopElement() {
        assertEquals("0", messageQueue.poll());
        assertEquals(99, messageQueue.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPopShouldThrowWhenEmpty() {
        new MessageQueue<>().poll();
    }

    @Test
    public void testPeekShouldReturnAndNotRemove() {
        assertEquals("0", messageQueue.peek());
        assertEquals(100, messageQueue.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekShouldThrowWhenEmpty() {
        new MessageQueue<>().peek();
    }

    @Test
    public void testSize() {
        assertEquals(100, messageQueue.size());
        assertEquals(0, new MessageQueue<>().size());
    }


    @Test
    public void testIsEmpty() {
        assertTrue(new MessageQueue<>().isEmpty());
        assertFalse(messageQueue.isEmpty());
    }

    @Test
    public void testIteratorShouldTraversItemsFromBeginToEnd() {
        int last = 0;
        for (String s : messageQueue) {
            assertEquals(String.valueOf(last++), s);
        }
    }
}