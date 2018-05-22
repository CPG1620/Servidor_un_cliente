package servidorbasico;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import protocoloBasico.ProtocoloBasico;

public class ServidorBasico {

    public static void main(String[] args) {
        ServidorBasico a = new ServidorBasico();
        a.iniciar();
    }

    public void iniciar() {
        int puerto = ProtocoloBasico.puerto;
        try {
            System.out.println("Esperando conexión..");
            ServerSocket srv = new ServerSocket(puerto);
            Socket skt = srv.accept();
            System.out.println("Conexión iniciada..");
            
            //Flujo para recibir la información del cliente
            BufferedInputStream entrada =
                    new BufferedInputStream(skt.getInputStream());
            Scanner r = new Scanner(entrada);
            
            //Flujo para responderle al cliente
            PrintWriter t = new PrintWriter(skt.getOutputStream());
            
            while(true) {
                String s = r.nextLine();
                System.out.println("Línea recibida: " + s);
                
                //Respuesta al cliente
                t.println("OK");
                //Vacía los buffers de salida, garantiza que
                //se envie toda la información a través del flujo
                t.flush();
            }
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
