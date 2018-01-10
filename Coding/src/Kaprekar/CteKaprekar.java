package Kaprekar;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Clase que calcula el n�mero de iteraciones necesarias hasta
 * alcanzar la constante de Kaprekar (6174) con un n�mero de 4 cifras.
 * Pr�cticamente cualquier n�mero es capaz de llegar a ella.
 * Algunos ejemplos de n�meros:
 * <ol>
 * <li>3528 - 1 Iteraci�n</li>
 * <li>3524 - 3 Iteraciones</li>
 * <li>1121 - 5 Iteraciones</li>
 * <li>1893 - 7 Iteraciones</li>
 * </ol>
 * @author Raul Ordas
 *
 */
public class CteKaprekar {
	static final int KAPREKAR = 6174;
	
	public static void main(String[] args) {
		int num[];
		int numDesc[];
		int resultado;
		int a, b;
		int numIteraciones = 0;
		String input;

		input = solicitaNumero();
		
		//almacena el n�mero input en dos arrays de tipo int[] (num y numDesc)
		num = new int[input.length()];
		numDesc =  new int[input.length()];
		stringArray(num, numDesc, input);

		do {
			//ordenamos los n�meros almacenados en los arrays []num y numDesc[]
			num = ordenAscendente(num);
			numDesc = ordenDescendente(numDesc);
			
			//convertimos los arrays []num y []numDesc en datos de tipo int para operar con ellos.
			a = arrayToNumero(num);
			b = arrayToNumero(numDesc);
			resultado = b - a;
			
			/*
			 * Aunque no es muy habitual, si el resultado fuera <1000 necesitamos
			 * realizar un ajuste para que se mantengan los 4 d�gitos necesarios. 
			 */
			resultado = repararResultado(resultado);
			
			numIteraciones++;
			
			/*
			 * Almacenamos el resultado en las variables originales num y numDesc
			 * por si fuera necesaria una nueva iteraci�n.
			 */
			num = numeroToArray(resultado);
			numDesc = numeroToArray(resultado);	
		} while (resultado != KAPREKAR);
		
		System.out.println(numIteraciones);
	}

	private static int repararResultado(int resultado) {
		if (resultado < 1000) {
			resultado *= 10;
		}
		return resultado;
	}

	private static void stringArray(int[] num, int[] numDesc, String input) {
		for (int i = 0; i < input.length(); i++) {
			num[i] = Character.getNumericValue(input.charAt(i));
			numDesc[i] = Character.getNumericValue(input.charAt(i));	
		}
	}
	
	private static String solicitaNumero() {
		String input = "";
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Introduce un n�mero de 4 cifras");
			input = sc.nextLine();
			
			if (input.length() > 4)
				System.out.println("Rango de n�mero Incorrecto");
		}  while (input.length() > 4);
		sc.close();
		return input;
	}

	private static int[] numeroToArray(int pNum) {
		/*
		 * Convierte un n�mero en un Array de la longitud del
		 * n�mero de d�gitos que tenga el n�mero. Posteriormente, guarda
		 * cada d�gito en una posici�n del Array. Para determinar la longitud del array
		 * almacenamos el n�mero en un String que nos permita acceder al m�todo .length().
		 */
		String numAux = "" + pNum;
		int[] resultado = new int[numAux.length()];
		for(int i = 0; i < numAux.length(); i++) {
			resultado[i] = Character.getNumericValue(numAux.charAt(i));
		}
		return resultado;
	}
	
	private static int arrayToNumero(int[] num) {
		/*
		 * Convierte un array en un n�mero multiplicando el valor
		 * de la posici�n del array * 10 y sum�ndole el resultado acumulado
		 * en cada iteraci�n.
		 */
		int resultado = 0;
		for(int i = 0; i < num.length; i++) {
			resultado = resultado*10 + num[i];
		}
		return resultado;
	}
	
	private static int[] ordenAscendente(int[] num) {
		/*La clase arrays ordena de manera ascendente un array num�rico
		 * utilizando .sort. No obstante debajo esta el c�digo que permite hacerlo a
		 * trav�s de iteraciones. Descubr� esta opci�n despu�s de
		 * hacerlo con un bucle for.
		 */
		Arrays.sort(num);
		
		/*int numAux;
		for(int i = 0; i < num.length; i++) {
			for(int j = i+1; j < num.length; j++) {
				if(num[i] > num[j]) {
					numAux = num[i];
					num[i] = num[j];
					num[j] = numAux;
				}
			}
		}*/
		return num;
	}
	private static int[] ordenDescendente(int[] num) {
		/*
		 * Ordena los n�meros del array de manera descendente. En este caso
		 * no he encontrado ninguna opci�n en la clase arrays que lo haga autom�ticamente.
		 */
		int numAux;
		for(int i = 0; i < num.length; i++) {
			for(int j = i+1; j < num.length; j++) {
				if(num[i] < num[j]) {
					numAux = num[i];
					num[i] = num[j];
					num[j] = numAux;
				}
			}
		}
		return num;
	}
}
