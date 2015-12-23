import com.mashape.unirest.http.exceptions.UnirestException;
import controllers.utils.pojo.SyncMessagePojo.ClockPojo;
import controllers.utils.pojo.SyncMessagePojo.CommandPojo;
import controllers.utils.Service;
import controllers.utils.sender.AsyncMessageConsumer;
import controllers.utils.sender.SyncMessageSender;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Json;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application application) {
        if (application.isDev())
            Logger.info("start in dev mode");
        saveService();
        addCallback();
        subscribeQueue();
        super.onStart(application);
    }

    private void subscribeQueue()
    {
        try {
            AsyncMessageConsumer  consumer = new AsyncMessageConsumer(Service.SERVICE_NAME.toString());
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            Logger.info("consume message in queue {}", Service.SERVICE_NAME);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
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
