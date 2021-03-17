package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public void start()
  {
    try
    {
      ServerSocket serverSocket = new ServerSocket(1234);

      System.out.println("Server started.");
      int id = 0;
      while (true)
      {
        System.out.println("Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client accepted.");
        new Thread(new ServerSocketHandler(socket, id)).start();
        id++;
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
