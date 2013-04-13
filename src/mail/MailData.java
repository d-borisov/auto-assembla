package mail;

import javax.mail.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MailData {
  private final Address from;
  private final List<Address> to;
  private final String subject;
  private final String text;

  public <T extends Address> MailData(Address from, Collection<T> to, String subject, String text) {
    this.from = from;
    this.to = new ArrayList<Address>(to);
    this.subject = subject;
    this.text = text;
  }

  public Address getFrom() {
    return from;
  }

  public Address[] getTo() {
    return to.toArray(new Address[to.size()]);
  }

  public String getSubject() {
    return subject;
  }

  public String getText() {
    return text;
  }
}
