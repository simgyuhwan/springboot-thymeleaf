package com.example.jpa2.domains.category.service;

import com.example.jpa2.domains.category.domain.item.ItemEntity;
import com.example.jpa2.domains.category.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(AddItemRequest request){
        ItemEntity newItem = ItemEntity.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .imagePath(request.getImagePath())
                .categoryId(request.getCategoryId())
                .build();
        ItemEntity savedItem = itemRepository.save(newItem);
        return savedItem.getItemId();
    }

    public ItemDetails findItem(Long itemId){
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return new ItemDetails(itemEntity);
    }

}
