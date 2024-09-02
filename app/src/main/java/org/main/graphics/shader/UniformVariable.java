package org.main.graphics.shader;

public class UniformVariable {
    private UniformValue value;
    private final int location;
    private boolean send;

    public UniformVariable(UniformValue value, int location, boolean send) {
        this.value = value;
        this.location = location;
        this.send = send;
    }

    public void update() {
        if (send) {
            value.update(location);
            send = false;
        }
    }

    public UniformValue get() {
        return value;
    }

    public void set(UniformValue value) {
        this.value = value;
        this.send = true;
    }

    public void set(float value) {
        this.value = UniformValue.FLOAT.from(value);
        this.send = true;
    }

    public void set(int value) {
        this.value = UniformValue.INT.from(value);
        this.send = true;
    }

    public void set(float v0, float v1) {
        this.value = UniformValue.VEC2.from(new float[]{v0, v1});
        this.send = true;
    }

    public void set(float v0, float v1, float v2) {
        this.value = UniformValue.VEC3.from(new float[]{v0, v1, v2});
        this.send = true;
    }

    public void set(float v0, float v1, float v2, float v3) {
        this.value = UniformValue.VEC4.from(new float[]{v0, v1, v2, v3});
        this.send = true;
    }
}
