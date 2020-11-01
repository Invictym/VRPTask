package framework.entity;

public class BrowserValues {

    private String url;
    private int timeout;
    private String browse;
    private boolean remote;

    public BrowserValues(String url, int timeout, String browse, boolean remote) {
        this.url = url;
        this.timeout = timeout;
        this.browse = browse;
        this.remote = remote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }
}
