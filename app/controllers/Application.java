package controllers;

import controllers.utils.Service;
import controllers.utils.pojo.AsyncMessagePojo.SimpleMessagePojo;
import controllers.utils.sender.AsyncMessageConsumer;
import controllers.utils.sender.AsyncMessageProducer;
import controllers.utils.sender.FtpSender;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result upload() {
        System.out.println("in upload controller");
        try {
            FtpSender.sendFile(Play.application().getFile("conf/prod.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok();
    }

    public static Result sendAsyncMessage() {
        try {
            AsyncMessageProducer producer = new AsyncMessageProducer(Service.SERVICE_NAME.toString());
            producer.sendMessage(new SimpleMessagePojo("hello world"));
            Logger.info("Message sent");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return ok();
    }

}
