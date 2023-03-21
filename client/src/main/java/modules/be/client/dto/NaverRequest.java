package modules.be.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NaverRequest {
    public String query;
    public String display;
    public String start;
    public String sort;

    public NaverRequest(SearchRequest request){
            this.query = request.getKeyword();
            this.display = String.valueOf(request.getPageSize());
            this.start = String.valueOf(request.getSearchPage());
            this.sort = request.getPageSort() != null? request.getPageSort().getNaver() : null;
    }
}
