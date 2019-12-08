package search;

import domain.Node;
import main.java.search.BFS;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BFSTest {
    private BFS bfs;
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
        this.bfs = new BFS(this.map1, new Node(1, 1), new Node(4, 3));
        this.bfs.search();

        assertTrue(this.bfs.isGoalFound());
    }

    @Test
    public void goalIsNotFoundWhenPathBlocked1() {
        this.bfs = new BFS(this.map2, new Node(1, 1), new Node(4, 4));
        this.bfs.search();

        assertFalse(this.bfs.isGoalFound());
    }

    @Test
    public void findsCorrectPath(){
        this.bfs = new BFS(this.map1, new Node(1, 1), new Node(4, 3));
        this.bfs.search();

        String testPath = "";
        for(Node node : bfs.constructFinalPath()) {
            testPath += node.toString() + "; ";
        }

        assertEquals("X: 4, Y: 3; X: 4, Y: 2; X: 3, Y: 1; X: 2, Y: 1; ", testPath);
    }
}
