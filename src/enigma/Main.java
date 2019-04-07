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
    	
    	try
    	{
    		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
    		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (System.out));
    		
    		bw.write("Introduce el texto a cifrar: ");
    		bw.flush();
    		String text = br.readLine();
    		
    		Enigma cifrar = new Enigma(text);
    		
    		cifrar.cifrar();
    		cifrar.toString_1();
    	}
    	catch (Exception ex) {}
        
    }
}