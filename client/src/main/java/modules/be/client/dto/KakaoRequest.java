package modules.be.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoRequest {
    private String query;
    private int size;
    private int page;
    private String sort;
    public KakaoRequest(SearchRequest request){
        this.query = request.getKeyword();
        this.size = request.getPageSize();
        this.page = request.getSearchPage();
        this.sort = request.getPageSort() != null? request.getPageSort().getKakao() : null;
    }
}
