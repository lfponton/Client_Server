import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient1
{
  public static void main(String[] args) throws IOException
  {
    Socket socket = new Socket("localhost", 1234);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    String message = in.readLine();
    System.out.println(message);
    Scanner scanner = new Scanner(System.in);
    String firstNumber = scanner.nextLine();
    out.println(firstNumber);
    message = in.readLine();
    System.out.println(message);
    String operator = scanner.nextLine();
    out.println(operator);
    message = in.readLine();
    System.out.println(message);
    String lastNumber = scanner.nextLine();
    out.println(lastNumber);
    message = in.readLine();
    System.out.println(message);


  }
}
