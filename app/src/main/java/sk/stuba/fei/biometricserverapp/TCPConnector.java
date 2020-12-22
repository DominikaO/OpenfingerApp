package sk.stuba.fei.biometricserverapp;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnector {
    private Socket socket;
    DataOutputStream dOut;
    PrintWriter printWriter;


    }


    /*public boolean connectTCP(){
        try {
            socket = new Socket("147.175.106.8",22222);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            byte[] pole = new byte[20];
            pole[0]='a';
            pole[1]='h';
            pole[2]='o';
            pole[3]='j';
            dOut.write(pole);
            dOut.flush();
            dOut.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return socket.isConnected();
    }*/


