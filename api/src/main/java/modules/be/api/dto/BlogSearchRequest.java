package modules.be.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import modules.be.api.common.Sort;

@Getter
public class BlogSearchRequest {
    @NonNull
    public String query;
    public Sort sort = Sort.ACCURACY;
    public String page;
    public String size;

    @Override
    public String toString() {
        return super.toString();
    }
}
