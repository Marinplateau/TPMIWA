package controllers;

import controllers.utils.sender.FtpSender;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.io.IOException;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result upload()
    {
        System.out.println("in upload controller");
        try {
            FtpSender.sendFile(Play.application().getFile("conf/prod.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok();
    }

}
