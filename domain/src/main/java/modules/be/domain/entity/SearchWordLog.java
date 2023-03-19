package modules.be.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
        }
)
public class SearchWordLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
