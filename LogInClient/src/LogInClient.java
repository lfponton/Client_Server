import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LogInClient
{
  public static void main(String[] args) throws IOException
  {
    Socket socket = new Socket("localhost", 1234);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter 'connect' or 'disconnect': ");
    System.out.print("> ");
    String message = scanner.nextLine();
    out.println(message);
    message = in.readLine();
    System.out.println(message);
  }
}
