import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer1
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
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
          System.out.println("Client connected from " + socket.getInetAddress()
              .getHostAddress() + " " + socket.getPort());
          out.println("Hello from server. Enter a number: ");
          String firstNumber = in.readLine();
          out.println("Perfect. Now, enter the desired operator: ");
          String operator = in.readLine();
          out.println("Thanks! Finally, enter the last number: ");
          String lastNumber = in.readLine();

          int firstNumberInt = Integer.parseInt(firstNumber);
          int lastNumberInt = Integer.parseInt(lastNumber);
          int result = 0;

          if (operator.equals("+"))
          {
            result = firstNumberInt + lastNumberInt;
          }
          else if (operator.equals("*"))
          {
            result = firstNumberInt * lastNumberInt;
          }
          else if (operator.equals("-"))
          {
            result = firstNumberInt - lastNumberInt;
          }

          out.println("The result is: " + result);
        }
      }
    }
  }
}
