package com.example.inventory.service;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.exception.ItemNotFoundException;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemCreationService {
    @Autowired
    ItemRepository itemRepository;
    public void createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        itemRepository.save(item);
    }

    public void updateItem(ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getItemId())
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());

        itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getOne(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }

    public void deleteOne(Long itemId) {
        Item item = itemRepository.findById(itemId)
                        .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        itemRepository.deleteById(itemId);
    }
}
