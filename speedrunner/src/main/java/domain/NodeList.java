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

    public void add(Node object) {
        if(this.index==this.size-1) {
            increaseSize();
        }
        values[this.index] = object;
        this.index++;
    }

    public Node get(int i) throws Exception {
        if(i>this.index-1){
            throw new Exception("ArrayIndexOutOfBounds");
        }
        if(i<0){
            throw new Exception("Negative value");
        }
        return this.values[i];
    }

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