package controllers.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.utils.Pojo.AppPojo;
import controllers.utils.Pojo.ClockPojo;
import play.libs.Json;

public class SyncMessageSender {

    public static HttpResponse<String> addServiceOnMainService(ServiceName serviceName) throws UnirestException {
        Service service = Service.getInstances();

        return Unirest.post(service.getServiceHttpURL(ServiceName.MAIN_WS) + Service.MAIN_WS_PATH_APP)
                .header("Content-type", "application/json")
                .body(Json.toJson(new AppPojo(serviceName.toString(), service.getServiceHttpURL(serviceName), Service.SERVICE_PORT.toString())).toString())
                .asString();
    }

    public static HttpResponse<String> addCallbackOnMainService(String cron, String pathCallBack, String body, ServiceName serviceName, ClockPojo.RequestType requestType) throws UnirestException {
        Service service = Service.getInstances();

        return Unirest.post(service.getServiceHttpURL(ServiceName.MAIN_WS) + Service.MAIN_WS_PATH_CLOCK)
                .header("Content-type", "application/json")
                .body(Json.toJson(new ClockPojo(cron, pathCallBack, body, serviceName.toString(), requestType)).toString())
                .asString();
    }
}
