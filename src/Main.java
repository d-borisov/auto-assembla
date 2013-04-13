import log.Log;
import log.WorkException;
import mail.MailAuth;
import mail.MailData;
import mail.MailSender;
import mail.MailSettings;
import settings.AppSettings;
import settings.DataParser;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import java.util.Collection;

public class Main {

  public static void main(String[] args) {
    try {
      AppSettings settings = readSettings();
      Collection<MailData> mailData = DataParser.parse(settings);
      sendMail(settings, mailData);
      Log.log("Done");
    } catch (WorkException e) {
      Log.warn(e);
    }
  }

  private static void sendMail(MailAuth auth, Collection<MailData> mailData) throws WorkException {
    try {
      new MailSender(new MailSettings(auth)).send(mailData);
    } catch (MessagingException e) {
      if (e instanceof AuthenticationFailedException) {
        throw WorkException.onLogicError("On mail sending", "Invalid pair login-password");
      }
      throw WorkException.onException("On mail sending", e);
    }
  }

  private static AppSettings readSettings() throws WorkException {
    return new AppSettings("", "");
  }
}
