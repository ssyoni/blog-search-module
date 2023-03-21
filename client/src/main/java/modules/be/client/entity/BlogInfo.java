package modules.be.client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class BlogInfo {
    public String title;
    public String contents;
    public String url;
    public String blogname;
    public String thumbnail;
    public String datetime;

}
