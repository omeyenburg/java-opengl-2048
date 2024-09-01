package org.main.graphics.shader;

public class UniformVariable {
    private UniformValue value;
    private int location;
    private boolean send;

    public UniformVariable(UniformValue value, int location, boolean send) {
        this.value = UniformValue.INT.from(3);
        this.location = location;
        this.send = send;
    }

    public void update() {
        if (send) {
            value.update(location);
            send = false;
        }
    }

    public void set(UniformValue value) {
        this.value = value;
        this.send = true;
    }

    public UniformValue get() {
        return value;
    }
}
