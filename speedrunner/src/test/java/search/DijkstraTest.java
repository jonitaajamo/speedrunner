package search;

import domain.Node;
import org.junit.Test;
import search.Dijkstra;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DijkstraTest {
    private Dijkstra dijsktra;

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
        this.dijsktra = new Dijkstra(map, new Node(1, 1), new Node(4, 3));
        this.dijsktra.search();

        assertTrue(this.dijsktra.isGoalFound());
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

        this.dijsktra = new Dijkstra(map, new Node(1, 1), new Node(4, 4));
        this.dijsktra.search();

        assertFalse(this.dijsktra.isGoalFound());
    }
}
