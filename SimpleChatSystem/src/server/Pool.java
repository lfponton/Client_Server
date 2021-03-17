package server;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<ServerSocketHandler> connections = new ArrayList<>();

  public synchronized void addConnection(ServerSocketHandler handler)
  {
    connections.add(handler);
  }

  public synchronized void broadcast(String message) {
    for (ServerSocketHandler connection : connections) {
      connection.sendMessage(message);
    }
  }
}
