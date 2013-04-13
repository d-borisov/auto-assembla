package log;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {

  private static final Logger log = Logger.getLogger(Log.class.getName());

  public static void log(LoggingString message) {
    addToLog(Level.INFO, message.apply());
  }

  public static void logEx(String message) {
    log(() -> message);
  }

  public static void log(String message) {
    addToLog(Level.INFO, message);
  }

  public static void warn(String message) {
    addToLog(Level.WARNING, message);
  }

  private static void addToLog(Level level, String message, Object... objects) {
    log.log(level, String.format(message, objects));
  }

  public static void warn(WorkException e) {
    addToLog(Level.WARNING, "%s\n%s", e.getRoutineName(), e.getReason());
  }
}
