package modules.be.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import modules.be.domain.entity.SearchWordLog;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchWordResponse_batch {
    private UUID id;
    private String keyword;
    private Long score;
    private String event;

    @Builder
    SearchWordResponse_batch(SearchWordLog entity, String action){
        this.id = entity.getId();
        this.keyword = entity.getKeyword();
        this.score = entity.getScore();
        this.event = action;
    }
}
