package server;

import shared.Message;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private List<ChatServerConnection> connections = new ArrayList<>();

  public void addConnection(ChatServerConnection chatServerConnection) {
    connections.add(chatServerConnection);
  }

  public void broadcast(Message message) {
    for (ChatServerConnection connection : connections) {
      if (!connection.getClientName().equals(message.getUser()))
      {
        connection.sendMessageToClient(message);
      }
    }
  }

  public void removeConnection(ChatServerConnection chatServerConnection)
  {
    connections.remove(chatServerConnection);
  }
}
