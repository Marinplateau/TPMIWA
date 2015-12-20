import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.utils.Pojo.ClockPojo;
import controllers.utils.Pojo.CommandPojo;
import controllers.utils.Service;
import controllers.utils.ServiceName;
import controllers.utils.SyncMessageSender;
import play.Application;
import play.GlobalSettings;
import play.libs.Json;

public class Global extends GlobalSettings {



    @Override
    public void onStart(Application application) {
        if (application.isDev())
            System.out.println("start in dev mode");
        saveService();
        addCallback();
        super.onStart(application);
    }

    private void addCallback() {
        try {
            System.out.println("add callback for get");
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", "", Service.SERVICE_NAME, ClockPojo.RequestType.GET);
            System.out.println("add callback for post");
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", Json.toJson(new CommandPojo("ddee", "fefef")).toString(), Service.SERVICE_NAME, ClockPojo.RequestType.POST);
            System.out.println("add callback for delete");
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", Json.toJson(new CommandPojo(12L)).toString(), Service.SERVICE_NAME, ClockPojo.RequestType.DELETE);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void saveService() {
        try {
            SyncMessageSender.addServiceOnMainService(Service.SERVICE_NAME);
            System.out.println("record on WS");

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
