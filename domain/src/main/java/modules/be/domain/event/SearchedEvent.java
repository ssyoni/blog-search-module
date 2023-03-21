package modules.be.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchedEvent {
    private String keyword;

}
