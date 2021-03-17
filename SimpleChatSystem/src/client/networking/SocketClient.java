package client.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient
{
  private String username;

  public void start() {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      System.out.println("Enter username: ");
      System.out.print("> ");
      Scanner scanner = new Scanner(System.in);
      username = scanner.nextLine();
      System.out.println("Enter message: ");
      System.out.print("> ");
      String message = scanner.nextLine();

      ClientSocketHandler handler = new ClientSocketHandler(this, socket);
      new Thread(handler).start();

      handler.sendMessage(message);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getUsername()
  {
    return username;
  }

  public void messageReceived(String msg)
  {
    System.out.println(msg);
  }
}
