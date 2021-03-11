import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LogInServer
{
  public static void main(String[] args) throws IOException
  {
    try (ServerSocket serverSocket = new ServerSocket(1234))
    {
      while (true)
      {
        try (Socket socket = serverSocket.accept())
        {
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          String message = in.readLine();
          if (!message.equals("connect"))
          {
            out.println("You will be disconnected.");
            socket.close();
          }
          out.println("You are connected!");

        }
      }
    }
  }
}
