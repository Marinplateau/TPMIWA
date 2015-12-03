package controllers;


import controllers.utils.Pojo.CommandPojo;
import model.Command;
import play.libs.Json;
import play.mvc.Result;

import static play.mvc.Controller.request;
import static play.mvc.Results.*;

public class CommandController {
    public static Result getCommands() {
        System.out.println("call command");
        return ok(Json.toJson(Command.find.all()));
    }

    public static Result addCommand() {
        CommandPojo commandPojo = Json.fromJson(request().body().asJson(), CommandPojo.class);
        if (!commandPojo.valideForAdd())
            return badRequest();

        new Command(commandPojo.getRef(), commandPojo.getBody()).save();
        return created();
    }

    public static Result removeCommand() {
        CommandPojo commandPojo = Json.fromJson(request().body().asJson(), CommandPojo.class);
        if (!commandPojo.valideForRemove())
            return badRequest();
        Command.find.byId(commandPojo.getCommandId()).delete();

        return ok();
    }
}
