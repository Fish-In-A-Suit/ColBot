#version 330

layout (location = 0) in vec3 inVertexPosition;
layout (location = 0) in vec2 textureCoordinate;

out vec2 outTexCoord;

void main() {
	gl_Position = vec4(inVertexPosition, 1.0);
	outTexCoord = textureCoordinate;
}