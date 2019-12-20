package domain;

public class NodeQueue {

    private Node[] values;
    private int head;
    private int tail;
    private int size;

    public NodeQueue() {
        this.values = new Node[11];
        this.head = 0;
        this.tail = 0;
        this.size = 11;
    }

    /**
     * Enqueues new node into the queue.
     * @param node Node to be enqueued
     */
    public void enqueue(Node node) {
        if (isFull()) {
            increaseSize();
        }

        values[tail] = node;
        tail++;
        if (tail == size) {
            tail = 0;
        }
    }

    /**
     * Doubles the current queue size, if gets full. Is called during enqueue operation.
     */
    private void increaseSize() {
        Node[] newValues = new Node[size * 2];
        int newTail = 0;
        while (!isEmpty()) {
            newValues[newTail] = values[head];
            head = next(head);
            newTail++;
        }
        tail = newTail;
        values = newValues;
        size *= 2;
    }

    /**
     * Selects next head position.
     * @param index Current index
     * @return new head position.
     */
    private int next(int index) {
        return (index + 1) % size;
    }

    /**
     * Takes the first node from queue and changes head's position.
     * @return Node taken from queue
     */
    public Node dequeue() {
        Node removable = values[head];
        head = next(head);
        return removable;
    }

    /**
     * Checks if queue is full.
     * @return true, if queue is full
     */
    public boolean isFull() {
        int tailnext = tail + 1;
        if (tailnext == size) {
            tailnext = 0;
        }
        return head == tailnext;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return head + 1;
    }
}