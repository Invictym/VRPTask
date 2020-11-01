package framework.config;

import framework.entity.BrowserValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class AutomationAppContext {
    private volatile static ApplicationContext ctx;

    private AutomationAppContext() {
    }

    public static ApplicationContext get() {
        if (ctx == null) {
            synchronized (AutomationAppContext.class) {
                if (ctx == null) {
                    ctx = new AnnotationConfigApplicationContext("framework.config");
                }
            }
        }
        return ctx;
    }

    public static BrowserValues getBrowserValues() {
        return  (BrowserValues) get().getBean("browserValues");
    }


}
