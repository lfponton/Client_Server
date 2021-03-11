package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection implements Runnable
{
  private final Socket socket;

  public ServerConnection(Socket socket)
  {
    this.socket = socket;
  }

  @Override public void run()
  {
    try
    {
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

      while (true) {
        String read = (String) in.readObject(); //stuck here
        if (read.equalsIgnoreCase("exit"))
        {
          socket.close();
          break;
        }
        System.out.println("Received from client: " + read);
        String result = read.toUpperCase();

        out.writeObject(result);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
