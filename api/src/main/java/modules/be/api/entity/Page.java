package modules.be.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import modules.be.client.param.ResultParam;

@NoArgsConstructor
@Getter
public class Page {
    public int totalPages;
    public int totalElements;
    public int size;
    public int page;

    public Page(int totalElements,String size, String page){
        this.totalElements = totalElements;
        this.totalPages = totalElements/Integer.parseInt(size);
        this.size = Integer.parseInt(size);
        this.page = Integer.parseInt(page);
    }

}
