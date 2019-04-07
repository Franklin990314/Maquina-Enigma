package enigma;

import java.util.ArrayList;
import java.io.*;

/**
 * 
 * @author Laura Catalina Vega Tinjaca
 * @author Michael Hernando Parra Sanchez
 * @author Frank nicolas Herran Garzon
 * @creator Franklin
 *
 */
public class Enigma {
    
    String Cifrar = null; // Variable que guarda el texto a cifrar
    char Clavija_1 = 'W'; // Variable que guarda la posición del Clavija en el Rotor_1
    char Clavija_2 = 'E'; // Variable que guarda la posición del Clavija en el Rotor_2
    char Clavija_3 = 'Q'; // Variable que guarda la posición del Clavija en el Rotor_3
    
    ArrayList<Character> Entrada = new ArrayList(); // ArrayList donde se guardaran los Character del texto a cifrar
    ArrayList<Character> Result = new ArrayList(); // ArrayList donde se guardaran los Character del texto ya cifrado
    
    /**
     * 
     * @param text: ingresa el Texto a Encriptar
     */
    public Enigma(String text){
        
    	Cifrar = text;
    	
    	// Se adiciona al ArrayList los Characters del texto
    	for(int i = 0; i < text.length(); i++)
    		Entrada.add(text.charAt(i));
    }
    
    /**
     * 
     * @param num 
     */
    public void Clavija(int num){
        
    	
    }
    
    /**
     * Creación de los Rotores y Apuntadores
     * Cifrado y Decifrado de los mensajes
     * 
     */
    public void cifrar(){
        
    	// Creación de Apuntadores y variables a utilizar
    	int Apuntador_1 = (char)'X';
    	int Apuntador_2 = (char)'D';
    	int Apuntador_3 = (char)'H';
    	int dif = 0, temp = 0, Cont = 0;
    	Rotor Ac1 = new Rotor();
    	Reflector Ac2 = new Reflector();
    	
    	// Creación de las Matrices que guardaran la posición de los Apuntadores
    	char [][]Rotor_1 = new char[26][2]; // Guarda las posiciones del Rotor_1
		char [][]Rotor_2 = new char[26][2]; // Guarda las posiciones del Rotor_2
		char [][]Rotor_3 = new char[26][2]; // Guarda las posiciones del Rotor_3
		char [][]Rotor_01 = new char[26][2]; // Guarda las posiciones de Rotor_01, las cuales se obtienen con la inversa del Rotor_1
		char [][]Rotor_02 = new char[26][2]; // Guarda las posiciones de Rotor_02, las cuales se obtienen con la inversa del Rotor_2
		char [][]Rotor_03 = new char[26][2]; // Guarda las posiciones de Rotor_03, las cuales se obtienen con la inversa del Rotor_3
		char [][]Rotor_0temp = new char[26][2]; // Rotor temporal para la creación de los Rotores 01,02,03
		char [][]Reflector = new char[26][2]; // Guarda las posiciones del Reflector
		
		// Se Crean los Rotores 1,2,3 y el Reflector
		System.arraycopy(Ac1.Rotor(1), 0, Rotor_1, 0, 26);
		System.arraycopy(Ac1.Rotor(2), 0, Rotor_2, 0, 26);
		System.arraycopy(Ac1.Rotor(3), 0, Rotor_3, 0, 26);
		System.arraycopy(Ac2.Reflector(), 0, Reflector, 0, 26);
		
		// Se crea el Rotor_01
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 2; j++) 
				Rotor_0temp[i][j] = Rotor_1[i][1-j];
		}
		
		Cont = 65;
		for(int i = 0; i < 26; i++) {
			
			for(int j = 0; j < 26; j++) {
				
				if((int)Rotor_0temp[j][0] == Cont) {
					temp = j;
					j = 26;
				}
			}
			for(int k = 0; k < 2; k++) 
				Rotor_01[i][k] = Rotor_0temp[temp][k];
			Cont++;
		}
		
		//Se crea el Rotor_02
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 2; j++) 
				Rotor_0temp[i][j] = Rotor_2[i][1-j];
		}
		
		Cont = 65;
		for(int i = 0; i < 26; i++) {
			
			for(int j = 0; j < 26; j++) {
				
				if((int)Rotor_0temp[j][0] == Cont) {
					temp = j;
					j = 26;
				}
			}
			for(int k = 0; k < 2; k++) 
				Rotor_02[i][k] = Rotor_0temp[temp][k];
			Cont++;
		}
		
		//Se crea el Rotor_03
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 2; j++) 
				Rotor_0temp[i][j] = Rotor_3[i][1-j];
		}
				
		Cont = 65;
		for(int i = 0; i < 26; i++) {
					
			for(int j = 0; j < 26; j++) {
						
				if((int)Rotor_0temp[j][0] == Cont) {
					temp = j;
					j = 26;
				}
			}
			for(int k = 0; k < 2; k++) 
				Rotor_03[i][k] = Rotor_0temp[temp][k];
			Cont++;
		}
		
		// Se repetira el ciclo mientras haya algo que leer en el ArrayList "Entrada"
		for(int i = 0; i < Entrada.size(); i++) {
			
			// Se calcula las letras conectadas en el rotor_1
			Apuntador_1 = Ac1.rotar((char)Apuntador_1);
			dif = Entrada.get(i) - 65;
			dif = Apuntador_1 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_1[dif - 65][1];
			dif = dif - Apuntador_1;
			if(dif < 0) 
				dif = 26 + dif;
			
			// Se calcula las letras conectadas en el rotor_2
			dif = Apuntador_2 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_2[dif - 65][1];
			dif = dif - Apuntador_2;
			if(dif < 0) 
				dif = 26 + dif;
			
			// Se calcula las letras conectadas en el rotor_3
			dif = Apuntador_3 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_3[dif - 65][1];
			dif = dif - Apuntador_3;
			if(dif < 0) 
				dif = 26 + dif;
			
			// Se calcula las letras conectadas en el reflector
			dif = 65 + dif;
			dif = Reflector[dif - 65][1];
			
			// Se calcula las letras conectadas en el rotor_03 de vuelta
			dif = dif - 65;
			if(dif < 0) 
				dif = 26 + dif;
			
			dif = Apuntador_3 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_03[dif - 65][1];
			
			// Se calcula las letras conectadas en el rotor_02 de vuelta
			dif = dif - Apuntador_3;
			if(dif < 0) 
				dif = 26 + dif;
			
			dif = Apuntador_2 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_02[dif - 65][1];
			
			// Se calcula las letras conectadas en el rotor_01 de vuelta
			dif = dif - Apuntador_2;
			if(dif < 0) 
				dif = 26 + dif;
			
			dif = Apuntador_1 + dif;
			if(dif > 90) 
				dif = 64 +( dif - 90 );
			
			dif = Rotor_01[dif - 65][1];
			
			// Se calcula las letras cifradas
			dif = dif - Apuntador_1;
			if(dif < 0) 
				dif = 26 + dif;
			
			dif = 65 + dif;
			
			// Se añade el resultado del Character encriptado al ArrayList "Result"
			Result.add((char)dif);
		}
		
    }
    
    /**
     * 
     * Se imprime el ArrayList que guarda los resultados de la Encriptación
     * 
     */
    public void toString_1(){
    	
    	try {
    		
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    		
    		for(int i = 0; i < Result.size(); i++) {
    			bw.write(Result.get(i));
    			bw.flush();
    		}
    			
    	}
    	catch (Exception ex) {}
    }        
}