package Prueba;

import java.util.Scanner;

public class Interfaz {
	  private static final Scanner scanner = new Scanner(System.in);

	    public static int pedirInt() {
	        while (!scanner.hasNextInt()) {
	            System.out.println("Error: ingresa un número entero.");
	            scanner.next();  // Limpiar entrada incorrecta
	        }
	        return scanner.nextInt();
	    }

	    public static double pedirDouble() {
	        while (!scanner.hasNextDouble()) {
	            System.out.println("Error: ingresa un número decimal.");
	            scanner.next();  // Limpiar entrada incorrecta
	        }
	        return scanner.nextDouble();
	    }

	    public static String pedirString() {
	        return scanner.next();
	    }

	    public static boolean pedirBoolean() {
	        while (!scanner.hasNextBoolean()) {
	            System.out.println("Error: ingresa 'true' o 'false'.");
	            scanner.next();  // Limpiar entrada incorrecta
	        }
	        return scanner.nextBoolean();
	    }

	    public static float pedirFloat() {
	        while (!scanner.hasNextFloat()) {
	            System.out.println("Error: ingresa un número en formato flotante.");
	            scanner.next();  // Limpiar entrada incorrecta
	        }
	        return scanner.nextFloat();
	    }
}
