package client;

public class RunClient
{
  public static void main(String[] args)
  {
    SocketClient socketClient = new SocketClient();

    socketClient.sendMessage("Wow");
  }
}
