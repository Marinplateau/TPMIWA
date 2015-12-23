package controllers.utils.pojo.AsyncMessagePojo;


import play.Logger;

public class SimpleMessagePojo extends AsyncMessagePojo {

    private String message;

    public SimpleMessagePojo(String message) {
        this.message = message;
    }

    @Override
    public void action() {
        Logger.info("{}", message);
    }

    @Override
    public String toString() {
        return "SimpleMessagePojo{" +
                "message='" + message + '\'' +
                '}';
    }
}
