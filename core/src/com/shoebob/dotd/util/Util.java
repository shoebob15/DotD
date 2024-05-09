package com.shoebob.dotd.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.game.DotD;
import com.shoebob.dotd.systems.PathfindingSystem;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

// idc that this is bad practice
public class Util {
    // returns a world-space coordinate given a screen-coordinate
    public static PositionComponent screenToWorld(PositionComponent pos, DotD game) {
        Vector3 tmp = game.camera.unproject(new Vector3(new Vector2(pos.x, pos.y), 0));
        return new PositionComponent(tmp.x, tmp.y);
    }

    public static PositionComponent screenToWorld(int x, int y, DotD game) {
        return screenToWorld(new PositionComponent(x, y), game);
    }

    public static PathfindingSystem.Node[][] tiledMapToNodeMap(TiledMap map) {
        TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer)map.getLayers().get("walls");
        TiledMapTileLayer nonCollisionLayer = (TiledMapTileLayer)map.getLayers().get("base");
        PathfindingSystem.Node[][] nodeMap = new PathfindingSystem.Node[nonCollisionLayer.getHeight()][nonCollisionLayer.getWidth()];

        for (int r = 0; r < collisionObjectLayer.getHeight(); r++) {
            for (int c = 0; c < collisionObjectLayer.getWidth(); c++) {
                if (collisionObjectLayer.getCell(r, c) != null) {
                    nodeMap[r][c] = new PathfindingSystem.Node(r, c, PathfindingSystem.NodeType.WALL);
                }
            }
        }

        for (int r = 0; r < nonCollisionLayer.getHeight(); r++) {
            for (int c = 0; c < nonCollisionLayer.getWidth(); c++) {
                if (nonCollisionLayer.getCell(r, c) != null) {
                    nodeMap[r][c] = new PathfindingSystem.Node(r, c, PathfindingSystem.NodeType.AIR);
                }
            }
        }

        return nodeMap;
    }

    public static String getRomanNumber(int number) {
        return join("", nCopies(number, "I"))
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }


    public enum Directions {
        NORTH(new Vector2(0, 1)),
        EAST(new Vector2(1, 0)),
        SOUTH(new Vector2(0, -1)),
        WEST(new Vector2(-1, 0));

        public final Vector2 vec;
        Directions(Vector2 vec) {
            this.vec = vec;
        }
    }
}
