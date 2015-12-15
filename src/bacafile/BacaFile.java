/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bacafile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author baskoro
 */
public class BacaFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            byte[] buf = new byte[10];
            Socket socket = new Socket("10.151.12.105", 6666);
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();       
            
            
            
            //buf = new byte[100];
            /*int len;
            while(true) {
               
                len = is.read(buf);
                if(len == -1) {
                    break;
                }
                
                System.out.print(new String(buf));
            }
            */
            //System.out.print(new String(buf));
            buf = new byte[1000];
            is.read(buf);
            System.out.println(new String(buf));
            
            os.write("USERNAME:5113100035\n".getBytes());
            
            
            buf = new byte[1000];
            is.read(buf);
            System.out.println(new String(buf));
            
            buf = new byte[1000];
            is.read(buf);
            System.out.println(new String(buf));
            
            String temp = new String(buf);
            String[] angka;
            angka = temp.split(" ");
            /*for(int i=0;i<3;i++){
                System.out.println(angka[i]);
            }*/
            while(true){
                int bil1, bil2;
            
                bil1 = Integer.valueOf(angka[0]);
                bil2 = Integer.valueOf(angka[2]);

                int hasil = 0;
                
                if(angka[1].equals("+")){
                    hasil = bil1 + bil2;
                    //System.out.println(hasil);
                }
                else if(angka[1].equals("-")){
                    hasil = bil1 - bil2;
                    //System.out.println(hasil);
                }
                else if(angka[1].equals("x")){
                    hasil = bil1 * bil2;
                    //System.out.println(hasil);
                }
                else if(angka[1].equals("mod")){
                    hasil = bil1 % bil2;
                    //System.out.println(hasil);
                }       
                String nilai = String.valueOf(hasil);
                String kirim = "Result:";
                //kirim.concat(nilai);
                System.out.println(kirim.concat(nilai).concat("\n"));

                os.write(kirim.concat(nilai).concat("\n").getBytes());
                buf = new byte[1000];
                is.read(buf);
                //System.out.print("-->");
                System.out.println(new String(buf));
                //System.out.println(new String(buf));
                
                String c = new String(buf);
                //System.out.println(c);
                String pecah = c.substring(0,4);
                //System.out.println(pecah);
                if(pecah.equals("Hash")){
                    break;
                }
                
                buf = new byte[1000];
                is.read(buf);
                System.out.println(new String(buf));
                //System.out.println(new String(buf));
                
                
                
                temp = new String(buf);
                angka = temp.split(" ");
                /*
                System.out.println("===\n");
                System.out.println(angka[0]);
                System.out.println(angka[1]);
                System.out.println(angka[2]);
                System.out.println("===\n");
                */
                
                
            }
            //System.out.println("hhhh");
            String temp2 = new String(buf);
            String[] hass;
            hass = temp2.split("\n");
            String kirim = "Hash:";
            //System.out.println(hass[0]);
            System.out.println(kirim.concat(hass[2]).concat("\n"));
            os.write(kirim.concat(hass[2]).concat("\n").getBytes());
            buf = new byte[1000];
            is.read(buf);
            System.out.println(new String(buf));
            
           
            os.flush();
            os.close();
            is.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(BacaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
