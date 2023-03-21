package modules.be.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import modules.be.client.entity.BlogInfo;
import modules.be.client.entity.Pagination;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SearchBaseResponse {
    List<BlogInfo> blogs;
    Pagination page;

}
