package modules.be.client.dto;

import lombok.*;
import modules.be.client.entity.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class SearchRequest{

    @NotBlank
    private String keyword;
    @Max(50)
    private int searchPage = 1;
    private Sort pageSort = Sort.ACCURACY;
    @Max(50)
    private int pageSize = 10;
    private int totalPageCount;

    @Builder
    public SearchRequest(String keyword, int searchPage, Sort pageSort, int pageSize){
        this.keyword = keyword;
        this.searchPage = searchPage;
        this.pageSort = pageSort;
        this.pageSize = pageSize;
    }
}
