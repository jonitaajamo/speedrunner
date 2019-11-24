package domain;

import java.util.Iterator;

public class NodeList implements Iterable<Node> {
    private Node t;
    private Node[] values;
    private static int DEFAULT_SIZE = 8;
    private int size;
    private int index;

    public NodeList() {
        this.values = new Node[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        this.index = 0;
    }

    /**
     * Adds new node to the list and checks if this.values has enough space.
     * @param node
     */
    public void add(Node node) {
        if(this.index==this.size-1) {
            increaseSize();
        }
        values[this.index] = node;
        this.index++;
    }

    /**
     * Gets node from the list by index
     *
     * @param i index where node is searched from
     * @return Node from the asked index
     * @throws Exception Throws exception if index is larger than the size of the array
     */
    public Node get(int i) throws Exception {
        if(i>this.index-1){
            throw new Exception("ArrayIndexOutOfBounds");
        }
        if(i<0){
            throw new Exception("Negative value");
        }
        return this.values[i];
    }

    /**
     * Removes one node from the list and moves all nodes to fill the emptied index
     * @param i index where the node is removed from
     * @throws Exception Throws exception if index is larger than the size of the array
     */
    public void remove(int i) throws Exception{
        if(i>this.index-1){
            throw new Exception("ArrayIndexOutOfBounds");
        }
        if(i<0){
            throw new Exception("Negative value");
        }
        for(int x=i; x < this.values.length-1;x++){
            this.values[x] = this.values[x+1];
        }
        this.index--;
    }

    /**
     *
     * @return the current size of the list
     */
    public int size() {
        return this.index;
    }

    private void increaseSize(){
        this.size=this.size+DEFAULT_SIZE;
        Node[] newValues = new Node[this.size];
        for(int i = 0; i<this.values.length; i++) {
            newValues[i]=this.values[i];
        }
        this.values = newValues;
    }

    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> it = new Iterator<Node>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && values[currentIndex] != null;
            }

            @Override
            public Node next() {
                return values[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}