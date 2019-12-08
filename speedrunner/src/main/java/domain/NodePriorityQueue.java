package domain;

public class NodePriorityQueue {

    private Node[] queue;
    private int size;
    private int freeIndex;

    /**
     * Initializes new NodePriorityQueue.
     * Implemented using binary heap.
     * Sets index 0 as parent node to all added nodes, index 0 is never used in queue.
     */
    public NodePriorityQueue() {
        this.queue = new Node[5];
        this.size = 0;
        this.freeIndex = 1;
        this.queue[0] = new Node(1, 1, Integer.MIN_VALUE);
    }

    /**
     * Adds new node to queue
     * @param node new Node added to queue
     */
    public void add(Node node) {
        if(this.queue.length == this.size + 1) {
            increaseSize();
        }

        this.queue[this.freeIndex] = node;
        this.freeIndex += 1;
        this.size += 1;
        setNewPriority();
    }

    /**
     * Polls node from sorted queue and returns it
     * @return Node with smallest heuristic
     */
    public Node poll() {
        Node headNode = this.queue[1];
        this.queue[1] = this.queue[this.size];
        this.queue[this.size] = null;
        this.freeIndex -= 1;
        this.size -= 1;
        if(this.size > 0) {
            setNewPriorityPoll();
        }
        return headNode;
    }

    /**
     * Prioritizes queue. Called after polling from the queue.
     */
    private void setNewPriorityPoll() {
        int index = 1;
        while(index * 2 <= this.size) {
            int minHeuristicIndex = minChild(index);
            if(this.queue[index].getHeuristic() > this.queue[minHeuristicIndex].getHeuristic()) {
                swap(index, minHeuristicIndex);
            }
            index = minHeuristicIndex;
        }
    }

    /**
     * Prioritizes queue. Called after adding to the queue.
     */
    private void setNewPriority() {
        int currentIndex = this.size;
        int parentIndex = this.size / 2;

        while(this.queue[currentIndex].getHeuristic() < this.queue[parentIndex].getHeuristic()) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = currentIndex / 2;
        }
    }

    /**
     * Finds node's child with smallest heuristic utilizing binary heap.
     * @param index index of parent node whose children look for
     * @return
     */
    private int minChild(int index) {
        if (index * 2 +1 > this.size) {
            return index * 2;
        } else if(this.queue[index * 2].getHeuristic() < this.queue[index * 2 +1].getHeuristic()) {
            return index * 2;
        } else {
            return index * 2 + 1;
        }
    }

    /**
     * Swaps places of two indexes
     * @param index1 First node to swap
     * @param index2 Second node to swap
     */
    private void swap(int index1, int index2) {
        Node tmpNode1 = queue[index1];
        Node tmpNode2 = queue[index2];
        queue[index1] = tmpNode2;
        queue[index2] = tmpNode1;
    }

    /**
     * Doubles the size of queue
     */
    private void increaseSize() {
        Node[] newQueue = new Node[this.queue.length * 2];
        for(int i = 0; i < this.queue.length; i++) {
            newQueue[i] = this.queue[i];
        }
        this.queue = newQueue;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
}
