package com.shoebob.dotd.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.shoebob.dotd.components.PositionComponent;
import com.shoebob.dotd.game.DotD;

import java.util.TreeMap;

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
}
