package modules.be.client.dto;

import lombok.*;
import modules.be.client.entity.Sort;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@ToString
public class SearchRequest {

    @NotBlank
    private String keyword;
    private String searchPage = "1";
    private Sort pageSort = Sort.ACCURACY;
    private String pageSize = "10";

    @Builder
    public SearchRequest(String keyword, String searchPage, Sort pageSort, String pageSize){
        this.keyword = keyword;
        this.searchPage = searchPage;
        this.pageSort = pageSort;
        this.pageSize = pageSize;
    }
}
