package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
  public void start()
  {
    try
    {
      ServerSocket serverSocket = new ServerSocket(1234);
      ConnectionPool connectionPool = new ConnectionPool();

      System.out.println("Server started..");
      while (true)
      {
        Socket socket = serverSocket.accept();

        ChatServerConnection chatServerConnection = new ChatServerConnection(
            socket, connectionPool);
        connectionPool.addConnection(chatServerConnection);

        Thread t = new Thread(chatServerConnection);
        t.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
