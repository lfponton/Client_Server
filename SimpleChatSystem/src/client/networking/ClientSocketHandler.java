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
      while (true)
      {
        Message response = (Message) inFromServer.readObject();
        if (response.getMessage().equalsIgnoreCase("exit"))
        {
          socket.close();
          break;
        }
        client.messageReceived(response.getMessage());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(String message) throws IOException
  {
    //Message msg = new Message(message, client.getUsername());
    Message msg = new Message(message);
    outToServer.writeObject(msg);
  }
}
