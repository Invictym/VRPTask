package framework.remote;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ThisMachine extends Machine {
    private ThisMachine() {
    }

    public static OSName getOSName() {
        String osNameProperty = System.getProperty(OS_NAME_SYSTEM_PROP_NAME);
        if (osNameProperty == null) {
            throw new RuntimeException("os.name property is not set");
        } else {
            osNameProperty = osNameProperty.toLowerCase();
        }
        if (osNameProperty.contains("win")) {
            return OSName.win;
        } else if (osNameProperty.contains("mac")) {
            return OSName.macos;
        } else if (osNameProperty.contains("linux") || osNameProperty.contains("nix") || osNameProperty.contains("nux") || osNameProperty.contains("aix")) {
            return OSName.linux;
        } else {
            throw new IllegalStateException(String.format("Your OS [%s] is not supported by this version of tool", osNameProperty));
        }
    }

    public static String getSlash() {
        OSName osName = getOSName();
        switch (osName) {
            case win:
                return "\\";
            case macos:
            case linux:
                return "/";
            default:
                throw new IllegalStateException(String.format("Your OS [%s] is not supported by this version of tool", osName));
        }
    }

    public static String getTmpDir() {
        switch (ThisMachine.getOSName()) {
            case win:
                return "c:\\users\\public";
            case linux:
            case macos:
                return "/tmp";
            default:
                throw new IllegalStateException("Unknown OS type: " + ThisMachine.getOSName());
        }
    }

    /**
     * @return vpn ip of current machine.
     */
    public static String getIP() {
        //todo
        throw new UnsupportedOperationException("not implemented");
    }

    public static String getNewline() {
        OSName osName = getOSName();
        switch (osName) {
            case win:
                return "\r\n";
            case macos:
                return "\r";
            case linux:
                return "\n";
            default:
                throw new IllegalStateException(String.format("Your OS [%s] is not supported by this version of tool", osName));
        }
    }

    public static String getPathToHost() {
        OSName osName = getOSName();
        switch (osName) {
            case win:
                return System.getenv(WINDIR_SYSTEM_PROP_NAME) + "\\system32\\drivers\\etc\\hosts";
            case macos:
            case linux:
                return "/etc/hosts";
            default:
                throw new IllegalStateException(String.format("Your OS [%s] is not supported by this version of tool", osName));
        }
    }

    public static byte[] loadResource(String resourcePath) {
        try {
            return Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resourcePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createFile(String path, String content) {
        createFile(path, content, false);
    }

    public static void createFile(String path, byte[] content) {
        createFile(path, content, false);
    }

    public static void createFile(String path, String content, boolean force) {
        createFile(path, content.getBytes(), force);
    }

    public static void createFile(String path, byte[] content, boolean force) {
        String localizedPath=osPath(path);
        if (force){
            new File(new File(localizedPath).getParentFile().getAbsolutePath()).mkdirs();
        }
    }

    public static String osPath(String path){
        return path.replace("\\", getSlash())
                .replace("/", getSlash());
    }

    public static int findFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore IOException on close()
            }
            return port;
        } catch (IOException e) {
        }
        throw new IllegalStateException("Can not find a free port");
    }
}
