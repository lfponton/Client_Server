package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public void start()
  {
    try {
      ServerSocket serverSocket = new ServerSocket(1234);

      Socket socket = serverSocket.accept();

      ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket);

      Thread t = new Thread(serverSocketHandler);
      t.start();

    } catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
