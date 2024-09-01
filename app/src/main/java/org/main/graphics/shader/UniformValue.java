package org.main.graphics.shader;

import static org.lwjgl.opengl.GL20.*;

public enum UniformValue {
    INT {
        @Override
        public void update(int location) {
            glUniform1i(location, (Integer) value);
        }
    },
    FLOAT {
        @Override
        public void update(int location) {
            glUniform1f(location, (Float) value);
        }
    },
    VEC2 {
        @Override
        public void update(int location) {
            float[] v = (float[]) value;
            glUniform2f(location, v[0], v[1]);
        }
    },
    VEC3 {
        @Override
        public void update(int location) {
            float[] v = (float[]) value;
            glUniform3f(location, v[0], v[1], v[2]);
        }
    },
    VEC4 {
        @Override
        public void update(int location) {
            float[] v = (float[]) value;
            glUniform4f(location, v[0], v[1], v[2], v[3]);
        }
    };

    // Field to hold the value
    protected Object value;

    // Method to set the value and return the enum constant
    public UniformValue from(Object value) {
        this.value = value;
        return this;
    }

    // Abstract method to update the uniform
    public abstract void update(int location);
}