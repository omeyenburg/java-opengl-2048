package org.main.graphics;

import org.lwjgl.opengl.*; // imports GL11, GL20, GL30, GL33; functions are spread across these versions
import org.main.geometry.*;

public class Buffer {
    public final int vao;
    private int size;
    private int index;
    private final int ebo_indices;
    private final int vbo_vertices;
    private final int vbo_shape;
    private final int vbo_dest;
    private final int vbo_color;

    public Buffer() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        ebo_indices = GL20.glGenBuffers();
        vbo_vertices = GL20.glGenBuffers();
        vbo_shape = GL20.glGenBuffers();
        vbo_dest = GL20.glGenBuffers();
        vbo_color = GL20.glGenBuffers();

        float[] vertices = new float[]{
            -1.0f, -1.0f,  0.0f, 0.0f, // bottom-left
            -1.0f, 1.0f, 0.0f, 1.0f,   // top-left
            1.0f, 1.0f, 1.0f, 1.0f,    // top-right
            1.0f, -1.0f, 1.0f, 0.0f    // bottom-right
        };

        // Indices to lines in vertices
        int[] indices = new int[]{0, 1, 2, 0, 2, 3};

        // VBO: vertices
        GL30.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo_vertices);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertices, GL20.GL_STATIC_DRAW);

        // Vertices
        GL20.glEnableVertexAttribArray(0);
        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4 * Float.BYTES, 0L);

        // Texcoords
        GL20.glEnableVertexAttribArray(1);
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4 * Float.BYTES, 0L);

        // EBO: indices
        GL20.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, ebo_indices);
        GL20.glBufferData( GL20.GL_ELEMENT_ARRAY_BUFFER, indices, GL20.GL_STATIC_DRAW );

        // VBO: shape
        GL20.glEnableVertexAttribArray(2);
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo_shape);
        GL33.glBufferData(GL20.GL_ARRAY_BUFFER, 0, GL20.GL_STREAM_COPY);
        GL33.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 0, 0L);
        GL33.glVertexAttribDivisor(2, 1);

        // VBO: dest
        GL20.glEnableVertexAttribArray(3);
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo_dest);
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, 0, GL20.GL_STREAM_COPY);
        GL33.glVertexAttribPointer(3, 4, GL11.GL_FLOAT, false, 0, 0L);
        GL33.glVertexAttribDivisor(3, 1);

        // VBO: color
        GL20.glEnableVertexAttribArray(4);
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo_color);
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, 0, GL20.GL_STREAM_COPY);
        GL33.glVertexAttribPointer(4, 4, GL11.GL_FLOAT, false, 0, 0L);
        GL33.glVertexAttribDivisor(4, 1);
    }

    public void add_instance(Vec2 shape, Vec4 dest, Vec4 color) {

    }
}
