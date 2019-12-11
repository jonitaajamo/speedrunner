package search;

import domain.Node;
import domain.NodeList;

public class AlgUtil {
    public AlgUtil(){}
    /**
     * Finds node's neighbors and checks if they can be moved on.
     * @param node Node whose neighbor's are checked
     * @return List of neighbors
     */
    public static NodeList getNeighbors(Node node, char[][] map) {
        int x = node.getX();
        int y = node.getY();

        NodeList neighbors = new NodeList();

        if(map[y][x-1] == '.') {
            neighbors.add(new Node(x-1,y, node));
        }
        if(map[y-1][x] == '.') {
            neighbors.add(new Node(x, y-1, node));
        }
        if(map[y][x+1] == '.') {
            neighbors.add(new Node(x+1,y, node));
        }
        if(map[y+1][x] == '.') {
            neighbors.add(new Node(x,y+1, node));
        }


        return neighbors;
    }
}
