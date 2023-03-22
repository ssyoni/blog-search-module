package modules.be.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NaverRequest {
    public String query;
    public int display;
    public int start;
    public String sort;

    public NaverRequest(SearchRequest request){
            this.query = request.getKeyword();
            this.display = request.getPageSize();
            this.start = request.getSearchPage();
            this.sort = request.getPageSort() != null? request.getPageSort().getNaver() : null;
    }
}
