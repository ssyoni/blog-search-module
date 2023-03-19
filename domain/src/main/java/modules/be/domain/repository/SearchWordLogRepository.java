package modules.be.domain.repository;

import modules.be.domain.entity.SearchWordLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface SearchWordLogRepository extends JpaRepository<SearchWordLog, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="3000")})   // 딜레이 제한
    @Transactional
    SearchWordLog findByKeyword(String keyword);
}
