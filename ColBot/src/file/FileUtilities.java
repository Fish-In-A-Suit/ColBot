package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtilities {
	
	public static void checkFile(File file) {
		if (file.canExecute()) {
			System.out.println("File " + file.getName() + " can execute.");
		} else {
			System.out.println("File " + file.getName() + " can't execute!");
		}
		
		if (file.canRead()) {
			System.out.println("File " + file.getName() + " can be read.");
		} else {
			System.out.println("File " + file.getName() + " cannot be read!");
		}
		
		if (file.canWrite()) {
			System.out.println("File " + file.getName() + " can be written to.");
		} else {
			System.out.println("File " + file.getName() + " can't be written to!");
		}
		
		if (file.exists()){
			System.out.println("File " + file.getName() + " exists.");
		} else {
			System.out.println("File " + file.getName() + " doesn't exist!");
		}
		
		if (file.isFile()) {
			System.out.println("File " + file.getName() + " is a file.");
		} else {
			System.out.println("File " + file.getName() + " isn't a file!");
		}
		
		if (file.isDirectory()) {
			System.out.println("File " + file.getName() + " is a directory!");
		} else {
			System.out.println("File " + file.getName() + " isn't a directory.");
		}
	}
	
	/**
	 * Writes the specified text to file
	 * @param file
	 * @param text
	 */
	public static void writeToFile(File file, String text){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(file, true)); //true to append
		} catch (IOException e) {
			System.err.println("Error while trying to write text to " + file.getAbsolutePath());
			e.printStackTrace();
		} 
		writer.println(text);
		writer.close();
	}
	
	/**
	 * Writes the specified text and array to the specified file
	 * @param filePath
	 * @param arr
	 */
	public static void writeToFile(String filePath, String text, int[] arr ) {
		PrintWriter writer = null;
		File file = new File(filePath);
		try {
			writer = new PrintWriter(new FileWriter(file, true)); //true to append
		} catch (IOException e) {
			System.err.println("Error while trying to write text to " + file.getAbsolutePath());
			e.printStackTrace();
		} 
		writer.println(text);
		for(int i : arr) {
			writer.print(i + " ");
		}
		writer.println("");
		writer.close();
	}

}
