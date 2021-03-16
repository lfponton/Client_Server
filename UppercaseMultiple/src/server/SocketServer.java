package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  public void startServer()
  {
    try
    {
      ServerSocket serverSocket = new ServerSocket(1234);

      while (true)
      {
        Socket socket = serverSocket.accept();
        System.out.println("Client connected from " + socket.getInetAddress()
            .getHostAddress() + " " + socket.getLocalPort());
        ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket);
        Thread t = new Thread(serverSocketHandler);
        t.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}
