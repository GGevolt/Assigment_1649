package implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageStackTest {

    private MessageStack<String> messageStack;

    @Before
    public void setUp() {
        this.messageStack = new MessageStack<>();
        for (int i = 0; i < 100; i++) {
            messageStack.push(String.valueOf(i));
        }
    }

    @Test
    public void testPushShouldAddAtTheTop() {
        messageStack.push("Slayer");
        assertEquals("Slayer", messageStack.peek());
    }

    @Test
    public void testPopShouldRemoveAndReturnTopElement() {
        assertEquals("99", messageStack.pop());
        assertEquals(99, messageStack.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPopShouldThrowWhenEmpty() {
        new MessageStack<>().pop();
    }

    @Test
    public void testPeekShouldReturnAndNotRemove() {
        assertEquals("99", messageStack.peek());
        assertEquals(100, messageStack.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekShouldThrowWhenEmpty() {
        new MessageStack<>().peek();
    }

    @Test
    public void testSizeShouldReturnCorrect() {
        assertEquals(0, new MessageStack<>().size());
        assertEquals(100, messageStack.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(new MessageStack<>().isEmpty());
        assertFalse(messageStack.isEmpty());
    }

    @Test
    public void testIteratorShouldTraversItemsFromTopToBottom() {
        int last = 99;
        for (String s : messageStack) {
            assertEquals(String.valueOf(last--), s);
        }
    }
}