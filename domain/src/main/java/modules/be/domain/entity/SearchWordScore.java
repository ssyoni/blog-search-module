package modules.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "search_word_score",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_word_keyword",
                        columnNames = {"keyword"}
                )
        },
        indexes = @Index(name = "idx__score", columnList = "score")
)
public class SearchWordScore {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "keyword")
    private String keyword;

    @Column
    private Long score = 1L;

//    @Version
//    private Integer version;

    public void updateScore(Long score){
        this.score = score;
    }

    public SearchWordScore(UUID id, String keyword, Long score){
        this.id = id;
        this.keyword = keyword;
        this.score = score;
    }

//    public SearchWordScore(String keyword, Long score){
//        this.keyword = keyword;
//        this.score = score;
//    }
}
