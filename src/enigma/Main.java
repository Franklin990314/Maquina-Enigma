package enigma;

import java.io.*;

/**
 * 
 * @author Laura Catalina Vega Tinjaca
 * @author Michael Hernando Parra Sanchez
 * @author Frank nicolas Herran Garzon
 * @creator Franklin
 *
 */
public class Main {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
    	char first = 'X',second = 'D',third = 'H';
    	
    	try
    	{
    		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
    		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (System.out));
    		
    		bw.write("***************************************************************************************");
    		bw.newLine();
    		bw.write("* POR FAVOR TENER EN CUENTA QUE ESTA APLICACIÓN SOLO FUNCIONA CON LETRAS MAYÚSCULAS,  *");
    		bw.newLine();
    		bw.write("*                         SIN ESPACIOS, CARACTERES O NÚMEROS                          *");
    		bw.newLine();
    		bw.write("***************************************************************************************");
    		bw.newLine();
    		bw.flush();
    		
    		bw.write("¿Le gustaría cambiar la posición inicial de los Apuntadores? ("
    				+ "los apuntadores se encuentran con posición inicial 'H', 'D' y 'X' respectivamente): ");
    		bw.flush();
    		String Apunt = br.readLine();
    		if(Apunt.equals("SI")) {
    			
    			bw.write("Posición inicial del apuntador No. 1: ");
    			bw.flush();
    			third = br.readLine().charAt(0);
    			
    			bw.write("Posición inicial del apuntador No. 2: ");
    			bw.flush();
    			second = br.readLine().charAt(0);
    			
    			bw.write("Posición inicial del apuntador No. 3: ");
    			bw.flush();
    			first = br.readLine().charAt(0);
    		}
    		
    		bw.write("Introduce el texto a Encriptar o Desencriptar: ");
    		bw.flush();
    		String text = br.readLine();
    		
    		Enigma cifrar = new Enigma(text);
    		
    		cifrar.cifrar(first, second, third);
    		cifrar.toString_1();
    	}
    	catch (Exception ex) {}
        
    }
}