package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeListTest {
    private NodeList list;


    @Before
    public void beforeTest() {
        this.list = new NodeList();
    }

    @Test
    public void nodesCanBeAddedToList() throws Exception {
        this.list.add(new Node(1, 2));
        assertEquals(1, this.list.get(0).getX());
    }

    @Test
    public void listCanBeIterated() {
        this.list.add(new Node(1,1));
        this.list.add(new Node(2,2));
        this.list.add(new Node(3,3));

        String testString = "";
        for(Node node : this.list) {
            testString += node.getX();
        }

        assertEquals("123", testString);
    }

    @Test
    public void nodesCanBeRemovedFromList() throws Exception {
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.remove(1);
        assertEquals(1, this.list.size());
    }

    @Test
    public void moreThanEightNodesCanBeAddedToList() throws Exception {
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(1, 2));
        this.list.add(new Node(8, 9));

        assertEquals(8, this.list.get(8).getX());
    }
}
