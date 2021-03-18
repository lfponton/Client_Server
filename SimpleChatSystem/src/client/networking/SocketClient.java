package client.networking;

import transferobjects.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient
{
  private String username;
  private ClientSocketHandler handler;

  public void start() {
    try
    {
      Socket socket = new Socket("localhost", 1234);
      handler = new ClientSocketHandler(this, socket);
      new Thread(handler).start();

      System.out.println("Enter username: ");
      System.out.print("> ");
      Scanner scanner = new Scanner(System.in);
      username = scanner.nextLine();
      while (true)
      {
        System.out.println("Enter message: ");
        System.out.print("> ");
        String message = scanner.nextLine();
        sendMessage(message);
        if (message.equalsIgnoreCase("exit")) {
          break;
        }
      }
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

  public void sendMessage(String msg) {
    try
    {
      handler.sendMessage(msg);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void messageReceived(String msg)
  {
    System.out.println(msg);
  }
}
