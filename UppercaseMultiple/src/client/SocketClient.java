package client;

import transferobjects.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient
{
  public void sendMessage(String msg) {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      Message message = new Message(msg);

      out.writeObject(message);

      message = (Message) in.readObject();

      System.out.println(message.getMsg());

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
