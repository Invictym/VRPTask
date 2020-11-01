package framework.logger;

import framework.config.TestEnv;
import framework.remote.ThisMachine;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static String currentLogDir;
    private static FileAppender currentFileAppender;

    private Log() {

    }

    public synchronized static void info(String message) {
        Logger.getLogger(getCallingClassName()).info(message);
    }

    public synchronized static void info(String template, Object... args) {
        info(String.format(template, args));
    }

    public synchronized static void info(Object message) {
        info(message.toString());
    }


    public synchronized static void warn(String message) {
        Logger.getLogger(getCallingClassName()).warn(message);
    }

    public synchronized static void warn(String template, Object... args) {
        warn(String.format(template, args));
    }

    public synchronized static void warn(Object message) {
        warn(message.toString());
    }


    public synchronized static void error(String message) {
        Logger.getLogger(getCallingClassName()).error(message);
    }

    public synchronized static void error(String template, Object... args) {
        error(String.format(template, args));
    }

    public synchronized static void error(Object message) {
        error(message.toString());
    }

    public synchronized static void error(String message, Exception e) {
        error(message + '\n' + e.getLocalizedMessage());
    }


    public synchronized static void debug(String message) {
        Logger.getLogger(getCallingClassName()).debug(message);
    }

    public synchronized static void debug(String template, Object... args) {
        debug(String.format(template, args));
    }

    public synchronized static void debug(Object message) {
        debug(message.toString());
    }


    public synchronized static String getRootDir() {
        if (currentLogDir == null) {
            generateCurrentLogDir();
        }
        return currentLogDir;
    }

    public synchronized static void backupToFile(String relativePath, byte[] content) {
        String fullPath = getRootDir() + ThisMachine.getSlash() + relativePath;
        ThisMachine.createFile(fullPath, content, true);
    }

    public synchronized static void backupToFile(String relativePath, String content) {
        backupToFile(relativePath, content.getBytes());
    }

    private static String getCallingClassName() {
        getRootDir(); //make sure that log dir initialized
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String fullClassName = stackTrace[2].getClassName();
        return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
    }

    private static void generateCurrentLogDir() {
        TestEnv env = TestEnv.get();
        SimpleDateFormat sdf = new SimpleDateFormat(env.getLogTemplate());
        String name;
        try {
            name = "log_" + sdf.format(new Date());
        } catch (Exception e) {
            name = "log_" + System.currentTimeMillis();
        }

        currentLogDir = String.format("%s%s%s",
                env.getLogRoot(),
                ThisMachine.getSlash(),
                name);
        try {
            //set automation.log file location into new generated dir
            Logger.getRootLogger().removeAppender(currentFileAppender);
            currentFileAppender = new FileAppender(new PatternLayout("%d{HH:mm:ss,SSS} %-5p [%27c{1}] - %m%n"), currentLogDir + "/automation.log", false);
            Logger.getRootLogger().addAppender(currentFileAppender);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
