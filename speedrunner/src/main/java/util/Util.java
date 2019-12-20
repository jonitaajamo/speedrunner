package util;

import domain.Node;
import domain.NodeList;

import java.io.*;

public class Util {
    /**
     * Imports map from .map format to two dimensional array. Reads map size from the text file.
     * @param file .map file containing the map
     * @return char[][] containing the map
     * @throws IOException Error if accessing the map fails.
     */
    public static char[][] importMap(String file) throws IOException {
        InputStream inputStream = Util.class.getResourceAsStream("/" + file);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
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

        map = new char[ySize][xSize];
        char[] points;

        for(int y = 0; y < ySize - 1; y++) {
            points = br.readLine().toCharArray();
            for(int x = 0; x < xSize - 1; x++) {
                map[y][x] = points[x];
            }
        }

        return map;
    }

    /**
     * Exports map in to .map file two dimensional char array.
     * @param map Map to be exported
     * @param algorithm Algorithm name for naming the file
     * @throws IOException Thrown error, if error occurs during IO access
     */
    public static void exportMap(char[][] map, String algorithm) throws IOException {
        FileWriter writer = new FileWriter(algorithm + ".map", false);
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                writer.write(map[y][x]);
            }
            writer.write("\r\n");
        }
        writer.close();
    }

    /**
     * Takes node list as input and marks included nodes with 'X' on given map.
     * @param nodes List of nodes to be written on a map
     * @param map Map where the nodes will be marked
     * @return Returns the map with markings.
     */
    public static char[][] writeNodeListToMap(NodeList nodes, char[][] map) {
        for(Node node : nodes) {
            map[node.getY()][node.getX()] = 'X';
        }
        return map;
    }

    /**
     * Manual two dimensinal array copy, without manipulating the old map.
     * @param mapToCopy Map to be copied
     * @return Return the new map.
     */
    public static char[][] copyMap(char[][] mapToCopy){
        char[][] copyOfMap = new char[mapToCopy.length - 1][mapToCopy[0].length - 1];
        for(int y = 0; y < mapToCopy.length - 1; y++) {
            for(int x = 0; x < mapToCopy[y].length - 1; x++) {
                copyOfMap[y][x] = mapToCopy[y][x];
            }
        }
        return copyOfMap;
    }
}
