package modules.be.domain.dto;

import lombok.*;
import modules.be.domain.entity.SearchWordLog;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchWordResponse{
    private Long id;
    private String keyword;
    private Long score;
    private String event;

    @Builder
    SearchWordResponse(SearchWordLog entity, String action){
        this.id = entity.getId();
        this.keyword = entity.getKeyword();
        this.score = entity.getScore();
        this.event = action;
    }
}
