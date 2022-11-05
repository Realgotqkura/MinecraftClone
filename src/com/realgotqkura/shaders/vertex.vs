#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec2 textCoords;

out vec2 exColour;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;

void main()
{
    vec4 mvPos = modelViewMatrix * vec4(position, 1.0);
    gl_Position = projectionMatrix * mvPos;
    exColour = textCoords;
}