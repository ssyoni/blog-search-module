package modules.be.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import modules.be.api.entity.Blog;
import modules.be.api.entity.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchResponse {
    List<Blog> blogs;
    Page page;
}
