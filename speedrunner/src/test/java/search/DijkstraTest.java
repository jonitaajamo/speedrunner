package search;

import domain.Node;
import org.junit.Before;
import org.junit.Test;
import search.Dijkstra;

import static org.junit.Assert.*;

public class DijkstraTest {
    private Dijkstra dijsktra;
    private char[][] map1;
    private char[][] map2;

    @Before
    public void initializeMaps(){
        this.map1 = new char[][]{
                {'T','T','T','T','T','T'},
                {'T','.','T','.','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','.','.','.','T'},
                {'T','T','T','T','T','T'},
        };
        this.map2 = new char[][]{
                {'T','T','T','T','T','T'},
                {'T','.','T','.','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','.','T','.','T'},
                {'T','T','T','T','T','T'},
        };
    }
    @Test
    public void goalIsFoundFromMap1() {
        char[][] map = this.map1;
        this.dijsktra = new Dijkstra(map, new Node(1, 1), new Node(4, 3));
        this.dijsktra.search();

        assertTrue(this.dijsktra.isGoalFound());
    }

    @Test
    public void goalIsNotFoundWhenPathBlocked1() {
        char[][] map = this.map2;

        this.dijsktra = new Dijkstra(map, new Node(1, 1), new Node(4, 4));
        this.dijsktra.search();

        assertFalse(this.dijsktra.isGoalFound());
    }

    @Test
    public void findsCorrectPath(){
        char[][] map = this.map1;

        this.dijsktra = new Dijkstra(map, new Node(1, 1), new Node(4, 3));
        this.dijsktra.search();

        String testPath = "";
        for(Node node : dijsktra.constructFinalPath()) {
            testPath += node.toString() + "; ";
        }

        assertEquals("X: 4, Y: 3; X: 4, Y: 4; X: 3, Y: 4; X: 2, Y: 4; X: 1, Y: 4; X: 1, Y: 3; X: 1, Y: 2; ", testPath);
    }
}
