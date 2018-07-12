package gui;

import math.Vector3f;
import renderEngine.Texture;
import utilities.ArrayUtilities;

public class Overlay {
	private Quad quad;
	private Vector3f position;
	private float scale;
	
	public Overlay(float[] vPos, int[] indices, float[] texCoords, String texturePath) {
		System.out.println("[Overlay.Overlay]: Creating a new overlay!");
		Texture texture = null;
		try {
			texture = new Texture(texturePath);
			System.out.println("  - creating a new Texture instance from " + texturePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		quad = new Quad(vPos, indices, texCoords, texture);
		System.out.println("[Overlay.Overlay]: String representation of quad: " + quad.toString());
		
		position = new Vector3f(0.5f, 0.5f, 0f);
		scale = 1.0f;
	}
	
	/**
	 * Changes the texture of the quad by drawing a new quad with a different texture ... optimize
	 * @param newTexture
	 */
	public void draw(Texture newTexture) {
		Quad newQuad = new Quad(quad.getPos(), quad.getIndices(), quad.getTexCoords(), newTexture);
		//replace quad with newQuad in OverlayManager
	}
	
	public Quad getQuad() {
		return quad;
	}
	
	public void cleanUp() {
		quad.cleanUp();
	}

}
