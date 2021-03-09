import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain1
{
  public static void main(String[] args)
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));

      Scanner scanner = new Scanner(System.in);

      String message = in.readLine();
      System.out.println(message);
      System.out.print("> ");
      String name = scanner.nextLine();
      out.println(name);
      message = in.readLine();
      System.out.println(message);
      message = in.readLine();
      System.out.println(message);
      System.out.print("> ");
      String stringToUppercase = scanner.nextLine();
      out.println(stringToUppercase);
      message = in.readLine();
      while (!stringToUppercase.equals("stop"))
      {
        System.out.println("How many times should I print it?");
        System.out.print("> ");
        int numberOfTimes = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfTimes; i++)
        {
          System.out.println(message);
        }
        message = in.readLine();
        System.out.println(message);
        System.out.print("> ");
        stringToUppercase = scanner.nextLine();
        out.println(stringToUppercase);
        message = in.readLine();
      }
      System.out.println(message);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
