package client;

import java.io.IOException;
import java.net.Socket;

public class Client
{
  private ClientSocketHandler handler;

  public void start()
  {
    try {
      Socket socket = new Socket("localhost", 1234);

      this.handler = new ClientSocketHandler(this, socket);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
