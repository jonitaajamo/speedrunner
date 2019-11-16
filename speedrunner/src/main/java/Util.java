import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {
    public static char[][] importMap(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int xSize = 0;
        int ySize = 0;
        String line;
        boolean firstLine = true;
        char[][] map;

        // read first 4 lines which contain info about map size
        for(int i = 0; i < 4; i++) {
            line = br.readLine();
            if(i == 1) {
                ySize = Integer.parseInt(line.split(" ")[1]);
            }else if(i == 2) {
                xSize = Integer.parseInt(line.split(" ")[1]);
            }
        }

        map = new char[xSize][ySize];
        char[] points;

        for(int y = 0; y < ySize - 1; y++) {
            points = br.readLine().toCharArray();
            for(int x = 0; x < xSize - 1; x++) {
                map[x][y] = points[x];
            }
        }

        return map;
    }
}
