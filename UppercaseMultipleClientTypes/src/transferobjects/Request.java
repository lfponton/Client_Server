package transferobjects;

import java.io.Serializable;

public class Request implements Serializable
{
  private String arg;
  private RequestType requestType;

  public Request(String arg, RequestType requestType)
  {
    this.arg = arg;
    this.requestType = requestType;
  }

  public String getArg()
  {
    return arg;
  }

  public RequestType getRequestType()
  {
    return requestType;
  }

  public enum RequestType {
    UPPERCASE, LOWERCASE
  }

}
