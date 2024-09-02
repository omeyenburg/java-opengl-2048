package org.main.geometry;

public class Vec4 {
    private final float[] array;

    public Vec4(float a, float b, float c, float d) {
        array = new float[]{a, b, c, d};
    }

    public float x() {
        return array[0];
    }

    public float y() {
        return array[1];
    }

    public float z() {
        return array[2];
    }

    public float w() {
        return array[3];
    }

    public float r() {
        return array[0];
    }

    public float g() {
        return array[1];
    }

    public float b() {
        return array[2];
    }

    public float a() {
        return array[3];
    }
}
