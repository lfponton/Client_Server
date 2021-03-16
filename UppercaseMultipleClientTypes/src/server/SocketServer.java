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

      while (true) {
        Socket socket = serverSocket.accept();

        StringModel stringModel = new StringModel();

        System.out.println("Client connected from " + socket.getInetAddress()
        .getHostAddress() + " " + socket.getLocalPort());

        Thread t = new Thread(new ServerSocketHandler(socket, stringModel));
        t.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
