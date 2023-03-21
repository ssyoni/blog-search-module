package modules.be.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import modules.be.client.common.exception.ErrorCode;
import modules.be.client.entity.Sort;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class SearchRequest {

//    @NotNull(message = "keyword는 null일 수 없다")
    @NonNull
    private String keyword;
    private String searchPage = "1";
    private Sort pageSort = Sort.ACCURACY;
    private String pageSize = "10";
    public SearchRequest(String keyword, String searchPage, Sort pageSort, String pageSize){
        this.keyword = keyword;
        this.searchPage = searchPage;
        this.pageSort = pageSort;
        this.pageSize = pageSize;
    }
}
