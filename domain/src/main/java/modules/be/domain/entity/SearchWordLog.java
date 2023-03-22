package modules.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "search_word_log",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_word_",
                        columnNames = {"keyword"}
                )
        },
        indexes = @Index(name = "idx__keyword", columnList = "keyword")
)
public class SearchWordLog {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.AUTO)
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "keyword")
    private String keyword;

    @Column
    private Long score = 1L;

//    @Version
//    private Integer version;

    public void updateScore(){
        this.score = this.score + 1;
    }

    public SearchWordLog(String keyword){
        this.keyword = keyword;
    }

}
