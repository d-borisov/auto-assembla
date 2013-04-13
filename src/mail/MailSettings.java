package mail;

public final class MailSettings {

  public final String smtpHost = "smtp.gmail.com";
  public final String smtpSocketFactoryPort = "465";
  public final String smtpSocketFactoryClass = "javax.net.ssl.SSLSocketFactory";
  public final String smtpAuth = "true";
  public final String smtpPort = "465";

  public final String login;
  public final String password;

  public MailSettings(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public MailSettings(MailAuth auth) {
    this.login = auth.getLogin();
    this.password = auth.getPassword();
  }
}
