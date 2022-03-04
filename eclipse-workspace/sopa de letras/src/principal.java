import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> sopa=leerSopa("sopa.txt");
		List<String> lista=leerLista("palabras.txt");
		long empezar=System.currentTimeMillis();
		for (String palabra : lista) {
			int cantidadEncontrada=0;
			int i=0;
			int j=0;
			int lineaSize=sopa.get(0).size();
			while(i<sopa.size()) {
				List<String> lineaActual=sopa.get(i);
				while(j<lineaSize) {
					int posPalabra=0;
					if(lineaActual.get(j).equals(Character.toString(palabra.charAt(posPalabra)))) {
						cantidadEncontrada += comprobarDiagonalAbajoDerecha(i,j,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalArribaDerecha(i,j,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalAbajoIzquierda(i,j,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalArribaIzquierda(i,j,palabra,sopa);
						cantidadEncontrada += comprobarHorizontalDerecha(i,j,palabra,sopa);
						cantidadEncontrada += comprobarHorizontalIzquierda(i,j,palabra,sopa);
						cantidadEncontrada += comprobarVerticalArriba(i,j,palabra,sopa);
						cantidadEncontrada += comprobarVerticalAbajo(i,j,palabra,sopa);
					}
					j++;
				}
				j=0;
				i++;
			}
			if(cantidadEncontrada==1) {
				System.out.println(palabra+": "+cantidadEncontrada+" vez");
			}
			else {
				System.out.println(palabra+": "+cantidadEncontrada+" veces");
			}
			
		}
		long terminar=System.currentTimeMillis();
		long tiempo=terminar-empezar;
		System.out.println("Tiempo ejecucion: "+tiempo +"ms");
		
	}
	
	public static int comprobarDiagonalArribaDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		if(linea+1<palabraSize) {
			//La palabra no entra hacia arriba
			return 0;
		}
		int lineaSize=sopa.get(0).size();
		if(posActual+palabraSize>lineaSize) {
			//La palabra no entra hacia el lateral
			return 0;
		}
		else {
			//La palabra entra
			int i=1;
			linea--;
			posActual++;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea--;
					posActual++;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;
			
		}
		
	}
	public static int comprobarDiagonalAbajoDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		int lineas=sopa.size();
		if(linea+palabraSize>lineas) {
			//La palabra no entra hacia abajo
			return 0;
		}
		
		int lineaSize=sopa.get(0).size();
		if(posActual+palabraSize>lineaSize) {
			//La palabra no entra hacia la derecha
			return 0;
		}
		else {
			//la palabra entra
			int i=1;
			linea++;
			posActual++;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea++;
					posActual++;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;
		}
		
	}
	public static int comprobarDiagonalArribaIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		if(linea+1<palabraSize) {
			//La palabra no entra hacia arriba
			return 0;
		}
		
		if(posActual-palabraSize+1<0) {
			//la palabra no entra hacia la izquierda
			return 0;
		}
		else {
			//la palabra entra
			int i=1;
			linea--;
			posActual--;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea--;
					posActual--;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;
		}
	}
	public static int comprobarDiagonalAbajoIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		int lineas=sopa.size();
		if(linea+palabraSize>lineas) {
			//La palabra no entra hacia abajo
			return 0;
		}
		
		if(posActual-palabraSize+1<0) {
			//la palabra no entra hacia la izquierda
			return 0;
		}
		else {
			//la palabra entra
			int i=1;
			linea++;
			posActual--;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea++;
					posActual--;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;	
		}
		
	}
	public static int comprobarHorizontalDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		int lineaSize=sopa.get(0).size();
		if(posActual+palabraSize>lineaSize) {
			//La palabra no entra hacia la derecha
			return 0;
		}
		else {
			//La palabra entra
			int i=1;
			posActual++;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					posActual++;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;
		}
		
	}
	public static int comprobarHorizontalIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		if(posActual-palabraSize+1<0) {
			//la palabra no entra hacia la izquierda
			return 0;
		}
		else {
			//la palabra entra
			int i=1;
			posActual--;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					posActual--;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;
		}
	}
	public static int comprobarVerticalArriba(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		if(linea+1<palabraSize) {
			//La palabra no entra hacia arriba
			return 0;
		}
		else {
			//La palabra entra
			int i=1;
			linea--;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea--;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;	
		}
		
	}
	public static int comprobarVerticalAbajo(int linea, int posActual,String palabra, List<List<String>> sopa) {
		int palabraSize=palabra.length();
		int lineas=sopa.size();
		if(linea+palabraSize>lineas) {
			//La palabra no entra hacia abajo
			return 0;
		}
		else {
			//La palabra entra
			int i=1;
			linea++;
			while(i<palabraSize) {
				if(sopa.get(linea).get(posActual).equals(Character.toString(palabra.charAt(i)))) {
					linea++;
					i++;
				}
				else {
					//No es la palabra que buscamos
					return 0;
				}
			}
			return 1;	
		}
		
	}
	
	public static List<List<String>> leerSopa(String rutaFichero){
		File sopa = new File (rutaFichero);
		FileReader fr;
		List<List<String>> resultado=new ArrayList<List<String>>();
		try {
			fr = new FileReader (sopa);
			BufferedReader br = new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				int size=linea.length();
				int i=0;
				List<String> caracteresLinea= new ArrayList<String>();
				while(i<size) {
					String caracter =""+linea.charAt(i);
					if(!caracter.equals(" ")) {
						caracteresLinea.add(caracter.toUpperCase());
					}
					i++;
				}
				resultado.add(caracteresLinea);
				linea=br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
		
	}
	
	public static List<String> leerLista(String rutaLista){
		List<String> resultado=new ArrayList<String>();
		File lista = new File (rutaLista);
		FileReader fr;
		try {
			fr = new FileReader (lista);
			BufferedReader br = new BufferedReader(fr);
			String linea=br.readLine();
			while(linea!=null) {
				resultado.add(linea.toUpperCase());
				linea=br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
