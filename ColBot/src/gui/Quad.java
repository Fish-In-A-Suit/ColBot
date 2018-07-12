package gui;

import renderEngine.Texture;
import utilities.ArrayUtilities;
import utilities.VAO;
import math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Quad {
	private VAO vao;
	private Texture texture;
	private int vertexCount;
	
	private float[] pos;
	private int[] indices;
	private float[] texCoords;
	
	private Vector3f DEFAULT_COLOUR = new Vector3f(0.93f, 0.05f, 0.77f);
	private Vector3f colour;
	
	private List<VAO> vaos = new ArrayList<>();
	private List<Texture> textures = new ArrayList<>();
	
	public Quad(float[] vPos, int[] indices, float[] texCoords, Texture texture) {
		System.out.println("[Quad.Quad]: Storing data in Quad!");
		
		System.out.println("  - data used to create the quad: ");
		System.out.println("    - vPos = " + ArrayUtilities.getArrayAsString(vPos));
		System.out.println("    - indices = " + ArrayUtilities.getArrayAsString(indices));
		System.out.println("    - texCoords = " + ArrayUtilities.getArrayAsString(texCoords));
	    
		System.out.println("[Quad.Quad]:  - creating new texture");
		this.texture = texture;
	    textures.add(texture);
	    
	    vertexCount = indices.length;
	    System.out.println("[Quad.Quad]:  - vertexCount = " + vertexCount);
	    colour = DEFAULT_COLOUR;
	    
	    System.out.println("[Quad.Quad]:  - creating new VAO");
	    vao = VAO.create();
	    vaos.add(vao);
	    
	    System.out.println("[Quad.Quad]:  - creating vao attributes");
	    vao.createAttribute(0, vPos, 3);
	    vao.createAttribute(1, texCoords, 2);
	    vao.createIndexBuffer(indices);
	}
	
	public float[] getPos() {
		return pos;
	}
	
	public int[] getIndices() {
		return indices;
	}
	
	public float[] getTexCoords() {
		return texCoords;
	}
	
	public VAO getVAO() {
		return vao;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public void cleanUp() {
		texture.cleanup();
		vao.delete();
	}

}
