package client.ui;

import client.networking.SocketClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatApplication extends Application
{
  private SocketClient client = new SocketClient();

  @Override public void start(Stage stage) throws Exception
  {
    client.start();
    ViewHandler viewHandler = new ViewHandler(client);
    viewHandler.start();
  }
}
