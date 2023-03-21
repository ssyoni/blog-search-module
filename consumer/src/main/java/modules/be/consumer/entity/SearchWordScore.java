package modules.be.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "search_word_score",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_word_score",
                        columnNames = {"keyword"}
                )
        },
        indexes = @Index(name = "idx__keyword", columnList = "keyword")
)
public class SearchWordScore {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "keyword")
    private String keyword;

    @Column
    private Long score = 1L;

    @Version
    private Integer version;

    public void updateScore(Long score){
        this.score = score;
    }

    public SearchWordScore(String id, String keyword, Long score){
        this.id = UUID.fromString(id);
        this.keyword = keyword;
        this.score = score;
    }
}
