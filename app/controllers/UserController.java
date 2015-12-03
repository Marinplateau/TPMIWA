package controllers;

import model.User;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.created;
import static play.mvc.Results.ok;


public class UserController {

    public static Result getUser() {
        List<User> users = User.find.all();
        return play.mvc.Results.TODO;
    }

    public static Result getUserJson() {
        List<User> users = User.find.all();
        return ok(Json.toJson(users));
    }

    public static Result getSpecificUser(long userId) {
        User user = User.find.byId(userId);
        return ok();
    }

    public static Result getSpecificUserFormLoginAndPassword(String login, String password) {
        User user = User.find.where()
                .like("login", login)
                .like("password", password)
                .findUnique();

        return ok();
    }


    public static Result addUser() {
        User user = new User();
        user.save();
        return created();
    }


    public static Result updateUser() {
        User user = new User();
        user.save();
        return ok();
    }

    public static Result removeUser() {
        User user = new User();
        user.save();
        return ok();
    }
}
