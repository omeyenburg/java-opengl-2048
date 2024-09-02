package org.main.graphics;

import org.lwjgl.opengl.*; // imports GL11, GL20, GL30, GL33; functions are spread across these versions

public class Buffer {
    public int vao;
    private int size;
    private int index;
    private int ebo_indices;
    private int vbo_vertices;
    private int vbo_shape;
    private int vbo_dest;
    private int vbo_color;

    public Buffer() {}

    public void add_instance(Vec2 shape, Vec4 dest, Vec4 color) {}
}
