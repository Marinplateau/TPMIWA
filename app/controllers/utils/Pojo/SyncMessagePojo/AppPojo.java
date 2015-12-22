package controllers.utils.pojo.SyncMessagePojo;


public class AppPojo implements SyncMessagePojo {
    private String name;
    private String hostname;
    private String port;

    public AppPojo(String name, String hostname, String port) {
        this.name = name;
        this.hostname = hostname;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "AppPojo{" +
                "name='" + name + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
