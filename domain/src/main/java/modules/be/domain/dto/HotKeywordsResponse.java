package modules.be.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class HotKeywordsResponse {
    private List<SearchWordResponse> hotKeywords;

    public HotKeywordsResponse(List<SearchWordResponse> responses){
        this.hotKeywords = responses;
    }
}
