package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodePriorityQueueTest {
    private NodePriorityQueue queue;

    @Before
    public void beforeTest() {
        this.queue = new NodePriorityQueue();
    }

    @Test
    public void nodesCanBeAddedToQueue() throws Exception {
        queue.add(new Node(2,2,5));
        queue.add(new Node(3,3,3));
        queue.add(new Node(2,2,4));
        queue.add(new Node(2,2,6));
        queue.add(new Node(5,5,2));
        assertEquals(5, queue.size());
    }

    @Test
    public void moreThanFiveNodesCanBeAddedToQueue() throws Exception {
        queue.add(new Node(2,2,5));
        queue.add(new Node(3,3,3));
        queue.add(new Node(1,2,4));
        queue.add(new Node(5,8,6));
        queue.add(new Node(5,5,2));
        queue.add(new Node(5,6,9));
        assertEquals(6, queue.size());
    }

    @Test
    public void nodesCanBePolled() throws Exception {
        Node node = new Node(2,2,5);
        queue.add(node);
        assertEquals(node, queue.poll());
    }

    @Test
    public void pollingNodeReturnsCorrectNode() throws Exception {
        queue.add(new Node(2,2,5));
        queue.add(new Node(3,3,3));
        queue.add(new Node(1,2,4));
        queue.add(new Node(5,8,6));
        queue.add(new Node(9,5,2));
        queue.add(new Node(5,6,9));
        assertEquals(9, queue.poll().getX());
    }
}
