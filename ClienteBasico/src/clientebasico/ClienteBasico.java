package clientebasico;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import protocoloBasico.ProtocoloBasico;

public class ClienteBasico {

    public static void main(String[] args) {
        ClienteBasico a = new ClienteBasico();
        a.iniciar();
    }

    public void iniciar() {
        int puerto = ProtocoloBasico.puerto;
        String servidor = "localhost";
        try {
            Socket skt = new Socket(servidor, puerto);

            //Flujo de salida
            PrintWriter r = new PrintWriter(skt.getOutputStream());
            
            //Flujo de entrada
            BufferedInputStream t =
                    new BufferedInputStream(skt.getInputStream());
            
            //Solicita una entrada del usuario
            Scanner srv = new Scanner(t);
            Scanner entrada = new Scanner(System.in);
            
            while (true) {
                String s = entrada.nextLine();
                System.out.println("Enviando: " + s);
                
                //Envia la informacion al servidor
                r.println(s);
                
                //Vacía los buffers de salida, garantiza que
                //se envie toda la información a través del flujo
                r.flush();
                
                // Espera la respuesta del servidor
                String c = srv.nextLine();
                System.out.println("Confirmación: " + c);
                                
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
