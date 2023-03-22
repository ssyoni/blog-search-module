package modules.be.domain.repository;

import modules.be.domain.entity.SearchWordLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SearchWordLogRepository extends JpaRepository<SearchWordLog, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SearchWordLog s where s.keyword = :keyword")
    SearchWordLog findByKeywordForUpdate(String keyword);

    SearchWordLog findByKeyword(String keyword);
}
