#version 330 core

layout(location = 0) in vec2 vertex_position;
layout(location = 1) in vec2 vertex_texcoord;
layout(location = 2) in vec2 vertex_shape;
layout(location = 3) in vec4 vertex_dest;
layout(location = 4) in vec4 vertex_color;

out vec2 texcoord;
flat out vec2 shape;
flat out vec4 color;

void main() {
    texcoord = vertex_texcoord;
    color = vertex_color;
    shape = vertex_shape;
}