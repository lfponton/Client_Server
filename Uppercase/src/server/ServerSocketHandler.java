package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out.println("Hello from server. Write your name:");
      String nameFromClient = in.readLine();
      out.println("Hello " + nameFromClient + "!");
      out.println("Please give me a string to convert to uppercase:");
      String message = in.readLine();
      while (!message.equals("stop"))
      {
        out.println(message.toUpperCase());
        out.println("More strings! Otherwise, enter 'stop':");
        message = in.readLine();
      }
      out.println("Bye!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
