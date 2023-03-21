package modules.be.consumer.repository;

import modules.be.consumer.entity.SearchWordScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SearchWordScoreRepository extends JpaRepository<SearchWordScore, UUID> {
    SearchWordScore save(SearchWordScore entity);

    Optional<SearchWordScore> findById(UUID id);
}
