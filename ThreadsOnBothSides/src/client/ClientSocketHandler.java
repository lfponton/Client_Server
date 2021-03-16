package client;

import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private Client client;
  private Socket socket;

  public ClientSocketHandler(Client client, Socket socket)
  {
    this.client = client;
    this.socket = socket;
  }

  @Override public void run()
  {

  }
}
