package modules.be.batch.entity;

import lombok.Data;

@Data
public class EventLog {
    private Long id;
    private String keyword;
    private Long score;
    private String event;
}
