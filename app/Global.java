import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.utils.pojo.SyncMessagePojo.ClockPojo;
import controllers.utils.pojo.SyncMessagePojo.CommandPojo;
import controllers.utils.Service;
import controllers.utils.sender.SyncMessageSender;
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
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", "", Service.SERVICE_NAME, ClockPojo.RequestType.GET);
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", Json.toJson(new CommandPojo("ddee", "fefef")).toString(), Service.SERVICE_NAME, ClockPojo.RequestType.POST);
            SyncMessageSender.addCallbackOnMainService(" 0 * * * * *", "/command", Json.toJson(new CommandPojo(12L)).toString(), Service.SERVICE_NAME, ClockPojo.RequestType.DELETE);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void saveService() {
        try {
            SyncMessageSender.addServiceOnMainService(Service.SERVICE_NAME);

        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
