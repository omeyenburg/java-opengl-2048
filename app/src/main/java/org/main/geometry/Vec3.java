package org.main.geometry;

public class Vec3 {
    private final float[] array;

    public Vec3(float a, float b, float c) {
        array = new float[]{a, b, c};
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

    public float r() {
        return array[0];
    }

    public float g() {
        return array[1];
    }

    public float b() {
        return array[2];
    }
}
