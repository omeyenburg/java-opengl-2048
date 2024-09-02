package org.main.geometry;

public class Vec2 {
    private final float[] array;

    public Vec2(float a, float b) {
        array = new float[]{a, b};
    }

    public float x() {
        return array[0];
    }

    public void x(float value) {
        array[0] = value;
    }

    public float y() {
        return array[1];
    }

    public void y(float value) {
        array[1] = value;
    }
}
