import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.utils.Pojo.AppPojo;
import controllers.utils.Pojo.ClockPojo;
import controllers.utils.Pojo.CommandPojo;
import controllers.utils.Service;
import play.Application;
import play.GlobalSettings;
import play.libs.Json;

public class Global extends GlobalSettings {

    private String SERVICE_NAME;


    @Override
    public void onStart(Application application) {
        if (application.isDev())
            System.out.println("start in dev mode");
        this.SERVICE_NAME = Service.getInstances().getTPMIWA();
        saveService();
        addCallback();
        super.onStart(application);
    }

    private void addCallback() {
        Service service = Service.getInstances();
        String urlMainWS = service.getServiceHttpURL(Service.MAIN_WS);
        try {
            System.out.println("add callback for get");
//            Unirest.post(urlMainWS + Service.MAIN_WS_PATH_CLOCK)
//            .header("Content-type", "application/json")
//                    .body(Json.toJson(new ClockPojo(" 0 * * * * *", "/command", "", SERVICE_NAME, ClockPojo.RequestType.GET)).toString())
//                    .asString();

            System.out.println("add callback for post");
            Unirest.post(urlMainWS + Service.MAIN_WS_PATH_CLOCK)
                    .header("Content-type", "application/json")
                    .body(Json.toJson(new ClockPojo(" 0 * * * * *", "/command", Json.toJson(new CommandPojo("ddee", "fefef")).toString(), SERVICE_NAME, ClockPojo.RequestType.POST)).toString())
                    .asString();
//
            System.out.println("add callback for delete");
//            Unirest.post(urlMainWS + Service.MAIN_WS_PATH_CLOCK)
//                    .header("Content-type", "application/json")
//                    .body(Json.toJson(new ClockPojo(" 0 * * * * *", "/command", Json.toJson(new CommandPojo(12L)).toString(), SERVICE_NAME, ClockPojo.RequestType.DELETE)).toString())
//                    .asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void saveService() {
        try {
            Service service = Service.getInstances();
            Unirest.post(service.getServiceHttpURL(Service.MAIN_WS) + Service.MAIN_WS_PATH_APP)
                    .header("Content-type", "application/json")
                    .body(Json.toJson(new AppPojo(SERVICE_NAME, service.getServiceHttpURL(SERVICE_NAME), service.getPort().toString())).toString())
                    .asString();
            System.out.println("Sended service request");

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
