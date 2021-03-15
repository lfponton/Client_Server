package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
  public void runClient() {
    // connect to server
    try {
      Socket socket = new Socket("localhost", 1234);
      System.out.println("Connected");
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      while (true)
      {
        // send message to server
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type message >");

        String s = scanner.nextLine();
        out.writeObject(s);
        if (s.equalsIgnoreCase("exit"))
        {
          socket.close();
          break;
        }
        // read result from server

        String result = (String) in.readObject();
        System.out.println("From server: " + result);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
