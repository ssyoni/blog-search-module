package modules.be.batch.entity;

import lombok.Data;

@Data
public class EventLog {
    private String id;
    private String keyword;
    private String score;
    private String event;
}
