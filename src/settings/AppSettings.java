package settings;

import mail.MailAuth;

public class AppSettings implements MailAuth {

  private final String login;
  private final String password;

  public AppSettings(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }
}
