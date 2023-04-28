package com.babel.vehiclerentingapproval.cron;
import java.io.*;

public class fileWriterSolicitudRenting {

    void creaFichero() throws IOException {
        FileWriter fichero = null;
        PrintWriter pw;

        try {
            fichero = new FileWriter("c:/prueba.txt");
            pw = new PrintWriter(fichero);
            pw.println("Nº peticiones de Solicitud Renting en el día: 0");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {

                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
