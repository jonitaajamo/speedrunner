import domain.Node;

import java.util.*;

public class Dijkstra {
    private char[][] map;
    private int[][] dist;
    private Node start;
    private Node goal;
    private boolean goalFound;

    /**
     * Dijsktra constructor.
     * @param map map of characters that represent the area that can be moved on
     * @param start Node where the search starts
     * @param goal Node what the algorithms tries to find
     */
    public Dijkstra(char[][] map, Node start, Node goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
        this.goalFound = false;
        this.dist = new int[map.length][map[0].length];
    }

    /**
     * Search method contains the Dijkstra algorithm itself.
     * Uses java implementations of data structures currently
     */
    public void search() {
        formatHelperMap();
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(start);

        Set<Node> visited = new HashSet();

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if(visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            if(currentNode.equals(this.goal)) {
                System.out.println("Goal found");
                this.goalFound = true;
                break;
            }

            List<Node> neighbors = getNeighbors(currentNode);
            for(Node neighbor : neighbors) {
                int distance = this.dist[neighbor.getX()][neighbor.getY()];
                int newDist = distance + 1;

                if(newDist < distance) {
                    this.dist[neighbor.getX()][neighbor.getY()] = newDist;
                    queue.add(new Node(neighbor.getX(), neighbor.getY(), newDist));
                }
            }

        }
    }

    /**
     * Finds node's neighbors and checks if they can be moved on.
     * @param node Node whose neighbor's are checked
     * @return List of neighbors
     */
    public List<Node> getNeighbors(Node node) {
        int x = node.getX();
        int y = node.getY();

        ArrayList<Node> neighbors = new ArrayList<>();
        if(this.map[x-1][y] == '.') {
            neighbors.add(new Node(x-1, y));
        }
        if(this.map[x+1][y] == '.') {
            neighbors.add(new Node(x+1,y));
        }
        if(this.map[x][y-1] == '.') {
            neighbors.add(new Node(x,y-1));
        }
        if(this.map[x][y+1] == '.') {
            neighbors.add(new Node(x,y+1));
        }
        if(this.map[x+1][y+1] == '.') {
            neighbors.add(new Node(x+1,y+1));
        }
        if(this.map[x-1][y+1] == '.') {
            neighbors.add(new Node(x-1,y+1));
        }
        if(this.map[x-1][y-1] == '.') {
            neighbors.add(new Node(x-1,y-1));
        }
        if(this.map[x+1][y-1] == '.') {
            neighbors.add(new Node(x+1,y-1));
        }

        return neighbors;
    }

    /**
     * Formats distance map with max int values
     */
    public void formatHelperMap() {
        for(int i = 0; i < this.dist.length; i++) {
            for(int j = 0; j < this.dist[0].length; j++) {
                this.dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public boolean isGoalFound() {
        return this.goalFound;
    }
}
