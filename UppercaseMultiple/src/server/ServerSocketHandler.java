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
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(new ObjectInputStream(socket.getInputStream()));

      while (true)
      {
        Message message = (Message) in.readObject();
        Message uppercase = new Message(message.getMsg().toUpperCase());

        out.writeObject(uppercase);
        System.out.println(uppercase);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }
}
