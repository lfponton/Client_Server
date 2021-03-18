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
  private String username;

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

      while (true)
      {
        Message message = (Message) inFromClient.readObject();

        username = message.getUserName();

        System.out.println(message);

        if (message.getMessage().equals("exit"))
        {
          pool.removeConnection(this);
          outToClient.writeObject(message);
          socket.close();
          break;
        }
        pool.broadcast(message.toString());
      }
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
      outToClient.writeObject(new Message(message, username));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getClientUsername()
  {
    return username;
  }

}
