package utilities;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class VBO {
	private final int vboID;
	private final int type;
	
	private VBO(int vboID, int type) {
		this.vboID = vboID;
		this.type = type;
	}
	
	public static VBO create(int type) {
		System.out.println("Creating buffer of type " + type);
		int id = glGenBuffers();
		return new VBO(id, type);
	}
	
	public void bind() {
		glBindBuffer(type, vboID);
	}
	
	public void unbind() {
		glBindBuffer(type, 0);
	}
	
	public void storeData(float[] data) {
		FloatBuffer buf = BufferUtilities.storeDataInFloatBuffer(data);
		storeData(buf);
	}
	
	public void storeData(FloatBuffer data) {
		bind();
		glBufferData(type, data, GL_STATIC_DRAW);
		unbind();
	}
	
	public void storeData(int[] data) {
		IntBuffer buf = BufferUtilities.storeDataInIntBuffer(data);
		storeData(buf);
	}
	
	public void storeData(IntBuffer data) {
		bind();
		glBufferData(type, data, GL_STATIC_DRAW);
		unbind();
	}
	
	public void delete() {
		glDeleteBuffers(vboID);
	}
	
	public int getId() {
		return vboID;
	}

}
