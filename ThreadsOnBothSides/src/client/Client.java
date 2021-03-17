package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
  private ClientSocketHandler handler;

  public void start()
  {
    try {
      Socket socket = new Socket("localhost", 1234);
      ClientSocketHandler handler = new ClientSocketHandler(this,
          socket);
      new Thread(handler).start();

      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();

      while (true) {
        handler.sendToServer("Hello from client");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void handleStringFromServer(String readFromServer)
  {
    System.out.println(readFromServer);
  }
}
