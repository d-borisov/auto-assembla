package settings;

import log.WorkException;
import mail.MailData;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataParser {

  public static Collection<MailData> parse(AppSettings settings) throws WorkException {
    final Address from;
    final List<InternetAddress> to = new ArrayList<>();
    try {
      from = new InternetAddress("opportune.flander@gmail.com");
    } catch (AddressException e) {
      throw WorkException.onException("On parsing FROM address", e);
    }
    try {
      to.add(new InternetAddress("auto-assembla@tickets.assembla.com"));
    } catch (AddressException e) {
      throw WorkException.onException("On parsing TO address", e);
    }

    return Collections.singleton(new MailData(from, to, getSubject(), getText()));
  }

  private static String getText() {
    return "Milestone: Автоматизация создания тикетов\n" +
        "Component: Mailer\n" +
        "Priority: 5\n" +
        "Assigned-to: \n" +
        "Permission: Private\n" +
        "Estimate: 0.0\n" +
        "Тип: Исследование\n" +
        "Description:\n" +
        "Описание тикета.\n" +
        "Чаще всего: \"Тикет, чтобы списывать время\".\n" +
        ".";
  }

  private static String getSubject() {
    return "Еще один тикет из приложения";
  }
}
