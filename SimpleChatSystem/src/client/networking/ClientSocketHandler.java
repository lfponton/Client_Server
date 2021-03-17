package client.networking;

import transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private SocketClient client;
  private Socket socket;
  private  ObjectOutputStream outToServer;

  public ClientSocketHandler(SocketClient client, Socket socket)
      throws IOException
  {
    this.client = client;
    this.socket = socket;
    outToServer = new ObjectOutputStream(socket.getOutputStream());
  }

  @Override public void run()
  {
    try
    {
      ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
      String username = (String) inFromServer.readObject();
      String message = (String) inFromServer.readObject();
      Message response = new Message(message, username);
      client.messageReceived(message.toString());
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message) throws IOException
  {
    Message msg = new Message(message, client.getUsername());
    outToServer.writeObject(msg);
  }
}
