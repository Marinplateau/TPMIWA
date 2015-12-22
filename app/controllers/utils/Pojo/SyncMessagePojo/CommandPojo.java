package controllers.utils.pojo.SyncMessagePojo;

import play.data.validation.Constraints;

public class CommandPojo implements SyncMessagePojo {
    private Long commandId;

    @Constraints.Required
    private String ref;
    @Constraints.Required
    private String body;

    public CommandPojo() {
    }

    public CommandPojo(String ref, String body) {
        this.ref = ref;
        this.body = body;
    }

    public CommandPojo(Long commandId) {
        this.commandId = commandId;
    }

    public String validate() {
        if (ref.isEmpty() || body.isEmpty())
            return "champs vide";
        return null;
    }

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean valideForAdd() {
        return ref != null && !ref.isEmpty() && body != null && !body.isEmpty();
    }

    public boolean valideForRemove() {
        return commandId != null;
    }
}
