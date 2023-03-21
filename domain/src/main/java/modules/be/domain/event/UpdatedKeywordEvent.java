package modules.be.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatedKeywordEvent {
    private UUID id;
    private String keyword;
    private Long score;

}
