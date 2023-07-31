package com.example.mini1.negotiations.service;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.exception.*;
import com.example.mini1.common.exception.user.NotExistUsernameException;
import com.example.mini1.items.exception.ItemNotFoundException;
import com.example.mini1.negotiations.exception.*;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.items.repository.SalesItemRepository;
import com.example.mini1.negotiations.dto.NegoInDto;
import com.example.mini1.negotiations.dto.NegoPageDto;
import com.example.mini1.negotiations.entity.NegotiationEntity;
import com.example.mini1.negotiations.repository.NegotiationRepository;
import com.example.mini1.users.entity.UserEntity;
import com.example.mini1.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NegotiationService {
    private final SalesItemRepository salesItemRepository;
    private final NegotiationRepository negoRepository;
    private final UserRepository userRepository;

    // 제안 등록
    public ResponseDto createProposal(String username, Long itemId, NegoInDto dto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new NotExistUsernameException();
        UserEntity user = optionalUser.get();

        Optional<SalesItemEntity> optionalItem = salesItemRepository.findById(itemId);
        if (optionalItem.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity item = optionalItem.get();

        NegotiationEntity newProposal = NegotiationEntity.ofUserItemDto(user, item, dto);
        negoRepository.save(newProposal);

        ResponseDto response = new ResponseDto();
        response.setMessage("구매 제안이 등록되었습니다.");
        return response;
    }

    // 제안 조회
    public Page<NegoPageDto> readAllProposal(String username, Long itemId, Integer page) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if(optionalSalesItem.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity itemEntity = optionalSalesItem.get();

        Pageable pageable = PageRequest.of(page, 25, Sort.by("id"));
        Page<NegotiationEntity> negoEntityPage;

        if(itemEntity.getUser().getUsername().equals(username)) {
            negoEntityPage = negoRepository.findAllByItemId(itemId, pageable);
        } else {
            negoEntityPage = negoRepository.findAllByItemIdAndUserUsername(itemId, username, pageable);
        }

        Page<NegoPageDto> negoDtoPage = negoEntityPage.map(NegoPageDto::fromEntity);
        return negoDtoPage;
    }

    // 제안 수정
    public ResponseDto updateProposal(String username, Long itemId, Long propId, NegoInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();
        if (!negoEntity.getUser().getUsername().equals(username))
            throw new UnAuthNegoEditException();

        negoEntity.updateNego(dto);
        negoRepository.save(negoEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안이 수정되었습니다.");
        return response;
    }

    // 제안 삭제
    public ResponseDto deleteProposal(String username, Long itemId, Long propId, NegoInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();
        if (!negoEntity.getUser().getUsername().equals(username))
            throw new UnAuthNegoDeleteException();

        negoRepository.deleteById(propId);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안을 삭제했습니다.");
        return response;
    }

    // 제안 수락 or 거절 결정
    public ResponseDto updateProposalStatus(String username, Long itemId, Long propId, NegoInDto dto) {
        Optional<SalesItemEntity> optionalItemEntity = salesItemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity itemEntity = optionalItemEntity.get();
        if (!itemEntity.getUser().getUsername().equals(username))
            throw new UnAuthNegoAcceptException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();
        NegotiationEntity negoEntity = optionalNegoEntity.get();

        negoEntity.updateStatus(dto);
        negoRepository.save(negoEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안의 상태가 변경되었습니다.");
        return response;
    }

    // 구매 확정
    public ResponseDto updateItemAndProposalStatus(String username, Long itemId, Long propId, NegoInDto dto) {
        Optional<SalesItemEntity> optionalItemEntity = salesItemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity itemEntity = optionalItemEntity.get();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();
        if (!negoEntity.getUser().getUsername().equals(username))
            throw new UnAuthNegoConfirmException();
        if(!negoEntity.getStatus().equals("수락"))
            throw new WrongStatusException();

        negoEntity.updateStatus(dto);
        negoEntity = negoRepository.save(negoEntity);

        if(negoEntity.getStatus().equals("확정")) {
            itemEntity.setStatus("판매완료");
            salesItemRepository.save(itemEntity);
        }

        List<NegotiationEntity> negoList = negoRepository.findAllByItemId(itemId);
        for(NegotiationEntity entity : negoList) {
            if(entity.getId().equals(negoEntity.getId())) continue;
            entity.setStatus("거절");
            negoRepository.save(entity);
        }

        ResponseDto response = new ResponseDto();
        response.setMessage("구매가 확정되었습니다.");
        return response;
    }
}
