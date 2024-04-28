package com.shoebob.dotd.components;

public class PositionComponent implements Component {
    public float x = 0, y = 0; // shouldn't be public, but oh well

    public PositionComponent() {}
    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    } // not technically supposed to have a constructor, but java doesn't have structs


}
