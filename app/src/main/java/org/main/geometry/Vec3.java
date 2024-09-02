package org.main.geometry;

public class Vec3 {
    private final float[] array;

    public Vec3(float a, float b, float c) {
        array = new float[]{a, b, c};
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

    public float z() {
        return array[2];
    }

    public void z(float value) {
        array[2] = value;
    }

    public float r() {
        return array[0];
    }

    public void r(float value) {
        array[0] = value;
    }

    public float g() {
        return array[1];
    }

    public void g(float value) {
        array[1] = value;
    }

    public float b() {
        return array[2];
    }

    public void b(float value) {
        array[2] = value;
    }

    public float[] array() {
        return this.array;
    }
}
