package modules.be.client.dto;

import lombok.*;
import modules.be.client.entity.Sort;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
public class SearchRequest {

    @NotBlank
    protected String keyword;
    protected int searchPage = 1;
    protected Sort pageSort = Sort.ACCURACY;
    protected int pageSize = 10;

    @Builder
    public SearchRequest(String keyword, int searchPage, Sort pageSort, int pageSize){
        this.keyword = keyword;
        this.searchPage = searchPage;
        this.pageSort = pageSort;
        this.pageSize = pageSize;
    }
}
