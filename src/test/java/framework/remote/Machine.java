package framework.remote;

public abstract class Machine {
    protected static final String WINDIR_SYSTEM_PROP_NAME = "WINDIR";
    protected static final String OS_NAME_SYSTEM_PROP_NAME = "os.name";

    protected enum OSName {
        win, macos, linux
    }
}
