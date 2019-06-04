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
     * @param dif: ingresa la diferencia entre letras del rotor externo a calcular
     * @param Apuntador: ingresa el apuntador del rotor externo
     * @param Rotor: ingresa el rotor exerno a evaluar
     * @return diferencia entre letras
     */
    public int Apuntador(int dif, int Apuntador, char Rotor[][]){
    	
    	dif = Apuntador + dif;
		if(dif > 90) 
			dif = 64 +( dif - 90 );
		
		dif = Rotor[dif - 65][1];
		dif = dif - Apuntador;
		if(dif < 0) 
			dif = 26 + dif;
		
    	return dif;
    }
    
    /**
     * 
     * @param dif: ingresa la diferencia entre letras del rotor interno a calcular
     * @param Apuntador: ingresa el apuntador del rotor externo
     * @param Rotor: ingresa el rotor interno a evaluar
     * @return diferencia entre letras
     */
    public int Apuntador_0(int dif, int Apuntador, char Rotor[][]){
    	
    	if(dif < 0) 
			dif = 26 + dif;
		
		dif = Apuntador + dif;
		if(dif > 90) 
			dif = 64 +( dif - 90 );
		
		dif = Rotor[dif - 65][1];
		
    	return dif;
    }
    
    /**
     * Creación de los Rotores y Apuntadores
     * Cifrado y Decifrado de los mensajes
     * 
     */
    public void cifrar(char first, char second, char third){
        
    	// Creación de Apuntadores y variables a utilizar
    	int Apuntador_1 = (char)first, Apuntador_2 = (char)second, Apuntador_3 = (char)third;
    	int dif = 0;
    	Rotor Ac1 = new Rotor();
    	Reflector Ac2 = new Reflector();
    	
    	// Creación de las Matrices que guardaran la posición de los Apuntadores
    	char [][]Rotor_1 = new char[26][2], Rotor_2 = new char[26][2], Rotor_3 = new char[26][2]; // Guarda las posiciones del Rotor_3
		char [][]Rotor_01 = new char[26][2], Rotor_02 = new char[26][2], Rotor_03 = new char[26][2]; // Guarda las posiciones de Rotor_03, las cuales se obtienen con la inversa del Rotor_3
		char [][]Rotor_0temp = new char[26][2]; // Rotor temporal para la creación de los Rotores 01,02,03
		char [][]Reflector = new char[26][2]; // Guarda las posiciones del Reflector
		
		// Se Crean los Rotores 1,2,3 y el Reflector
		System.arraycopy(Ac1.Rotor(1), 0, Rotor_1, 0, 26);
		System.arraycopy(Ac1.Rotor(2), 0, Rotor_2, 0, 26);
		System.arraycopy(Ac1.Rotor(3), 0, Rotor_3, 0, 26);
		System.arraycopy(Ac2.Reflector(), 0, Reflector, 0, 26);
		
		// Se Crean los Rotores Auxiliares 01,02 y 03
		System.arraycopy(Ac1.Rotor_2(Rotor_1, Rotor_0temp), 0, Rotor_01, 0, 26);
		System.arraycopy(Ac1.Rotor_2(Rotor_2, Rotor_0temp), 0, Rotor_02, 0, 26);
		System.arraycopy(Ac1.Rotor_2(Rotor_3, Rotor_0temp), 0, Rotor_03, 0, 26);
		
		// Se repetira el ciclo mientras haya algo que leer en el ArrayList "Entrada"
		for(int i = 0; i < Entrada.size(); i++) {
			
			// Se gira el rotor 1
			Apuntador_1 = Ac1.rotar((char)Apuntador_1);
			
			// Se crea el apuntador del rotor III
			if(Apuntador_1 == (char)'W') 
				Apuntador_2 = Ac1.rotar((char)Apuntador_2);
			// Se crea el apuntador del rotor II
			else if(Apuntador_1 == (char)'X' && Apuntador_2 == (char)'E')
				Apuntador_2 = Ac1.rotar((char)Apuntador_2);
			// Se crea el apuntador del rotor I
			if(Apuntador_1 == (char)'X' && Apuntador_2 == (char)'F')
				Apuntador_3 = Ac1.rotar((char)Apuntador_3);
			
			// Se calcula las letras conectadas en el rotor_1
			dif = Entrada.get(i) - 65;
			dif = Apuntador(dif, Apuntador_1, Rotor_1);
			
			// Se calcula las letras conectadas en el rotor_2
			dif = Apuntador(dif, Apuntador_2, Rotor_2);
			
			// Se calcula las letras conectadas en el rotor_3
			dif = Apuntador(dif, Apuntador_3, Rotor_3);
			
			// Se calcula las letras conectadas en el reflector
			dif = 65 + dif;
			dif = Reflector[dif - 65][1];
			
			// Se calcula las letras conectadas en el rotor_03 de vuelta
			dif = dif - 65;
			dif = Apuntador_0(dif, Apuntador_3, Rotor_03);
			
			// Se calcula las letras conectadas en el rotor_02 de vuelta
			dif = dif - Apuntador_3;
			dif = Apuntador_0(dif, Apuntador_2, Rotor_02);
			
			// Se calcula las letras conectadas en el rotor_01 de vuelta
			dif = dif - Apuntador_2;
			dif = Apuntador_0(dif, Apuntador_1, Rotor_01);
			
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
    		bw.write("Su texto Encriptado o Desencriptado es: >> ");
			bw.flush();
    		 
    		for(int i = 0; i < Result.size(); i++) {
    			bw.write(Result.get(i));
    			bw.flush();
    		}
    			
    	}
    	catch (Exception ex) {}
    }        
}