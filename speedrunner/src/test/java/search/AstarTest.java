package search;

import domain.Node;

import org.junit.Before;
import org.junit.Test;
import search.Astar;

import static org.junit.Assert.*;

public class AstarTest {
    private Astar astar;
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
        this.astar = new Astar(this.map1, new Node(1, 1), new Node(4, 3));
        this.astar.search();

        assertTrue(this.astar.isGoalFound());
    }

    @Test
    public void goalIsNotFoundWhenPathBlocked1() {

        this.astar = new Astar(this.map2, new Node(1, 1), new Node(4, 4));
        this.astar.search();

        assertFalse(this.astar.isGoalFound());
    }

    @Test
    public void findsCorrectPath(){
        this.astar = new Astar(this.map1, new Node(1, 1), new Node(4, 3));
        this.astar.search();

        String testPath = "";
        for(Node node : astar.constructFinalPath()) {
            testPath += node.toString() + "; ";
        }

        assertEquals("X: 4, Y: 3; X: 4, Y: 4; X: 3, Y: 4; X: 2, Y: 4; X: 1, Y: 4; X: 1, Y: 3; X: 1, Y: 2; ", testPath);
    }
}
