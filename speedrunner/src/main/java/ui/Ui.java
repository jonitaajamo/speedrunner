package ui;

import domain.Node;
import domain.NodeList;
import search.Astar;
import search.BFS;
import search.Dijkstra;
import util.Util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Ui {
    private Scanner reader;
    private char[][] map;
    private final DecimalFormat df;

    public Ui(Scanner reader) throws IOException {
        this.reader = reader;
        this.map = Util.importMap("ca_cave.map");
        this.df = new DecimalFormat("#.####");
    }

    public void start() {
        System.out.println("Speedrunner");
        String input = "";
        while(true) {
            System.out.println("Select mode:");
            System.out.println("1 Benchmark");
            System.out.println("2 Path finding");
            System.out.println("q exit");
            System.out.print("Command: ");
            input = reader.nextLine();
            if(input.toLowerCase().matches("1|benchmark")) {
                benchmark();
            }else if(input.toLowerCase().matches("2|path finding")) {
                pathFinding();
            }else if(input.toLowerCase().matches("exit|q")){
                break;
            }else{
                System.out.println("Oops, couldn't recognize the command");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Bye.");
    }


    private void benchmark() {
        System.out.println();
        System.out.println("Algorithms (separate multiple selections by space)");
        System.out.println("1 Astar / A*");
        System.out.println("2 BFS");
        System.out.println("3 Dijkstra");
        System.out.println("4 All");
        System.out.print("Algorithm(s): ");
        String algorithms = reader.nextLine();
        System.out.print("Number of iterations (default is 1): ");
        String iterations = reader.nextLine();
        System.out.println();
        System.out.println(parseBenchmarkCommands(algorithms, iterations));
    }

    private String parseBenchmarkCommands(String input, String iterations) {
        String[] commands = input.split("\\s+");
        String results = "\tavg. time\t\tavg. path length\t\tavg. node count\n\n";
        String noResults = results;
        int loops = 1;
        long benchmarkStartTime = System.nanoTime();
        if(!iterations.equals("") && iterations.length() != 0) {
            loops = Integer.parseInt(iterations);
            if(loops < 1) {
                return "Number of iterations must be greater than 0, try again";
            }
        }

        for(String command : commands) {
            if(command.toLowerCase().matches("1|astar|a\\*|4|all")){
                results += benchmarkAstar(loops);
            }
            if(command.toLowerCase().matches("2|bfs|4|əll")){
                results += benchmarkBFS(loops);
            }
            if(command.toLowerCase().matches("3|dijkstra|4|all")){
                results += benchmarkDijkstra(loops);
            }
        }
        if(results.equals(noResults)) {
            return "Number of iterations must be greater than 0, try again";
        }

        double benchmarkTime = (double) (System.nanoTime() - benchmarkStartTime) / 1000000000;
        results += "\nBenchmark took total of " + df.format(benchmarkTime) + " seconds";
        return results;
    }

    private String benchmarkDijkstra(int iterations) {
        long totalTime = 0;
        long totalPath = 0;
        long totalVisited = 0;
        Dijkstra dijkstra = null;
        for(int i = 0; i < iterations; i++) {
            dijkstra = new Dijkstra(map, new Node(122, 70), new Node(106, 224));
            long startTime = System.nanoTime();
            dijkstra.search();
            totalTime += System.nanoTime() - startTime;
            totalPath += dijkstra.finalPathLength();
            totalVisited += dijkstra.getVisitedNodes();
        }
        long averageVisited = totalVisited / iterations;
        long averagePath = totalPath / iterations;
        double aStarTime = (double) totalTime / iterations / 1000000;
        return "Dijk.\t" + df.format(aStarTime) + " ms \t\t" + averageVisited + "\t\t\t\t" + averagePath + "\n\n";
    }

    private String benchmarkBFS(int iterations) {
        long totalTime = 0;
        long totalPath = 0;
        long totalVisited = 0;
        BFS bfs = null;
        for(int i = 0; i < iterations; i++) {
            bfs = new BFS(map, new Node(122, 70), new Node(106, 224));
            long startTime = System.nanoTime();
            bfs.search();
            totalTime += System.nanoTime() - startTime;
            totalPath += bfs.finalPathLength();
            totalVisited += bfs.getVisitedNodes();
        }
        long averageVisited = totalVisited / iterations;
        long averagePath = totalPath / iterations;
        double aStarTime = (double) totalTime / iterations / 1000000;
        return "BFS \t" + df.format(aStarTime) + " ms \t\t" + averageVisited + "\t\t\t\t" + averagePath + "\n\n";
    }

    private String benchmarkAstar(int iterations) {
        long totalTime = 0;
        long totalPath = 0;
        long totalVisited = 0;
        Astar astar = null;
        for(int i = 0; i < iterations; i++) {
            astar = new Astar(map, new Node(122, 70), new Node(106, 224));
            long startTime = System.nanoTime();
            astar.search();
            totalTime += System.nanoTime() - startTime;
            totalPath += astar.finalPathLength();
            totalVisited += astar.getVisitedNodes();
        }
        long averageVisited = totalVisited / iterations;
        long averagePath = totalPath / iterations;
        double aStarTime = (double) totalTime / iterations / 1000000;
        return "Astar\t" + df.format(aStarTime) + " ms \t\t" + averageVisited + "\t\t\t\t" + averagePath + "\n\n";
    }

    private void pathFinding() {
        System.out.println();
        System.out.println("Select algorithm for finding the path");
        System.out.println("Results will be written to a file");
        System.out.println("1 Astar / A*");
        System.out.println("2 BFS");
        System.out.println("3 Dijkstra");
        System.out.print("Algorithm: ");
        String input = reader.nextLine();
        System.out.println(parsePathFindingCommands(input));
    }

    private String parsePathFindingCommands(String input) {
        String[] commands = input.split("\\s+");
        String result = "";
        for(String command : commands) {
            if(command.toLowerCase().matches("1|astar")){
                result = findAstarPath();
            }else if(command.toLowerCase().matches("2|bfs")){
                result = findBFSPath();
            }else if(command.toLowerCase().matches("3|dijkstra")){
                result = findDijkstraPath();
            }else{
                return "Your algorithm selection didn't match any algorithms";
            }
        }
        return "\n"+ result;
    }

    private String findAstarPath() {
        Astar astar = new Astar(map, new Node(122, 70), new Node(106, 224));
        astar.search();
        NodeList path = astar.constructFinalPath();
        try {
            Util.exportMap(Util.writeNodeListToMap(path, this.map), "astar");
            return "Succesfully written path to a file astar.map";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Something went wrong when writing path to a file.";
    }

    private String findBFSPath() {
        BFS bfs = new BFS(map, new Node(122, 70), new Node(106, 224));
        bfs.search();
        NodeList path = bfs.constructFinalPath();
        try {
            Util.exportMap(Util.writeNodeListToMap(path, this.map), "bfs");
            return "Succesfully written path to a file bfs.map";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Something went wrong when writing path to a file.";
    }

    private String findDijkstraPath() {
        Dijkstra dijkstra = new Dijkstra(map, new Node(122, 70), new Node(106, 224));
        dijkstra.search();
        NodeList path = dijkstra.constructFinalPath();
        try {
            Util.exportMap(Util.writeNodeListToMap(path, this.map), "dijkstra");
            return "Succesfully written path to a file dijkstra.map";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Something went wrong when writing path to a file.";
    }
}

