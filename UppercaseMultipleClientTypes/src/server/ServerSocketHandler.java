package server;

import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{

  private final Socket socket;
  private StringModel stringModel;

  public ServerSocketHandler(Socket socket, StringModel stringModel) {
    this.socket = socket;
    this.stringModel = stringModel;
  }

  @Override public void run()
  {
    try
    {
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      String message = "Uppercase or lowercase?";
      out.writeObject(message);

      Request request = (Request) in.readObject();

      String str;
      if (request.getRequestType() == Request.RequestType.UPPERCASE)
      {
        str = stringModel.toUpperCase(request.getArg());
      }
      else
      {
        str = stringModel.toLowerCase(request.getArg());
      }

      request = new Request(str, request.getRequestType());

      out.writeObject(request);
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

}
