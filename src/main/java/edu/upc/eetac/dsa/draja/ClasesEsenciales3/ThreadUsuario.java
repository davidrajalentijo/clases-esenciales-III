package edu.upc.eetac.dsa.draja.ClasesEsenciales3;


import java.io.*;
import java.net.*;
import java.text.*;
import java.util.Date;

public class ThreadUsuario extends Thread {
	

	Socket ClientSocket=null;//Lo rellenaremos con el socket del cliente
	PrintWriter out= null;//Será para que el servidor pueda escribir al cliente
	BufferedReader in =null;//Será para que el servidor pueda leer del cliente
	String MsgCliente=null;
	
	int IdThread=0;
	
	public ThreadUsuario ( Socket con, int id){
		ClientSocket=con;
		IdThread=id;
		System.out.println("Hemos pasado correctamente los parametros: Socket: "+ClientSocket+" ID:"+IdThread);
	}
	
	public void run() {
		
		try{
			System.out.println("Estamos dentro del trhead");
			//Creamos los objetos para poder leer y escribir del socket.
			out= new PrintWriter(ClientSocket.getOutputStream(),true);
			in = new BufferedReader ( new InputStreamReader(ClientSocket.getInputStream()));
			
			String InputLine;
			int pet;
			while((InputLine=in.readLine())!=null){
					
					pet=Integer.parseInt(InputLine);

					System.out.println("Servidor---> Recibimos: "+InputLine);
				
					if(pet==0){
						//Sacar fecha del sistema
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY  HH: mm: ss");
						String fechastring = formato.format(fecha);
						System.out.println("Servidor---> "+fechastring);
					
						//Enviamos la fecha al cliente.			
						out.println(fechastring);
						
						System.out.println("Servidor---> Hemos enviado correctamente los datos al CLiente.");	
					}				
					if(pet==1){
						
						//Sacar fecha del sistema
						Date fecha = new Date();
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY  HH: mm: ss");
						String fechastring = formato.format(fecha);
						DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
						String Fechames= df4.format(fecha);
						String Msgsend=Fechames+fechastring;
						System.out.println("Servidor---> "+Msgsend);
					
						//Enviamos la fecha al cliente.			
						out.println(Msgsend);						
						System.out.println("Servidor---> Hemos enviado correctamente los datos al CLiente.");
					}
			}
			//Cerramos objetos.
			out.close();
			in.close();
			//Cerramos Connexiones.
			ClientSocket.close();
			Thread.interrupted();
			
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	

}
