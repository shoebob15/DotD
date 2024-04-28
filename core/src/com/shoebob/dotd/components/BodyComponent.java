package com.shoebob.dotd.components;

public class BodyComponent implements Component {
    public BodyComponent() {}
    public BodyComponent(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float width = 0;
    public float height = 0;
}
