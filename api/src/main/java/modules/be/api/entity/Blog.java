package modules.be.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import modules.be.client.param.BlogInfo;
import modules.be.client.param.ResultParam;

import javax.xml.transform.Result;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    public String title;
    public String contents;
    public String url;
    public String blogname;
    public String thumbnail;
    public String datetime;

    @Builder
    public Blog(BlogInfo param){
        this.title = param.title;
        this.contents = param.contents;
        this.url = param.url;
        this.blogname = param.blogname;
        this.thumbnail = param.thumbnail;
        this.datetime = param.datetime;
    }

}
