package com.shoebob.dotd.systems;

public class PathfindingSystem {

    static class Node {
        enum NodeType {
            AIR,
            WALL,
            START,
            END,
            EXPLORED
        }

        public int x, y;
        public double f; // cost of node
        public double g; // distance between current node and start node
        public double h; // heuristic | estimated distance from start to end node
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

        public void clearData() {
            f = 0;
            g = 0;
            h = 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    ", type=" + type +
                    ", parent=" + parent +
                    '}';
        }
    }


    // uses the a* pathfinding
    public static boolean[][] pathfind(boolean[][] map) {
        return new boolean[1][1];
    }
}