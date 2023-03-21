package modules.be.domain.dto;

import lombok.*;
import modules.be.domain.entity.SearchWordLog;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchWordResponse{
    private String keyword;
    private Long score;

    public SearchWordResponse(SearchWordLog entity){
        this.keyword = entity.getKeyword();
        this.score = entity.getScore();
    }

}
