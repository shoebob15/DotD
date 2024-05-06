package com.shoebob.dotd.systems;

import java.util.HashSet;
import java.util.Set;

public class PathfindingSystem {
    private enum NodeType {
        AIR,
        WALL,
        START,
        END,
        EXPLORED
    }

    private class Node {
        public int x, y;
        public double distance; // calculated value of the distance
        public NodeType type;
        public Node parent;

        public Node(int x, int y) {
            this(x, y, NodeType.AIR);
        }

        public Node(int x, int y, NodeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", type=" + type +
                    ", parent=" + parent +
                    '}';
        }
    }


    // uses the a* pathfinding
    public static boolean[][] pathfind(Node[][] map) {
        Set<Node> unexplored = new HashSet<>(); // can not contain duplicate objects - "fancy" ArrayList

        Node startNode = null;
        Node endNode = null;
        // reset each node
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null) {
                    map[i][j].distance = Integer.MAX_VALUE;
                    unexplored.add(map[i][j]);
                }
            }
        }

        // find starting node
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && map[i][j].type == NodeType.START) {
                    startNode = map[i][j];
                }
            }
        }
        // if the start node is not found, throw an exception
        if (startNode == null) {
            throw new IllegalArgumentException("The Node map must contain 1 start node.");
        }

        // find ending node
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && map[i][j].type == NodeType.END) {
                    endNode = map[i][j];
                }
            }
        }
        // if the end node is not found, throw an exception
        if (endNode == null) {
            throw new IllegalArgumentException("The Node map must contain 1 end node.");
        }

        while (!unexplored.isEmpty()) {

        }

        startNode.distance = 0;



        return new boolean[1][1];
    }
}