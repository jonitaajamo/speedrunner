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

    private int next(int i) {
        return (i + 1) % size;
    }

    public Node dequeue() {
        Node removable = values[head];
        head = next(head);
        return removable;
    }

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