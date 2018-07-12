 package renderEngine;

import static org.lwjgl.opengl.GL11.*;

import gui.Overlay;
import gui.OverlayManager;
import shaders.ShaderProgram;
import utilities.FileUtilities;

public class Renderer {
	private ShaderProgram shaderProgram;
	private OverlayManager overlayManager;
	
	public void render(Window window) {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		if(window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResized(false);
		}
		
		System.out.println("Binding shader program!");
		shaderProgram.bind();

		System.out.println("Rendering overlays!");
		overlayManager.render();
		
		System.out.println("Unbinding shader program!");
		shaderProgram.unbind();
	}
	
	public void init() throws Exception {
		System.out.println("[Renderer.init] Initializing renderer... ");
		
		System.out.println("[Renderer.init]: Creating new ShaderProgram...");
		shaderProgram = new ShaderProgram();
		
		overlayManager = new OverlayManager();
		
		System.out.println("[Renderer.init] Creating vertex shader... ");
		shaderProgram.createVertexShader(FileUtilities.loadResource("/shaders/vertexShader.vs"));
		
		System.out.println("[Renderer.init] Creating fragment shader... ");
		shaderProgram.createFragmentShader(FileUtilities.loadResource("/shaders/fragmentShader.fs"));
		
		System.out.println("[Renderer.init] Linking shaderProgram... ");
		shaderProgram.link();
		
		System.out.println("[Renderer.init]: Finding uniform variable locations...");
		defineUniformLocations();
		
		System.out.println("[Renderer.init]: Creating a new quad!");
		Overlay quad = new Overlay(new float[]{-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f},
				new int[]{0, 1, 2, 2, 3, 0}, new float[]{0, 0, 0, 1, 1, 1, 1, 0}, "resources/textures/defaultTexture.png");
		overlayManager.registerOverlay(quad);
	}
	
	private void defineUniformLocations() {
		//define uniforms here - if any since dealing with overlays
	}

}
