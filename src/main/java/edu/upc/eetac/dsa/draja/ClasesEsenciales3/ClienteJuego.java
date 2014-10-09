package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClienteJuego {
	
	public static void main(String[] args) throws IOException {

		// Creamos Objetos y atributos para la conexión
		// Damos la @Ip y el puerto donde escuchará el Servidor.
		String ServerHostName = new String("127.0.0.1");
		int puerto = 1010;

		// Inicializamos paarametros para el Socket y escritura y lectura de
		// este
		Socket SocketCon = null;
		PrintWriter out = null;
		BufferedReader in = null;

		// Instanciamos los parametros creados.Ponemos un try, para que no pete
		// el programa
		try {
			SocketCon = new Socket(ServerHostName, puerto);
			out = new PrintWriter(SocketCon.getOutputStream());
			in = new BufferedReader(new InputStreamReader(SocketCon.getInputStream()));
		} catch (UnknownHostException ue) {
			System.err.println("Host: " + ServerHostName + " no encontrado.");
			System.exit(1);
		} catch(IOException ioe){
			System.err.println("No se ha podido coger I/O de"+ "la conexión de: "+ ServerHostName);
			System.exit(1);
		}
		
		//Interactuamos con el jugador
		System.out.println("=============================================================================================");
		System.out.println("=\t Quieres Jugar a los Chinos?(si/no)\t=");
		System.out.println("=============================================================================================");
		
		String jugar=null;
		Scanner inconsola =new Scanner(System.in);//leeer de pantalla
		boolean jugando=true;
		
		jugar=inconsola.next();
		if(jugar=="no")
			jugando=false;
		else{
			out.close();
			in.close();
			SocketCon.close();			
		}
		
		System.out.println("=============================================================================================");
		System.out.println("=\t Cuál es tu nombre?\t=");
		System.out.println("=============================================================================================");
		
		String nombre, UserOutput,UserInput,apuesta, monedas, consola;
		String []peticion;
		String []leer;
		nombre= inconsola.next();
		
		while(jugando==true){
			//Petición para jugar
			UserOutput="PLAY "+nombre;
			out.println(UserOutput);//Enviamos al servidor.
			System.out.println("Hemos enviado correctamente elPLAY");
						
			while( (UserInput=in.readLine()) != null ){
				System.out.println("Leemos del buffer.");
				if(in.read()!=-1){
					System.out.println("Hay chicha en el buffer.");
					peticion=UserInput.split(" "); 
					if(peticion[0]=="WAIT")
						System.out.println("Esperando que hable el servidor...");
					if(peticion[0]=="YOUR"){
						System.out.println("=============================================================================================");
						System.out.println("=\tIntroduce las monedas y tu apuesta separados con espacio\t=");
						System.out.println("=============================================================================================");
						//Leemos lo que escribe en consola
						consola=inconsola.next();
						leer=consola.split(" ");
						monedas=leer[0];
						apuesta=leer[1];
						//Construimos y enviamos el mensaje al Servidor.
						UserOutput= " MY BET "+monedas+" "+apuesta;
						out.println(UserOutput);
					}if(peticion[0]=="VERSUS"){
						System.out.println("=============================================================================================");
						System.out.println("=\t Su contrincante será: "+peticion[1]+"\t=");	
						System.out.println("=============================================================================================");
					}if(peticion[0]=="BET"){
						System.out.println("=============================================================================================");
						System.out.println("=\t "+UserInput+"\t=");	
						System.out.println("=============================================================================================");
					}if(peticion[0]=="WINNER"){
						System.out.println("=============================================================================================");
						System.out.println("=\t "+UserInput+"\t=");	
						System.out.println("=============================================================================================");
						jugando=false;
					}
				}
			}
		}
		System.out.println("Adiós. Ahora te desconectarás de todo.");
		out.close();
		in.close();
		SocketCon.close();
	}

}
