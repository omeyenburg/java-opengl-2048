#version 330 core

out vec4 fragColor;
in vec2 texcoord;
flat in vec2 shape;
flat in vec4 color;

void main() {
    fragColor = vec4(1.0, 0.0, 1.0, 0.8);
}