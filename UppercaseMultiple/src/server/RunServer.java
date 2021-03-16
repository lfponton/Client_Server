package server;

public class RunServer
{
  public static void main(String[] args)
  {
    SocketServer socketServer = new SocketServer();
    socketServer.startServer();
  }
}
