package benchmark;

import implementations.ArrayList;
import implementations.MessageQueue;
import implementations.SinglyLinkedList;
import implementations.MessageStack;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// Arguments that specify the memory allocation pool
// Xms -> starting memory pool 2GB and Xmx - the maximum memory pool 4GB
// NOTE: When running those tests you may want to adjust those values as well
// as the param value, otherwise -> java.lang.OutOfMemoryError may occur
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx4G"})
@State(Scope.Benchmark)
public class BenchmarkTests {

    @Param({"1000", "10000"/*, "100000000"*/})
    private long n;

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private MessageStack<Integer> messageStack = new MessageStack<>();
    private MessageQueue<Integer> messageQueue = new MessageQueue<>();
    private SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

    private void fillArrayListWithRandomValues(ArrayList<Integer> list) {
        new Random()
                .ints()
                .limit(n)
                .forEach(list::add);
    }

    private void fillStackWithRandomValues(MessageStack<Integer> messageStack) {
        new Random()
                .ints()
                .limit(n)
                .forEach(messageStack::push);
    }

    private void fillQueueWithRandomValues(MessageQueue<Integer> messageQueue) {
        new Random()
                .ints()
                .limit(n)
                .forEach(messageQueue::offer);
    }

    private void fillSinglyLinkedListWithRandomValues(SinglyLinkedList<Integer> singlyLinkedList) {
        // In the SinglyLinkedList we can use a pointer to the end of the structure
        // then it wont matter if we call addLast() or addFirst()
        // but that we will implement later so for now we will call addFirst()
        new Random()
                .ints()
                .limit(n)
                .forEach(singlyLinkedList::addFirst);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkTests.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void testAddInArrayList(Blackhole blackhole) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
    }

    @Benchmark
    public void testAddInStack(Blackhole blackhole) {
        MessageStack<Integer> numbers = new MessageStack<>();
        for (int i = 0; i < n; i++) {
            numbers.push(i);
        }
    }

    @Benchmark
    public void testAddInQueue(Blackhole blackhole) {
        MessageQueue<Integer> numbers = new MessageQueue<>();
        for (int i = 0; i < n; i++) {
            numbers.offer(i);
        }
    }

    @Benchmark
    public void testAddInSinglyLinkedListAddFirst() {
        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();
        for (int i = 0; i < n; i++) {
            numbers.addFirst(i);
        }
    }

    @Benchmark
    public void testAddInSinglyLinkedListAddLast() {
        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();
        for (int i = 0; i < n; i++) {
            numbers.addLast(i);
        }
    }

    @Benchmark
    public void testRemoveAtFrontArrayList() {
        fillArrayListWithRandomValues(arrayList);
        while (!arrayList.isEmpty()) {
            arrayList.remove(0);
        }
    }

    @Benchmark
    public void testStackPop() {
        fillStackWithRandomValues(messageStack);
        while (!messageStack.isEmpty()) {
            messageStack.pop();
        }
    }

    @Benchmark
    public void testQueuePoll() {
        fillQueueWithRandomValues(messageQueue);
        while (!messageQueue.isEmpty()) {
            messageQueue.poll();
        }
    }

    @Benchmark
    public void testSinglyLinkedListRemoveFirst() {
        fillSinglyLinkedListWithRandomValues(singlyLinkedList);
        while (!singlyLinkedList.isEmpty()) {
            singlyLinkedList.removeFirst();
        }
    }

    @Benchmark
    public void testSinglyLinkedListRemoveLast() {
        fillSinglyLinkedListWithRandomValues(singlyLinkedList);
        while (!singlyLinkedList.isEmpty()) {
            singlyLinkedList.removeLast();
        }
    }
}
