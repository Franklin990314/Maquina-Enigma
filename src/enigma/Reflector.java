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
public class Reflector {
    
	/**
	 * 
	 * @return una Matriz con el Reflector creado
	 */
	public char[][] Reflector() {
		
		try
		{
			// Se crea la varibale que leera el archivo con los datos del reflector
			BufferedReader bz = new BufferedReader (new FileReader(new File("Reflector/Reflector.in")));
			
			String Linea = null;
			// Se crea la matriz de 26 columnas con dos filas
			char [][]Reflector = new char[26][2];
	    	
			// Se lee el archivo con los datos del reflector
			for(int i = 0; (Linea = bz.readLine()) != null; i ++) {
				
				String[] inf = Linea.split(" - ");
				// Se ingresan los datos a la Matriz
				for(int j = 0; j < 2; j++)
					Reflector[i][j] = inf[j].charAt(0);
			}
			return Reflector;
			
		}
		catch (Exception ex) {}
		
		return null;
	}
	
}