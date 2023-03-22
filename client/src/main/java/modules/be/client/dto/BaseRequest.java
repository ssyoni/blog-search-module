package modules.be.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import modules.be.client.entity.Sort;

@Getter
@AllArgsConstructor
public class BaseRequest {
    protected String keyword;
    protected String searchPage = "1";
    protected Sort pageSort = Sort.ACCURACY;
    protected String pageSize = "10";
}
