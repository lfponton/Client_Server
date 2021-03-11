package client;

import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
  private ObjectInputStream in;
  private Socket socket;

  public void startClient() {
    try
    {
      socket =new Socket("localhost", 1234);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());

      Thread t = new Thread(() ->listenToServer());
      t.start();

      Scanner scanner = new Scanner(System.in);
      System.out.println("Insert user name >");

      String userName = scanner.nextLine();
      out.writeObject(userName);

      while (true) {
        System.out.println("Input > ");
        String input = scanner.nextLine();
        Message message = new Message(input, userName);
        out.writeObject(message);

        if (input.equalsIgnoreCase("exit")) {
          break;
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void listenToServer()
  {
    try
    {
      while (true)
      {
        Message response = (Message) in.readObject();
        if (response.getMessageBody().equalsIgnoreCase("exit")) {
          socket.close();
          break;
        }
        System.out.println(response);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
