package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private ObjectOutputStream outToClient;
  private ObjectInputStream inFromClient;
  private int id;

  public ServerSocketHandler(Socket socket, int id)
  {
    this.socket = socket;
    this.id = id;
    try
    {
      outToClient = new ObjectOutputStream(socket.getOutputStream());
      inFromClient = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
      outToClient.writeObject(String.valueOf(id));
      while (true)
      {
        System.out.println(
            "Client connected from " + socket.getInetAddress().getHostAddress()
                + " " + socket.getPort());
        String readObject = (String) inFromClient.readObject();
        System.out.println(readObject);
        String response = "And hello to you, client number " + id;
        outToClient.writeObject(response);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}