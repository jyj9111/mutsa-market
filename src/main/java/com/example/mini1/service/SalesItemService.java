package com.example.mini1.service;

import com.example.mini1.dto.SalesItemInDto;
import com.example.mini1.dto.SalesItemOutDto;
import com.example.mini1.dto.SalesItemPageDto;
import com.example.mini1.entity.SalesItemEntity;
import com.example.mini1.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesItemService {
    private final SalesItemRepository repository;

    // SalesItem 등록
    public void createSalesItem(SalesItemInDto dto) {
        SalesItemEntity newEntity = new SalesItemEntity();
        newEntity.setTitle(dto.getTitle());
        newEntity.setDescription(dto.getDescription());
        newEntity.setMinPriceWanted(dto.getMinPriceWanted());
        newEntity.setWriter(dto.getWriter());
        newEntity.setPassword(dto.getPassword());
        newEntity.setStatus("판매중");
        repository.save(newEntity);
//        return SalesItemInDto.fromEntity(repository.save(newEntity));
    }

    // SalesItem 전체조회(페이지)
    public Page<SalesItemPageDto> readSalesItemPaged(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        Page<SalesItemEntity> salesItemEntityPage =repository.findAll(pageable);
        Page<SalesItemPageDto> salesItemDtoPage = salesItemEntityPage.map(SalesItemPageDto::fromEntity);
        return salesItemDtoPage;
    }

    // SalesItem 단일조회
    public SalesItemOutDto readSalesItem(Long id) {
        Optional<SalesItemEntity> optionalEntity = repository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return SalesItemOutDto.fromEntity(optionalEntity.get());
    }
}
