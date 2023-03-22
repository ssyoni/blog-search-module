package modules.be.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class BlogInfo {
    public String title;
    public String contents;
    public String url;
    public String blogname;
    public String thumbnail;
    public LocalDate date;
}
