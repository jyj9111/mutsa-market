package com.example.mini1.negotiations.service;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.dto.exception.*;
import com.example.mini1.common.exception.*;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.exception.*;
import com.example.mini1.items.repository.SalesItemRepository;
import com.example.mini1.negotiations.dto.NegoInDto;
import com.example.mini1.negotiations.dto.NegoPageDto;
import com.example.mini1.negotiations.entity.NegotiationEntity;
import com.example.mini1.negotiations.repository.NegotiationRepository;
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

    // 제안 등록
    public ResponseDto createProposal(Long itemId, NegoInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        NegotiationEntity newProposal = new NegotiationEntity();
        newProposal.setItemId(itemId);
        newProposal.setWriter(dto.getWriter());
        newProposal.setPassword(dto.getPassword());
        newProposal.setSuggestedPrice(dto.getSuggestedPrice());
        newProposal.setStatus("제안");
        negoRepository.save(newProposal);

        ResponseDto response = new ResponseDto();
        response.setMessage("구매 제안이 등록되었습니다.");
        return response;
    }

    // 제안 조회
    public Page<NegoPageDto> readAllProposal(Long itemId, String writer, String password, Integer page) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if(optionalSalesItem.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity itemEntity = optionalSalesItem.get();

        Pageable pageable = PageRequest.of(page, 25, Sort.by("id"));
        Page<NegotiationEntity> negoEntityPage;

        if(itemEntity.getWriter().equals(writer)
                && itemEntity.getPassword().equals(password)) {
            negoEntityPage = negoRepository.findAllByItemId(itemId, pageable);
        } else {
            negoEntityPage = negoRepository.findAllByItemIdAndWriterEqualsAndPasswordEquals(itemId, writer, password, pageable);
        }

        Page<NegoPageDto> negoDtoPage = negoEntityPage.map(NegoPageDto::fromEntity);
        return negoDtoPage;
    }

    // 제안 수정
    public ResponseDto updateProposal(Long itemId, Long propId, NegoInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();

        if(!negoEntity.getWriter().equals(dto.getWriter()))
            throw new NotMatchedWriterException();
        if(!negoEntity.getPassword().equals(dto.getPassword()))
            throw new NotMatchedPasswordException();

        negoEntity.setSuggestedPrice(dto.getSuggestedPrice());
        negoRepository.save(negoEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안이 수정되었습니다.");
        return response;
    }

    // 제안 삭제
    public ResponseDto deleteProposal(Long itemId, Long propId, NegoInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();

        if(!negoEntity.getWriter().equals(dto.getWriter()))
            throw new NotMatchedWriterException();
        if(!negoEntity.getPassword().equals(dto.getPassword()))
            throw new NotMatchedPasswordException();

        negoRepository.deleteById(propId);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안을 삭제했습니다.");
        return response;
    }

    // 제안 수락 or 거절 결정
    public ResponseDto updateProposalStatus(Long itemId, Long propId, NegoInDto dto) {
        Optional<SalesItemEntity> optionalItemEntity = salesItemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity itemEntity = optionalItemEntity.get();
        if(!itemEntity.getWriter().equals(dto.getWriter()))
            throw new NotMatchedWriterException();
        if(!itemEntity.getPassword().equals(dto.getPassword()))
            throw new NotMatchedPasswordException();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();
        negoEntity.setStatus(dto.getStatus());
        negoRepository.save(negoEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("제안의 상태가 변경되었습니다.");
        return response;
    }

    // 구매 확정
    public ResponseDto updateItemAndProposalStatus(Long itemId, Long propId, NegoInDto dto) {
        Optional<SalesItemEntity> optionalItemEntity = salesItemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity itemEntity = optionalItemEntity.get();

        Optional<NegotiationEntity> optionalNegoEntity = negoRepository.findById(propId);
        if(optionalNegoEntity.isEmpty())
            throw new ProposalNotFoundException();

        NegotiationEntity negoEntity = optionalNegoEntity.get();
        if(!negoEntity.getWriter().equals(dto.getWriter()))
            throw new NotMatchedWriterException();
        if(!negoEntity.getPassword().equals(dto.getPassword()))
            throw new NotMatchedPasswordException();
        if(!negoEntity.getStatus().equals("수락"))
            throw new WrongStatusException();

        negoEntity.setStatus(dto.getStatus());
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
