package modules.be.batch.mapper;

import modules.be.batch.entity.EventLog;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EventLogMapper implements FieldSetMapper<EventLog> {
    @Override
    public EventLog mapFieldSet(FieldSet fieldSet) throws BindException {
        EventLog eventLog = new EventLog();
        eventLog.setId(fieldSet.readString(0));
        eventLog.setKeyword(fieldSet.readString(1));
        eventLog.setScore(fieldSet.readString(2));
        eventLog.setEvent(fieldSet.readString(3));
        return eventLog;
    }
}
