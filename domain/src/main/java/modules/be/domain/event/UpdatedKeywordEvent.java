package modules.be.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedKeywordEvent {
    private UUID id;
    private String keyword;
    private Long score;

}
