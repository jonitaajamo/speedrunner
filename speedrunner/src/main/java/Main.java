import domain.Node;

public class Main {
    public static void main(String args[]) {
        //only for testing purposes currently
        char[][] map = new char[10][10];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(i == 0 || j == 0 || i == map.length - 1 || j == map[i].length - 1) {
                    map[i][j] = 'T';
                    System.out.print("T");
                }else{
                    map[i][j] = '.';
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        Astar astar = new Astar(map, new Node(1, 1), new Node(8, 8));
        astar.search();
    }
}
