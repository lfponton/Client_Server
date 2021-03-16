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

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());

        String message = (String) in.readObject();
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
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

      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }
}
