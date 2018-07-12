package gui;

import java.util.ArrayList;
import java.util.List;

import utilities.VAO;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

/**
 * This class holds references to all current overlays and their locations + r
 * @author Aljoša
 *
 */
public class OverlayManager {
	private List<Overlay> overlays;
	
	public OverlayManager() {
		System.out.println("[OverlayManager.init]: Initializing overlay manager!");
		overlays = new ArrayList<>();
	}
	
	public void registerOverlay(Overlay overlay){
		overlays.add(overlay);
	}
	
	public List<Overlay> getOverlays() {
		return overlays;
	}
	
	public void render() {
		for (int i = 0; i < overlays.size(); i++) {
			System.out.println("Size of overlays: " + overlays.size());
			Overlay overlay = overlays.get(i);
			
			System.out.println("String representation of quad: " + overlay.getQuad().toString());
			
		    if(overlay.getQuad().getTexture() != null) {
		    	glActiveTexture(GL_TEXTURE0);
				glBindTexture(GL_TEXTURE_2D, overlay.getQuad().getTexture().getId());
		    }
		    
		    System.out.println("Getting vao of overlay");
		    VAO vao = overlay.getQuad().getVAO();
		    System.out.println("Binding attrib location 0 and 1");
		    vao.bind(0, 1);
		    vao.bindIndexBuffer(true);
		    System.out.println("Binding index buffer");
		    //vao.bindIndexBuffer(true);
		    System.out.println("Drawing with the following data:");
		    System.out.println("  - vertex count = " + overlay.getQuad().getVertexCount());
		    glDrawElements(GL_TRIANGLES, overlay.getQuad().getVertexCount(), GL_UNSIGNED_INT, 0);
		    System.out.println("unbinding attrib locations 0 and 1");
		    vao.unbind(1, 0);
		    glBindTexture(GL_TEXTURE_2D, 0);
		}
	}

}
