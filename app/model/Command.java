package model;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Command extends Model {

    public static Finder<Long, Command> find = new Finder<>(Long.class, Command.class);
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String ref;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public Command(String ref, String body) {
        this.ref = ref;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
