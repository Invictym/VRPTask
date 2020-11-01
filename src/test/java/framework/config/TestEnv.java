package framework.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestEnv {
    @Autowired
    private Environment env;

//    @Value("${logger.root.dir:#{T(com.ca.automation.dxi.remote.ThisMachine).getTmpDir()}}")
    @Value("${logger.root.dir:logs}")
    private String logRoot;
    @Value("${logger.timestamp.pattern:yyyy-MM-dd_hh-mm-ss}")
    private String logTemplate;

    public String getLogRoot() {
        return logRoot;
    }

    public String getLogTemplate() {
        return logTemplate;
    }

    private static TestEnv testProperties;

    public static TestEnv get() {
        if (testProperties == null) {
            testProperties = (TestEnv) AutomationAppContext.get().getBean("testEnv");
        }
        return testProperties;
    }
}
