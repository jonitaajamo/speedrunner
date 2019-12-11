package domain;


public class Node implements Comparable<Node>{
    private int x;
    private int y;
    private int heuristic;
    private Node parent;
    private boolean hasParent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.heuristic = 0;
    }

    public Node(int x, int y, int heuristic) {
        this(x, y);
        this.heuristic = heuristic;
    }

    public Node(int x, int y, Node parent) {
        this(x, y);
        this.parent = parent;
        this.hasParent = true;
        this.heuristic = 0;
    }

    public void calculateHeuristic(Node goal) {
        this.heuristic = Math.abs(this.x - goal.getX()) + Math.abs(this.y - goal.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasParent(){
        return this.hasParent;
    }

    public Node getParent() {
        return parent;
    }

    public int getHeuristic() {
        return this.heuristic;
    }

    @Override
    public int compareTo(Node node) {
        return this.heuristic - node.heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        return "X: " + this.x + ", Y: " + this.y;
    }
}
