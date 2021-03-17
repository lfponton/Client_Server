package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  public void start() {
    try {
      ServerSocket serverSocket = new ServerSocket(1234);
      Pool pool = new Pool();

      System.out.println("Server started.");

      while (true) {
        System.out.println("Waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client accepted.");

        ServerSocketHandler handler = new ServerSocketHandler(socket, pool);
        pool.addConnection(handler);

        new Thread(handler).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
