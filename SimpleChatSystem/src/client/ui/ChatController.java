package client.ui;

import client.networking.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import transferobjects.Message;

public class ChatController
{

  @FXML private TextField messageField;
  @FXML private Button sendMessageButton;
  @FXML private TextArea messagesArea;
  private SocketClient client;
  private ViewHandler viewHandler;

  public void init(SocketClient client, ViewHandler viewHandler) {
    this.client = client;
    this.viewHandler = viewHandler;
  }

  public void sendMessageButton(ActionEvent actionEvent) {

    //Message message = new Message(messageField.getText(), client.getUsername());
    Message message = new Message(messageField.getText());
    client.sendMessage(message.getMessage());
    messageField.clear();
    if (message.getMessage().equalsIgnoreCase("exit"))
    {
      viewHandler.getStage().close();
    }
  }

  public void updateMessageArea(Message message)
  {
    messagesArea.appendText(message.toString() + "\n");
  }

}
