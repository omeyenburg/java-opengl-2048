#version 330 core
out vec4 fragColor;
in vec2 texcoord;
flat in vec2 shape;
flat in vec4 color;

uniform sampler2D texSprites;
uniform sampler2D texFont;

uniform float time;

void main() {
    float radius = length(texcoord) * (1 + time * 0.01);
    float angle = atan(-texcoord.y, texcoord.x) + 0.5 * time / radius;
    vec2 coord = vec2(cos(angle), sin(angle)) * radius;

    fragColor = texture(texSprites, coord + 0.5);
}