package util;

import domain.Node;
import domain.NodeList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Util {
    /**
     * Imports map from .map format to two dimensional array. Reads map size from the text file.
     * @param file .map file containing the map
     * @return char[][] containing the map
     * @throws IOException Error if accessing the map fails.
     */
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

    public static void exportMap(char[][] map, String algorithm) throws IOException {
        StringBuilder builder = new StringBuilder();
        FileWriter writer = new FileWriter(algorithm + ".map", true);
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                writer.write(map[y][x]);
            }
            writer.write("\r\n");
        }
        writer.close();
    }

    public static char[][] writeNodeListToMap(NodeList nodes, char[][] map) {
        for(Node node : nodes) {
            map[node.getY()][node.getX()] = 'X';
        }
        return map;
    }
}
