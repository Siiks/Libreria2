

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Marcelo {

	public static void main(String[] args) {

		// Primero voy a leer los ficheros de texto
		try {
			// Variables para controlar el tiempo que tarda en ejecutar
			long init, time;
			init = System.currentTimeMillis();

			File fichPalabras = new File("palabras.txt");
			File fichSopa = new File("sopa.txt");

			BufferedReader br1 = new BufferedReader(new FileReader(fichPalabras));
			BufferedReader br2 = new BufferedReader(new FileReader(fichSopa)); // ?
			BufferedReader br3 = new BufferedReader(new FileReader(fichSopa));

			// Variable para almacenar las palabras
			List<String> palabras = new ArrayList<>();

			// Lectura fichero palabras (SOLUCION)
			String linea = br1.readLine();

			while (linea != null) {
				palabras.add(linea);
				linea = br1.readLine();
			}

			int filas = 0, columnas = 0;

			// Lectura fichero sopa de letras
			linea = br2.readLine();
			if (linea == null)
				System.out.println("Error al leer del archivo " + fichSopa.getName());

			// Teniendo la primera linea, la parto y cuento columnas
			columnas = linea.split(" ").length;

			// Ahora cuento filas
			while (linea != null) {
				filas++;
				linea = br2.readLine();
			} // Una vez salgo de aqui br2 ya es null

			// Variable para almacenar la sopa de letras
			String[][] sopaLetras = new String[filas][columnas];

			// Variables para iterar sobre la matriz
			int i = 0, j = 0;

			linea = br3.readLine();

			// Ahora creada la variable, toca almacenar en ella la sopa
			while (linea != null) {
				while (j < columnas) {
					sopaLetras[i][j] = linea.split(" ")[j];
					j++;
				}
				i++;
				j = 0;

				linea = br3.readLine();
			}

			// Sopa de letras almacenado

			for (String palabra : palabras) {
				System.out.println(palabra + " " + buscaPalabras(sopaLetras, filas, columnas, palabra) + " veces");
			}

			time = System.currentTimeMillis() - init;

			System.out.println("Tiempo tardado: " + time + " ms");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Busca la primera aparación de la palabra
	// 1
	public static int buscarHorizontalDerecha(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) { // Iteramos sobre las //
																							// filas
			for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& palabras.length() + indiceColumna - 1 < columnas) {
					String palabraEncontradaEnSopa = "";

					for (int altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altColumna++, tamPalabra++) {
						palabraEncontradaEnSopa += sopaDeLetras[indiceFila][altColumna];
					}

					if (palabraEncontradaEnSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("1.Palabra: " + palabraEncontradaEnSopa + " en i: " + indiceFila + " j: "
//								+ indiceColumna);

						palabraEncontrada++;
					}
				}
			}
		}

		return palabraEncontrada;
	}

	// 2
	public static int buscarHorizontalIzquierda(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) { // Iteramos sobre
																							// las filas
			for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& indiceColumna - palabras.length() + 1 >= 0) {
					String palabraEncontradaEnSopa = "";

					for (int altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altColumna--, tamPalabra++) {

						palabraEncontradaEnSopa += sopaDeLetras[indiceFila][altColumna];
					}

					if (palabraEncontradaEnSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("2.Palabra: " + palabras + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;

					}
				}
			}
		}

		return palabraEncontrada;
	}

	// 3
	public static int buscarVerticalArriba(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;
		// M linea 6
		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) {
			for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& indiceFila + 1 >= palabras.length()) {
					String palabraEncontradaEnSopa = "";

					for (int altFila = indiceFila, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila--, tamPalabra++) {
						palabraEncontradaEnSopa += sopaDeLetras[altFila][indiceColumna]; // Construyo la palabra
																							// vista en la sopa de
																							// letras
					}

					if (palabraEncontradaEnSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("3.Palabra: " + palabras + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;

					}
				}
			}
		}
		return palabraEncontrada;
	}

	// 4
	public static int buscarVerticalAbajo(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) {
			for (int indiceColumna = 0; indiceColumna < columnas ; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& palabras.length() + indiceFila <= filas) {
					String palabraEncontradaEnSopa = "";

					for (int altFila = indiceFila, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila++, tamPalabra++) {
						palabraEncontradaEnSopa += sopaDeLetras[altFila][indiceColumna];
					}

					if (palabraEncontradaEnSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("4.Palabra: " + palabraEncontradaEnSopa + " en i: " + indiceFila + " j: "
//								+ indiceColumna);

						palabraEncontrada++;
					}

				}
			}
		}

		return palabraEncontrada;
	}

	// 5
	public static int buscarDiagonalDerechaArriba(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) {
			for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& palabras.length() + indiceColumna - 1 < columnas 
						&& indiceFila + 1 >= palabras.length()) {
					String palabraSopa = "";
					
					for (int altFila = indiceFila, altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila--, altColumna++, tamPalabra++) {
						palabraSopa += sopaDeLetras[altFila][altColumna];
					}

					if (palabraSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("5.Palabra: " + palabras + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;
					}
				}
			}
		}
		return palabraEncontrada;
	}

	// 6
	public static int buscarDiagonalDerechaAbajo(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas && !isVacia(palabras); indiceFila++) {
			for (int indiceColumna = 0; indiceColumna < columnas; indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& palabras.length() + indiceColumna - 1 < columnas
						&& palabras.length() + indiceFila <= filas) {
					
					String palabraSopa = "";

					for (int altFila = indiceFila, altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila++, altColumna++, tamPalabra++) {
						palabraSopa += sopaDeLetras[altFila][altColumna]; // Construyo la palabra vista en la sopa
																			// de letras
					}

					if (palabraSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("6.Palabra: " + palabras + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;
					}
				}
			}
		}

		return palabraEncontrada;
	}

	// 7
	public static int buscarDiagonalIzquierdaArriba(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 1; indiceFila < filas; indiceFila++) {
			for (int indiceColumna = 1; indiceColumna < columnas && !isVacia(palabras); indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& indiceColumna - palabras.length() + 1 >= 0
						&& indiceFila + 1 >= palabras.length()) {
					String palabraSopa = "";

					// fila + 1 >= tamPalabra
					// columna - tamPalabra + 1 >= 0
					
					for (int altFila = indiceFila, altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila--, altColumna--, tamPalabra++) {
						palabraSopa += sopaDeLetras[altFila][altColumna]; // Construyo la palabra vista en la sopa
								// REVISAR AQUI											// de letras
					}

					if (palabraSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("7.Palabra: " + palabras + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;
					}
				}
			}
		}
		return palabraEncontrada;
	}

	// 8
	public static int buscarDiagonalIzquierdaAbajo(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int palabraEncontrada = 0;

		for (int indiceFila = 0; indiceFila < filas; indiceFila++) {
			for (int indiceColumna = 1; indiceColumna < columnas && !isVacia(palabras); indiceColumna++) {
				if (sopaDeLetras[indiceFila][indiceColumna].startsWith(palabras.substring(0, 1))
						&& indiceColumna - palabras.length() + 1 >= 0
						&& indiceFila + palabras.length() <= filas) {
					String palabraSopa = "";

					// 
					
					for (int altFila = indiceFila, altColumna = indiceColumna, tamPalabra = 0; tamPalabra < palabras
							.length(); altFila++, altColumna--, tamPalabra++) {
						palabraSopa += sopaDeLetras[altFila][altColumna]; // Construyo la palabra vista en la sopa
																			// de letras
					}

					if (palabraSopa.equalsIgnoreCase(palabras)) {
//						System.out.println("8.Palabra: " + palabraSopa + " en i: " + indiceFila + " j: " + indiceColumna);

						palabraEncontrada++;
					}
				}
			}
		}

		return palabraEncontrada;
	}

	public static int buscaPalabras(String[][] sopaDeLetras, int filas, int columnas, String palabras) {
		int solucion = 0;

		solucion += buscarHorizontalDerecha(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarHorizontalIzquierda(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarVerticalArriba(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarVerticalAbajo(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarDiagonalDerechaArriba(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarDiagonalDerechaAbajo(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarDiagonalIzquierdaArriba(sopaDeLetras, filas, columnas, palabras);
		solucion += buscarDiagonalIzquierdaAbajo(sopaDeLetras, filas, columnas, palabras);

		return solucion;
	}

	public static boolean isVacia(String cadena) {
		return cadena.equals("") || cadena == null;
	}
}
