package com.allddaom.models.qnas.infra.repo;

import com.allddaom.models.qnas.domain.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
}
