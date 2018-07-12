package utilities;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

public class VAO {
	public final int id;
	
	private List<VBO> dataVbos = new ArrayList<>();
	private VBO indexVbo;
	private int indexCount;
	
	private VAO(int id) {
		this.id = id;
	}
	
	public static VAO create() {
		int id = glGenVertexArrays();
		return new VAO(id);
	}
	
	public int getIndexCount() {
		return indexCount;
	}
	
	public void bind(int...attributes) {
		bind();
		for(int i : attributes) {
			glEnableVertexAttribArray(i);
		}
	}
	
	private void bind() {
		glBindVertexArray(id);
	}
	
	public void unbind(int...attributes) {
		for(int i : attributes) {
			glDisableVertexAttribArray(i);
		}
		unbind();
	}
	
	private void unbind() {
		glBindVertexArray(0);
	}
	
	public void bindIndexBuffer(boolean value) {
		if (value == true) {
			System.out.println("[VAO.bindIndexBuffer]: Binding ");
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVbo.getId());
			System.out.println("[VAO.bindIndexBuffer]: id of indexVbo: " + indexVbo.getId());
		} else {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		}
		
	}
	
	public void createIndexBuffer(int[] indices) {
		System.out.println("[VAO.createIndexBuffer]: Creating index buffer out of " + ArrayUtilities.getArrayAsString(indices));
		indexVbo = VBO.create(GL_ELEMENT_ARRAY_BUFFER);
		indexVbo.bind();
		indexVbo.storeData(indices);
		indexCount = indices.length;
		//indexVbo.unbind(); ???!
	}
	
	public void createAttribute(int attribute, float[] data, int attrSize) {
		VBO dataVbo = VBO.create(GL_ARRAY_BUFFER);
		dataVbo.bind();
		dataVbo.storeData(data);
		glVertexAttribPointer(attribute, attrSize, GL_FLOAT, false, 0, 0);
		dataVbos.add(dataVbo);
		dataVbo.unbind();
	}
	
	public void delete() {
		glDeleteVertexArrays(id);
		for (VBO vbo : dataVbos) {
			vbo.delete();
		}
		indexVbo.delete();
	}

}
