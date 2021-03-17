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
        System.out.println("Waiting for client");
        Socket socket = serverSocket.accept();

        //StringModel stringModel = new StringModel();

        System.out.println("Client connected from " + socket.getInetAddress()
        .getHostAddress() + " " + socket.getLocalPort());

        ServerSocketHandler serverSocketHandler = new ServerSocketHandler(
            socket, this);

        new Thread(serverSocketHandler).start();
        //Thread t = new Thread(new ServerSocketHandler(socket, stringModel));
        //t.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String convertToUppercase(String argument) {
      return argument.toUpperCase();
  }

  public String convertToLowercase(String argument)
  {
    return argument.toLowerCase();
  }
}
