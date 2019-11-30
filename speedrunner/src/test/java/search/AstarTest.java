package search;

import domain.Node;

import org.junit.Test;
import search.Astar;

import static org.junit.Assert.*;

public class AstarTest {
    private Astar astar;

    @Test
    public void goalIsFoundFromMap1() {
        char[][] map = {
                {'T','T','T','T','T','T'},
                {'T','.','T','.','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','.','.','.','T'},
                {'T','T','T','T','T','T'},
        };
        this.astar = new Astar(map, new Node(1, 1), new Node(4, 3));
        this.astar.search();

        assertTrue(this.astar.isGoalFound());
    }

    @Test
    public void goalIsNotFoundWhenPathBlocked1() {
        char[][] map = {
                {'T','T','T','T','T','T'},
                {'T','.','T','.','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','T','T','.','T'},
                {'T','.','.','T','.','T'},
                {'T','T','T','T','T','T'},
        };

        this.astar = new Astar(map, new Node(1, 1), new Node(4, 4));
        this.astar.search();

        assertFalse(this.astar.isGoalFound());
    }
}
