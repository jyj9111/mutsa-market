package com.example.mini1.service;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.nego.NegoPageDto;
import com.example.mini1.dto.nego.NegotiationInDto;
import com.example.mini1.entity.NegotiationEntity;
import com.example.mini1.entity.SalesItemEntity;
import com.example.mini1.exception.ItemNotFoundException;
import com.example.mini1.repository.NegotiationRepository;
import com.example.mini1.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NegotiationService {
    private final SalesItemRepository salesItemRepository;
    private final NegotiationRepository negoRepository;

    public ResponseDto createProposal(Long itemId, NegotiationInDto dto) {
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
}
