package com.allddaom.models.qnas.service;

import com.allddaom.models.qnas.infra.repo.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository repository;

}
