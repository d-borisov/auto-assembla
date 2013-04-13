package mail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Properties;


public class MailSender {

  private final MailSettings settings;

  public MailSender(MailSettings settings) {
    this.settings = settings;
  }

  public void send(MailData data) throws MessagingException {
    final Session session = configSession(settings);
    sendMessage(data, session);
  }

  public void send(Collection<MailData> data) throws MessagingException {
    final Session session = configSession(settings);
    for (MailData message : data) {
      sendMessage(message, session);
    }
  }

  private static Session configSession(final MailSettings settings) {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", settings.smtpHost);
    properties.put("mail.smtp.socketFactory.port", settings.smtpSocketFactoryPort);
    properties.put("mail.smtp.socketFactory.class", settings.smtpSocketFactoryClass);
    properties.put("mail.smtp.auth", settings.smtpAuth);
    properties.put("mail.smtp.port", settings.smtpPort);

    return Session.getDefaultInstance(properties,
        new Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(settings.login, settings.password);
          }
        });
  }

  private static void sendMessage(MailData data, Session session) throws MessagingException {
    final Message message = new MimeMessage(session);
    message.setFrom(data.getFrom());
    message.setRecipients(Message.RecipientType.TO, data.getTo());
    message.setSubject(data.getSubject());
    message.setText(data.getText());

    Transport.send(message);
  }

}
