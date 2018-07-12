package utilities;

public class ArrayUtilities {
	public static void displayArray(String[] array) {
		int i = 0;
		for (String s:array) {
			System.out.print("[" + i + "]: " + s + " ");
			i++;
		}
	}
	
	public static void displayArray(int[] array) {
		int i = 0;
		for (int j:array) {
			System.out.print("[" + i + "]: " + +j + " ");
			i++;
		}
	}
	
	public static void displayArray(float[] array) {
		float f = 0;
		for (float j:array) {
			System.out.print("[" + f + "]: " + +j + " ");
			f++;
		}
	}
	
	public static String getArrayAsString(float[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (float j : array) {
			sb.append("" + j + " | ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static String getArrayAsString(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i : array) {
			sb.append("" + i + " | ");
		}
		sb.append("]");
		return sb.toString();
	}

}
