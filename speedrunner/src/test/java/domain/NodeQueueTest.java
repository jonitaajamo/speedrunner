package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeQueueTest {
    private NodeQueue queue;


    @Before
    public void beforeTest() {
        this.queue = new NodeQueue();
    }

    @Test
    public void nodesCanBeQueued() {
        this.queue.enqueue(new Node(1, 2));
        assertEquals(1, this.queue.size());
    }

    @Test
    public void nodesCanBeDequeued() {
        this.queue.enqueue(new Node(1, 2));
        assertEquals(1, this.queue.dequeue().getX());
    }
}

