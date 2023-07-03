package com.example.mini1.service;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.item.SalesItemInDto;
import com.example.mini1.dto.item.SalesItemOutDto;
import com.example.mini1.dto.item.SalesItemPageDto;
import com.example.mini1.entity.SalesItemEntity;
import com.example.mini1.exception.ImageUpdateException;
import com.example.mini1.exception.ItemNotFoundException;
import com.example.mini1.exception.NotMatchedPasswordException;
import com.example.mini1.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesItemService {
    private final SalesItemRepository repository;

    // SalesItem 등록
    public ResponseDto createSalesItem(SalesItemInDto dto) {
        SalesItemEntity newEntity = new SalesItemEntity();
        newEntity.setTitle(dto.getTitle());
        newEntity.setDescription(dto.getDescription());
        newEntity.setMinPriceWanted(dto.getMinPriceWanted());
        newEntity.setWriter(dto.getWriter());
        newEntity.setPassword(dto.getPassword());
        newEntity.setStatus("판매중");
        repository.save(newEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("등록이 완료되었습니다. ");
        return response;
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
            throw new ItemNotFoundException();

        return SalesItemOutDto.fromEntity(optionalEntity.get());
    }

    // SalesItem 업데이트
    public ResponseDto updateSalesItem(Long id, SalesItemInDto dto) {
        Optional<SalesItemEntity> optionalEntity = repository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity entity = optionalEntity.get();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setMinPriceWanted(dto.getMinPriceWanted());
        entity.setWriter(dto.getWriter());
        entity.setPassword(dto.getPassword());
        repository.save(entity);

        ResponseDto response = new ResponseDto();
        response.setMessage("물품이 수정되었습니다.");
        return response;
    }

    // 게시글 삭제
    public ResponseDto deleteSalesItem(Long id, SalesItemInDto dto) {
        Optional<SalesItemEntity> optionalEntity = repository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        ResponseDto response = new ResponseDto();
        SalesItemEntity entity = optionalEntity.get();

        if(entity.getPassword().equals(dto.getPassword())) {
            repository.deleteById(entity.getId());
            response.setMessage("물품을 삭제했습니다.");
        } else {
            throw new NotMatchedPasswordException();
        }

        return response;
    }

    // 물품 이미지 업데이트
    public ResponseDto updateItemImage(Long id, MultipartFile image, String writer, String password) {
        Optional<SalesItemEntity> optionalEntity = repository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity entity = optionalEntity.get();

        if(!entity.getPassword().equals(password))
            throw new NotMatchedPasswordException();

        String extension = "." + image.getOriginalFilename().split("\\.")[1];
        String imageDir = String.format("./item-images/%d", id);
        String filename = String.format("item_%d_%s", id, LocalDateTime.now().toString().replace(":",""));
        String itemImageName = filename + extension;

        try {
            Files.createDirectories(Paths.get(imageDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ImageUpdateException();
        }

        File file = new File(Path.of(imageDir, itemImageName).toUri());

        try (OutputStream outputStream = new FileOutputStream(file)){
            outputStream.write(image.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ImageUpdateException();
        }

        String imageUrl = String.format("/static/%d/%s", id, itemImageName);
        entity.setImageUrl(imageUrl);
        repository.save(entity);

        ResponseDto response = new ResponseDto();
        response.setMessage("이미지가 등록되었습니다.");

        return response;
    }
}
