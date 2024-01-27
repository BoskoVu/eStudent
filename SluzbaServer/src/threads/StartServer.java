/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;

/**
 *
 * @author Bosko
 */
public class StartServer extends Thread{
    
    ServerSocket ss;
    boolean end;
    
    @Override
    public void run() {
        while(true){
            try {
                ss=new ServerSocket(9000);
                while(!end){
                    Socket s=ss.accept();
                    HandleClients hc=new HandleClients(s);
                    hc.start();
                    Controller.getInstance().getListaSoket().add(s);
                }
            } catch (IOException ex) {
                Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
