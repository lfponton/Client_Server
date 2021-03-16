package server;

import transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private final Socket socket;

  public ServerSocketHandler(Socket socket)
  {
    this.socket = socket;
  }

  @Override public void run()
  {
    try
    {
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


      Message message = (Message) in.readObject();

      message = new Message(message.getMsg().toUpperCase());
      out.writeObject(message);
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }
}
