#version 330 core

// Input vertex attributes
layout(location = 0) in vec2 vertex_position;
layout(location = 1) in vec2 vertex_texcoord;
layout(location = 2) in vec2 vertex_shape;
layout(location = 3) in vec4 vertex_dest;
layout(location = 4) in vec4 vertex_color;

// Output variables
out vec2 texcoord;
flat out vec2 shape; // Keep flat qualifier here if you're using it for fragment shader
flat out vec4 color;

void main() {
    // Pass the vertex attributes to the fragment shader
    texcoord = vertex_texcoord;
    color = vertex_color;
    shape = vertex_shape;

    // Set the position of the vertex
    gl_Position = vec4(vertex_position * vertex_dest.zw + vertex_dest.xy, 0.0, 1.0);
}