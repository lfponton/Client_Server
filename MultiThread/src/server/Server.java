package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public void start() {
    System.out.println("Starting server...");
    try {
      ServerSocket serverSocket = new ServerSocket(1234);

      while (true) {
        Socket socket = serverSocket.accept(); //stuck here
        System.out.println("Client connected");

        ServerConnection serverConnection = new ServerConnection(socket);
        Thread t = new Thread(serverConnection);
        t.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
