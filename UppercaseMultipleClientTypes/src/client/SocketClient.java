package client;

import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient
{
public void sendMessage(String msg)
  {
    {
      try
      {
        Socket socket = new Socket("localhost", 1234);

        ObjectOutputStream outToServer = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
          System.out.println("Request type?");
          String requestType = scanner.nextLine();
          System.out.println("Argument?");
          String argument = scanner.nextLine();
          Request request = new Request(argument, requestType);
          outToServer.writeObject(request);
          if ("Closeclient".equals(requestType)) {
            outToServer.close();
            inFromServer.close();
            socket.close();
            break;
          }
          Request response = (Request) inFromServer.readObject();
          System.out.println(response.getArgument());
        }
        /*
        String input = scanner.nextLine();

        Request.RequestType requestType;

        if ("uppercase".equals(input))
        {
          requestType = Request.RequestType.UPPERCASE;
        }
        else
        {
          requestType = Request.RequestType.LOWERCASE;
        }

        Request request = new Request(msg, requestType);
        out.writeObject(request);

        request = (Request) in.readObject();

        System.out.println(request.getArg());


         */
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }
}
