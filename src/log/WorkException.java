package log;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class WorkException extends Exception {

  private final String routineName;
  private final String reasonDescription;
  private final Throwable reasonException;

  private WorkException(String routineName, String reasonDescription, Throwable reasonException) {
    super();
    this.routineName = routineName;
    this.reasonDescription = reasonDescription;
    this.reasonException = reasonException;
  }

  public static WorkException onLogicError(String routineName, String reason) {
    return new WorkException(routineName, reason, null);
  }

  public static WorkException onException(String routineName, Throwable reason) {
    return new WorkException(routineName, null, reason);
  }

  public String getRoutineName() {
    return routineName;
  }

  public String getReason() {
    final String forException = reasonException != null ? ExceptionUtils.getStackTrace(reasonException) : "";
    return reasonDescription != null ? reasonDescription + "\n" + forException : forException;
  }
}
