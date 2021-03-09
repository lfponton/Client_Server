import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain2
{
  public static void main(String[] args) throws IOException
  {
    try (ServerSocket serverSocket = new ServerSocket(1234);)
    {
      while (true) {
        try (Socket socket = serverSocket.accept();)
        {
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          System.out.println("Client connected from " + socket.getInetAddress().getHostAddress() + " " + socket.getPort());
          out.println("Hello from server. Write your name: ");
          String nameOfClient = in.readLine();
          out.println("Hello " + nameOfClient);
        }
      }
    }
  }
}
