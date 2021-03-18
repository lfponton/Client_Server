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
  @FXML private Button sendButton;
  @FXML private TextArea messagesArea;
  private SocketClient client;

  public void init(SocketClient client) {
    this.client = client;
  }

  public void sendMessageButton(ActionEvent actionEvent) {
    Message message = new Message(messageField.getText(), client.getUsername());

    client.sendMessage(message.toString());
  }

  public void updateMessageArea()
  {

  }

}
