package server;

import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private ObjectInputStream inFromClient;
  private ObjectOutputStream outToClient;
  private SocketServer socketServer;
  private Socket socket;
  private StringModel stringModel;

  public ServerSocketHandler(Socket socket, SocketServer server)
  {
    this.socket = socket;
    socketServer = server;
    try
    {
      inFromClient = new ObjectInputStream(socket.getInputStream());
      outToClient = new ObjectOutputStream(socket.getOutputStream());
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
      while (true)
      {
        Request request = (Request) inFromClient.readObject();
        String result = "N/A";
        if ("Uppercase".equals(request.getRequestType()))
        {
          result = socketServer.convertToUppercase(request.getArgument());
        }
        else if ("Lowercase".equals(request.getRequestType()))
        {
          result = socketServer.convertToLowercase(request.getArgument());
        }
        else if ("Closeclient".equals(request.getRequestType()))
        {
          inFromClient.close();
          outToClient.close();
          socket.close();
          break;
        }
        Request response = new Request(result, "Response");
        outToClient.writeObject(response);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }


    /*
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

     */
  }
}
