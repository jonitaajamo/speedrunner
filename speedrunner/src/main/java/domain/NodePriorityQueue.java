package domain;

public class NodePriorityQueue {

    private Node[] queue;
    private int size;
    private int freeIndex;

    public NodePriorityQueue() {
        this.queue = new Node[5];
        this.size = 0;
        this.freeIndex = 1;
        this.queue[0] = new Node(1, 1, Integer.MIN_VALUE);
    }

    public void add(Node node) {
        if(this.queue.length == this.size + 1) {
            increaseSize();
        }
        this.size++;
        this.freeIndex++;
        setNewPriority();
    }

    public Node poll() {
        Node headNode = this.queue[1];
        this.queue[1] = this.queue[this.size];
        this.queue[this.size] = null;
        this.freeIndex -= 1;
        this.size -= 1;
        if(this.size > 0) {
            setNewPriorityPoll();
        }
        return null;
    }

    private void setNewPriorityPoll() {
    }

    private void setNewPriority() {
        int currentIndex = this.size;
        int parentIndex = this.size / 2;

        while(queue[currentIndex].getHeuristic() < queue[parentIndex].getHeuristic()) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = currentIndex / 2;
        }
    }

    private void swap(int index1, int index2) {
        Node tmpNode1 = queue[index1];
        Node tmpNode2 = queue[index2];
        queue[index1] = tmpNode2;
        queue[index2] = tmpNode1;
    }

    private void increaseSize() {
        Node[] newQueue = new Node[this.queue.length * 2];
        for(int i = 0; i < this.queue.length; i++) {
            newQueue[i] = this.queue[i];
        }
        this.queue = newQueue;
    }


}
