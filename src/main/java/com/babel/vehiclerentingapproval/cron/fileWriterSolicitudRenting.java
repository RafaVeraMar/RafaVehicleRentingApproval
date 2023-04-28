package com.babel.vehiclerentingapproval.cron;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fileWriterSolicitudRenting {

    void createFileByDay() throws IOException {
        FileWriter fichero = null;
        PrintWriter pw;
        try {
            String urlFileToday = getUrlFile();
            fichero = new FileWriter(urlFileToday);
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

    public String getUrlFile(){
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String date = dateFormat.format(new Date());
        return "C:\\Users\\javier.roldan\\VehicleRentingApproval\\src" +
                "\\main\\resources\\files\\"+date+".txt";
    }
}
