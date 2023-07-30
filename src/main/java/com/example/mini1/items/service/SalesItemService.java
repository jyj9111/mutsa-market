package com.example.mini1.items.service;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.exception.etc.ImageUpdateException;
import com.example.mini1.items.exception.ItemNotFoundException;
import com.example.mini1.common.exception.user.NotExistUsernameException;
import com.example.mini1.items.dto.SalesItemInDto;
import com.example.mini1.items.dto.SalesItemOutDto;
import com.example.mini1.items.dto.SalesItemPageDto;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.items.exception.UnAuthUserException;
import com.example.mini1.items.repository.SalesItemRepository;
import com.example.mini1.users.entity.UserEntity;
import com.example.mini1.users.repo.UserRepository;
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
    private final SalesItemRepository itemRepository;
    private final UserRepository userRepository;

    // SalesItem 등록
    public ResponseDto createSalesItem(String username, SalesItemInDto dto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new NotExistUsernameException();
        UserEntity user = optionalUser.get();

        SalesItemEntity newEntity = SalesItemEntity.fromDto(dto);
        newEntity.setUser(user);
        itemRepository.save(newEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("등록이 완료되었습니다. ");
        return response;
    }

    // SalesItem 전체조회(페이지)
    public Page<SalesItemPageDto> readSalesItemPaged(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        Page<SalesItemEntity> salesItemEntityPage = itemRepository.findAll(pageable);
        Page<SalesItemPageDto> salesItemDtoPage = salesItemEntityPage.map(SalesItemPageDto::fromEntity);
        return salesItemDtoPage;
    }

    // SalesItem 단일조회
    public SalesItemOutDto readSalesItem(Long id) {
        Optional<SalesItemEntity> optionalEntity = itemRepository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        return SalesItemOutDto.fromEntity(optionalEntity.get());
    }

    // SalesItem 업데이트
    public ResponseDto updateSalesItem(String username, Long id, SalesItemInDto dto) {
        Optional<SalesItemEntity> optionalEntity = itemRepository.findById(id);
        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity itemEntity = optionalEntity.get();
        if (!itemEntity.getUser().getUsername().equals(username)) {
            throw new UnAuthUserException();
        }

        itemEntity.updateItem(dto);
        itemRepository.save(itemEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("물품이 수정되었습니다.");
        return response;
    }

    // 게시글 삭제
    public ResponseDto deleteSalesItem(String username, Long id) {
        Optional<SalesItemEntity> optionalEntity = itemRepository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity itemEntity = optionalEntity.get();
        if (!itemEntity.getUser().getUsername().equals(username)) {
            throw new UnAuthUserException();
        }

        ResponseDto response = new ResponseDto();
        itemRepository.deleteById(itemEntity.getId());
        response.setMessage("물품을 삭제했습니다.");

        return response;
    }

    // 물품 이미지 업데이트
    public ResponseDto updateItemImage(String username, Long id, MultipartFile image) {
        Optional<SalesItemEntity> optionalEntity = itemRepository.findById(id);

        if(optionalEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity itemEntity = optionalEntity.get();
        if (!itemEntity.getUser().getUsername().equals(username)) {
            throw new UnAuthUserException();
        }

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
        itemEntity.setImageUrl(imageUrl);
        itemRepository.save(itemEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("이미지가 등록되었습니다.");

        return response;
    }
}
