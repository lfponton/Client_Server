package client;

import client.networking.SocketClient;

public class RunClient
{
  public static void main(String[] args)
  {
    SocketClient client = new SocketClient();
    client.start();
  }
}
