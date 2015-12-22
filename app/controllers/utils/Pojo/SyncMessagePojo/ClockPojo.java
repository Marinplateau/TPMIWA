package controllers.utils.pojo.SyncMessagePojo;

public class ClockPojo implements SyncMessagePojo {
    private String cron;
    private String endpoint;
    private String message;
    private String service_name;
    private RequestType request_type;

    public ClockPojo(String cron, String endpoint, String message, String service_name, RequestType request_type) {
        this.cron = cron;
        this.endpoint = endpoint;
        this.message = message;
        this.service_name = service_name;
        this.request_type = request_type;
    }

    public String getCron() {
        return cron;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMessage() {
        return message;
    }

    public String getService_name() {
        return service_name;
    }

    public RequestType getRequest_type() {
        return request_type;
    }

    public static enum  RequestType{
        GET, POST, PUT, DELETE;
    }
}
