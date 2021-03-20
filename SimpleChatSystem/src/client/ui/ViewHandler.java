package client.ui;

import client.networking.SocketClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene chatScene;
  private SocketClient client;

  public ViewHandler(SocketClient client)
  {
    this.client = client;
  }

  public void start() {
    stage = new Stage();
    openView();
  }

  private void openView()
  {
    if (chatScene == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/client/ui/ChatView.fxml"));
        Parent root = loader.load();

        ChatController controller =  loader.getController();
        controller.init(client, this);
        client.setController(controller);

        stage.setTitle("Simple Chat");
        chatScene = new Scene(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(chatScene);
    stage.show();
  }

  public Stage getStage()
  {
    return stage;
  }
}
