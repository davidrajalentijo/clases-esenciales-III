package edu.upc.eetac.dsa.draja.ClasesEsenciales3;

import java.io.*;
import java.net.*;

public class ThreadUser extends Thread {
	Socket ClientSocket1=null,ClientSocket2=null;
	PrintWriter out1=null,out2=null;
	BufferedReader in1=null, in2=null;
	String MsgReciev=null;
	String MsgSend=null;
	String nombre1,nombre2,monedas1,monedas2,apuesta1,apuesta2,ganador,line;
	String []trozos;
	int Id=0,mon1,mon2,apu1,apu2,montotal;
	
	public ThreadUser ( Socket con1,Socket con2,String nom1,String nom2, int id){		
		ClientSocket1=con1;
		ClientSocket2=con2;
		Id=id;
		nombre1=nom1;
		nombre2=nom2;
		try{
		out1= new PrintWriter ( ClientSocket1.getOutputStream());
		out2=new PrintWriter ( ClientSocket2.getOutputStream());
		in1= new BufferedReader ( new InputStreamReader ( ClientSocket1.getInputStream()));
		in2= new BufferedReader ( new InputStreamReader ( ClientSocket2.getInputStream()));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}		
	}
	public void run (){
		
		//Enviamos los Vs a cada jugador
		out1.println("VERSUS "+nombre2);
		out2.println("VERSUS "+nombre1);
		System.out.println("Hemos enviado los VS a cada jugador.");
		
		//Jugador2 espera, Jugador 1 apuesta.
		out2.println("WAIT BET");
		out1.println("YOUR BET");
		System.out.println("Enviamos a cada jugador una orden");
		
		//Leemos lo que nos tiene que enviar el jugador 1
		try{
			while((line=in1.readLine())!=null){
				trozos=line.split(" ");
				if(trozos[0]=="MY"){
					monedas1=trozos[1];
					apuesta1=trozos[2];
					mon1=Integer.parseInt(monedas1);
					apu1=Integer.parseInt(apuesta1);
					System.out.println("El jugador "+nombre1+" ha puesto "+mon1+" monedasy apuesta "+apu1);
					
					//Enviamos al jugador 1, la apuesta que ha hecho
					out1.println("BET OF "+nombre1+" "+apu1);
					System.out.println("Se ha enviado la confirmación correctamente.");
					break;
					
				}
			}
		}catch(IOException ioe){
			System.err.println(" No se ha podido leer del buffer del jugador 1");
			System.exit(1);
		}
		
		//Ahora enviamos al jugador 2 que ya es su turno.
		out2.println("YOUR BET");
		System.out.println("Turno del jugador 2");
		
		//Leemos lo que nos tiene que enviar el jugador 2
		try{
			while((line=in2.readLine())!=null){
				trozos=line.split(" ");
				if(trozos[0]=="MY"){
				monedas2=trozos[1];
				apuesta2=trozos[2];
				mon2=Integer.parseInt(monedas2);
				apu2=Integer.parseInt(apuesta2);
				System.out.println("El jugador "+nombre2+" ha puesto "+mon2+" monedasy apuesta "+apu2);
							
				//Enviamos al jugador 1, la apuesta que ha hecho
				out2.println("BET OF "+nombre2+" "+apu2);
				System.out.println("Se ha enviado la confirmación correctamente.");
				break;
							
				}
			}
		}catch(IOException ioe){
					System.err.println(" No se ha podido leer del buffer del jugador 2");
					System.exit(1);
		}
		//Miramos quien es el ganador
		montotal=mon1+mon2;
		
		if(apu1==montotal){
			System.out.println("El ganador ha sido "+nombre1);
			out1.println("WINNER "+nombre1);
			out2.println("WINNER "+nombre1);
		}
		if(apu2==montotal){
			System.out.println("El ganador ha sido "+nombre2);
			out1.println("WINNER "+nombre2);
			out2.println("WINNER "+nombre2);
		}else{
			System.out.println("Nadie gana.");
			out1.println("WINNER NONE");
			out2.println("WINNER NONE");
		}
		
		try{
			//Cerramos connexiones para acabar partida.
			System.out.println("Se acabó la partida.");
		
			out1.close();
			out2.close();
			in1.close();
			in2.close();
			ClientSocket1.close();
			ClientSocket2.close();
		}catch(IOException ioe){
			System.err.println("Error al cerrar connexiones.");
			System.exit(1);
		}		
		
	}
	


}
