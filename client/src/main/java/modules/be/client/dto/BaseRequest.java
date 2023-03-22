package modules.be.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import modules.be.client.entity.Sort;

@Getter
@NoArgsConstructor
public class BaseRequest {
    protected String keyword;
    protected int searchPage = 1;
    protected Sort pageSort = Sort.ACCURACY;
    protected int pageSize = 10;

    BaseRequest(SearchRequest request){
        this.keyword = request.getKeyword();
        this.searchPage = request.getPageSize();
        this.pageSort = request.getPageSort();
        this.pageSize = request.getPageSize();
    }
}