package hu.webuni.hr.tamasdobiasz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class LogEntry {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime ts;
    private String description;
    private String username;


    public LogEntry() {

    }

    public LogEntry(long id, LocalDateTime ts, String description, String username) {
        super();
        this.id = id;
        this.ts = LocalDateTime.now();
        this.description = description;
        this.username = username;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDateTime getTs() {
        return ts;
    }
    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



}
