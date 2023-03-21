package modules.be.domain.repository;

import modules.be.domain.entity.SearchWordScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SearchWordScoreRepository extends JpaRepository<SearchWordScore, UUID> {

}
