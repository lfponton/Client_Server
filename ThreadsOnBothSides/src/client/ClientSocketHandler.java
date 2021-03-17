package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private Client client;
  private Socket socket;
  private ObjectOutputStream outToServer;
  private String id;

  public ClientSocketHandler(Client client, Socket socket)
  {
    this.client = client;
    this.socket = socket;
  }

  @Override public void run()
  {
    try
    {
      ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
      outToServer = new ObjectOutputStream(socket.getOutputStream());
      id = (String) inFromServer.readObject();
      while (true) {
        String readFromServer = (String) inFromServer.readObject();
        client.handleStringFromServer(readFromServer);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
  public void sendToServer(String message) {
    try
    {
      outToServer.writeObject(message + " " + id);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
