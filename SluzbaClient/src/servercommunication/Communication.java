/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servercommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.HandleServer;
import tranfer.Request;
import tranfer.Response;

/**
 *
 * @author Bosko
 */
public class Communication {
    private static Communication instance;
    Socket s;

    private Communication() {
    }

    public static Communication getInstance() {
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public boolean Connect(){
        try {
            s=new Socket("localhost", 9000);
            HandleServer hs=new HandleServer();
            hs.start();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public Response getResponse(){
        Response res=new Response();
        try {
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            res=(Response) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void sendRequest(Request req){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(req);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
