package net.scottrowley.skillsmanager.response;

import java.util.Map;

public class ErrorResponse {
  private String code;
  private String message;
  private Map<String, String> data;

  public ErrorResponse(final String code, final String message, final Map<String, String> data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public Map<String, String> getData() {
    return data;
  }

  public void setData(final Map<String, String> data) {
    this.data = data;
  }
}
