import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class MasRapidoPorFilas extends Thread {
	public int filaInicio;
	public int filaFin;
	public List<String> lista;
	private static Map<String,Integer> cantidadPalabras=new HashMap<String,Integer>();
	private List<List<String>> sopa;
	private static Semaphore semaforo= new Semaphore(1);
	
	public MasRapidoPorFilas(int filaInicio,int filaFin, List<List<String>> sopa, List<String> lista) {
		this.filaInicio=filaInicio;
		this.filaFin=filaFin;
		this.sopa=sopa;
		this.lista=lista;
	}
	public void run() {
		for(int fila=filaInicio;fila<filaFin;fila++) {
			List<String> f=sopa.get(fila);
			for(int columna=0;columna<f.size();columna++) {
				String letra=f.get(columna);
				for(String palabra : lista) {
					if(palabra.startsWith(letra)) {
						int cantidadEncontrada=0;
						cantidadEncontrada += comprobarDiagonalAbajoDerecha(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalArribaDerecha(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalAbajoIzquierda(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarDiagonalArribaIzquierda(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarHorizontalDerecha(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarHorizontalIzquierda(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarVerticalArriba(fila,columna,palabra,sopa);
						cantidadEncontrada += comprobarVerticalAbajo(fila,columna,palabra,sopa);
						try {
							semaforo.acquire();
							cantidadPalabras.put(palabra, cantidadPalabras.get(palabra)+cantidadEncontrada);
							semaforo.release();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> sopa=leerSopa("sopa.txt");
		List<String> lista=leerLista("palabras.txt");
		long empezar=System.currentTimeMillis();
		for(String palabra: lista) {
			cantidadPalabras.put(palabra, 0);
		}
		int division=sopa.size()/6;
		MasRapidoPorFilas cant1= new MasRapidoPorFilas(0,division,sopa,lista);
		cant1.start();
		MasRapidoPorFilas cant2= new MasRapidoPorFilas(division,division*2,sopa,lista);
		cant2.start();
		MasRapidoPorFilas cant3= new MasRapidoPorFilas(division*2,division*3,sopa,lista);
		cant3.start();
		MasRapidoPorFilas cant4= new MasRapidoPorFilas(division*3,division*4,sopa,lista);
		cant4.start();
		MasRapidoPorFilas cant5= new MasRapidoPorFilas(division*4,division*5,sopa,lista);
		cant5.start();
		MasRapidoPorFilas cant6= new MasRapidoPorFilas(division*5,sopa.size(),sopa,lista);
		cant6.start();
		try {
			cant1.join();
			cant2.join();
			cant3.join();
			cant4.join();
			cant5.join();
			cant6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(String palabra: lista) {
			int cantidadEncontrada=cantidadPalabras.get(palabra);
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
						caracteresLinea.add(caracter);
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
				resultado.add(linea);
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
	
	public int comprobarDiagonalArribaDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarDiagonalAbajoDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarDiagonalArribaIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarDiagonalAbajoIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarHorizontalDerecha(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarHorizontalIzquierda(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarVerticalArriba(int linea, int posActual,String palabra, List<List<String>> sopa) {
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
	public int comprobarVerticalAbajo(int linea, int posActual,String palabra, List<List<String>> sopa) {
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

}
