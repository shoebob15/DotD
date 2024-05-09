package com.shoebob.dotd.systems;

import com.shoebob.dotd.util.Util;

import java.util.HashSet;
import java.util.Set;

public class PathfindingSystem {
    public enum NodeType {
        AIR,
        WALL,
        START,
        END
    }

    public static class Node {
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


    // uses the a* pathfinding algorithm
    public static Util.Directions[] pathfind(Node[][] map) {
        Set<Node> explored = new HashSet<>();
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

        Node[] adjacentNodes = new Node[0];
        while (!unexplored.isEmpty()) {
            Node currentNode = null;
            double minDistance = Double.MAX_VALUE;

            // Find the node with the smallest distance in the unexplored set
            for (Node node : unexplored) {
                if (node.distance < minDistance) {
                    currentNode = node;
                    minDistance = node.distance;
                }
            }

            if (currentNode == null) {
                break; // No path found
            }

            if (currentNode == endNode) {
                // Path found, construct and return the path
                return constructPath(startNode, endNode);
            }

            unexplored.remove(currentNode);
            explored.add(currentNode);

            // Get neighboring nodes - top, right, bottom, left
            adjacentNodes = new Node[4];

            // make the algorithm ignore any nodes
            if (currentNode.y == map.length - 1)
                adjacentNodes[0] = new Node(currentNode.x, currentNode.y + 1, NodeType.WALL);
            else adjacentNodes[0] = map[currentNode.x][currentNode.y + 1];

            if (currentNode.x == map.length - 1)
                adjacentNodes[1] = new Node(currentNode.x + 1, currentNode.y, NodeType.WALL);
            else adjacentNodes[1] = map[currentNode.x + 1][currentNode.y];

            if (currentNode.y == 0)
                adjacentNodes[2] = new Node(currentNode.x, currentNode.y - 1, NodeType.WALL);
            else adjacentNodes[2] = map[currentNode.x][currentNode.y - 1];

            if (currentNode.x == 0)
                adjacentNodes[3] = new Node(currentNode.x - 1, currentNode.y, NodeType.WALL);
            else adjacentNodes[3] = map[currentNode.x - 1][currentNode.y];


            for (Node neighbor : adjacentNodes) {
                if (neighbor == null || explored.contains(neighbor) || neighbor.type == NodeType.WALL) {
                    continue; // Skip walls or already explored nodes
                }

                double tentativeDistance = currentNode.distance + 1; // Assuming each step has a distance of 1

                if (tentativeDistance < neighbor.distance) {
                    neighbor.distance = tentativeDistance;
                    neighbor.parent = currentNode;
                    if (!unexplored.contains(neighbor)) {
                        unexplored.add(neighbor);
                    }
                }
            }
        }
        // No path found
        return null;
    }

    private static Util.Directions[] constructPath(Node startNode, Node endNode) {
        // Trace back from end node to start node
        Node currentNode = endNode;
        int length = (int) Math.ceil(currentNode.distance); // Length of the path

        if (length == Integer.MAX_VALUE) {
            return null;
        }

        Util.Directions[] path = new Util.Directions[length];
        int index = length - 1;

        while (currentNode != startNode) {
            Node parent = currentNode.parent;
            if (parent.x < currentNode.x) {
                path[index--] = Util.Directions.WEST;
            } else if (parent.x > currentNode.x) {
                path[index--] = Util.Directions.EAST;
            } else if (parent.y < currentNode.y) {
                path[index--] = Util.Directions.NORTH;
            } else if (parent.y > currentNode.y) {
                path[index--] = Util.Directions.SOUTH;
            }
            currentNode = parent;
        }

        return path;
    }
}