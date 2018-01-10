package Kaprekar;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Clase que calcula el número de iteraciones necesarias hasta
 * alcanzar la constante de Kaprekar (6174) con un número de 4 cifras.
 * Prácticamente cualquier número es capaz de llegar a ella.
 * Algunos ejemplos de números:
 * <ol>
 * <li>3528 - 1 Iteración</li>
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
		
		//almacena el número input en dos arrays de tipo int[] (num y numDesc)
		num = new int[input.length()];
		numDesc =  new int[input.length()];
		stringArray(num, numDesc, input);

		do {
			//ordenamos los números almacenados en los arrays []num y numDesc[]
			num = ordenAscendente(num);
			numDesc = ordenDescendente(numDesc);
			
			//convertimos los arrays []num y []numDesc en datos de tipo int para operar con ellos.
			a = arrayToNumero(num);
			b = arrayToNumero(numDesc);
			resultado = b - a;
			
			/*
			 * Aunque no es muy habitual, si el resultado fuera <1000 necesitamos
			 * realizar un ajuste para que se mantengan los 4 dígitos necesarios. 
			 */
			resultado = repararResultado(resultado);
			
			numIteraciones++;
			
			/*
			 * Almacenamos el resultado en las variables originales num y numDesc
			 * por si fuera necesaria una nueva iteración.
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
			System.out.println("Introduce un número de 4 cifras");
			input = sc.nextLine();
			
			if (input.length() > 4)
				System.out.println("Rango de número Incorrecto");
		}  while (input.length() > 4);
		sc.close();
		return input;
	}

	private static int[] numeroToArray(int pNum) {
		/*
		 * Convierte un número en un Array de la longitud del
		 * número de dígitos que tenga el número. Posteriormente, guarda
		 * cada dígito en una posición del Array. Para determinar la longitud del array
		 * almacenamos el número en un String que nos permita acceder al método .length().
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
		 * Convierte un array en un número multiplicando el valor
		 * de la posición del array * 10 y sumándole el resultado acumulado
		 * en cada iteración.
		 */
		int resultado = 0;
		for(int i = 0; i < num.length; i++) {
			resultado = resultado*10 + num[i];
		}
		return resultado;
	}
	
	private static int[] ordenAscendente(int[] num) {
		/*La clase arrays ordena de manera ascendente un array numérico
		 * utilizando .sort. No obstante debajo esta el código que permite hacerlo a
		 * través de iteraciones. Descubrí esta opción después de
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
		 * Ordena los números del array de manera descendente. En este caso
		 * no he encontrado ninguna opción en la clase arrays que lo haga automáticamente.
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
