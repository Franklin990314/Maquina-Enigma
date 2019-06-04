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
public class Rotor {
    
    /**
     * 
     * @param num: ingresa el número del rotor a crear
     * @return una matriz con el rotor ya creado
     */
    public char [][] Rotor(int num){
    	
    	try
    	{
    		
    		// Se crea la varibale que leera el archivo con los datos del rotor
    		BufferedReader bz = null;
    		if(num == 1)
    			bz = new BufferedReader (new FileReader(new File("Rotor/Rotor_1.in")));
    		else if(num == 2) 
    			bz = new BufferedReader (new FileReader(new File("Rotor/Rotor_2.in")));
    		else 
    			bz = new BufferedReader (new FileReader(new File("Rotor/Rotor_3.in")));
    		
    		String Linea = null;
    		// Se crea la matriz de 26 columnas con dos filas
			char [][]Rotor = new char[26][2]; 
			
			// Se lee el archivo con los datos del rotor
			for(int i = 0; (Linea = bz.readLine()) != null; i ++) { 
				
				String[] inf = Linea.split(" - ");
				// Se ingresan los datos a la Matriz
				for(int j = 0; j < 2; j++)
					Rotor[i][j] = inf[j].charAt(0);
			}
			return Rotor;
    		
    	}
    	catch (Exception ex) {}
    	
    	return null;
    }
    
    /**
     * 
     * @param Rotor: ingresa el rotor externo
     * @param Rotor_temp
     * @return una matriz con el rotor interno ya creado
     */
    public char [][] Rotor_2(char[][] Rotor, char[][] Rotor_temp){
    	
    	// Se crean variables a utilizar
    	int Cont = 0, temp = 0;
    	char [][]Rotor_00 = new char[26][2];
    	
    	// Se llena la matriz del rotor temporal
    	for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 2; j++) 
				Rotor_temp[i][j] = Rotor[i][1-j];
		}
		
		Cont = 65;
		// Se crea el rotor interno
		for(int i = 0; i < 26; i++) {
			
			// Se busca la letra conectada de forma ordenada del rotor externo
			for(int j = 0; j < 26; j++) {
				
				if((int)Rotor_temp[j][0] == Cont) {
					temp = j;
					j = 26;
				}
			}
			// Se llena la matriz del rotor interno 
			for(int k = 0; k < 2; k++) 
				Rotor_00[i][k] = Rotor_temp[temp][k];
			Cont++;
		}
		return Rotor_00;
    }
    /**
     * 
     * @param Apuntador: ingresa el Apuntador actual
     * @return un character con el Apuntador nuevo
     */
    public char rotar(char Apuntador){
    	
    	// Se pasa al siguiente apuntador
    	int Apunt = (char)Apuntador + 1; 
    	
    	// Se verifica si el apuntador a llegado al character 'Z' para reiniciar el apuntador en 'A'
    	if (Apunt > 90)
    		Apunt = 65;
    	
    	return (char)Apunt;
    	
    }
}