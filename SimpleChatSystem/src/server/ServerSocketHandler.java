package server;

import transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Pool pool;
  private Socket socket;
  private ObjectOutputStream outToClient;
  public ServerSocketHandler(Socket socket, Pool pool) {
    this.socket = socket;
    this.pool = pool;
  }

  @Override public void run()
  {
    try {
      outToClient = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());

      System.out.println("Client connected from " + socket.getInetAddress().getHostAddress()
      + " " + socket.getPort());

      Message message = (Message) inFromClient.readObject();

      System.out.println(message);

      pool.broadcast(message.getUserName());
      pool.broadcast(message.getMessage());

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message)
  {
    try
    {
      outToClient.writeObject(message);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
