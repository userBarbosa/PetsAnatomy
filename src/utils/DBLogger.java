package utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DBLogger {

  final String loggerLib = "org.mongodb.driver";
  final Logger lgr = Logger.getLogger(loggerLib);
  final String path = "databaselogs.log";

  public void setupLogger() {
    System.out.println("Logger setup initialized.");
    LogManager.getLogManager().reset();
    lgr.setLevel(Level.ALL);

    ConsoleHandler ch = new ConsoleHandler();
    ch.setLevel(Level.WARNING);
    lgr.addHandler(ch);

    FileHandler fh;
    try {
      fh = new FileHandler(path);
      fh.setFormatter(new SimpleFormatter());
      fh.setLevel(Level.ALL);
      lgr.addHandler(fh);
    } catch (Exception e) {
      lgr.log(Level.SEVERE, "Error on logger class\n", e);
    }
  }
}
