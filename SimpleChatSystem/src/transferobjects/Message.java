package transferobjects;

import java.io.Serializable;

public class Message implements Serializable
{
  private String message;
  private String userName;

  public Message(String message, String userName)
  {
    this.message = message;
    this.userName = userName;
  }

  public String getMessage()
  {
    return message;
  }

  public String getUserName()
  {
    return userName;
  }
  @Override public String toString() {
    return userName + ": " + message;
  }

}
