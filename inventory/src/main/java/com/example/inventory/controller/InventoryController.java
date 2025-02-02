package com.example.inventory.controller;

import com.example.inventory.dto.ItemDto;
import com.example.inventory.model.Item;
import com.example.inventory.service.ItemCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    ItemCreationService itemCreationService;

    @PostMapping("/create")
    public String create(@RequestBody ItemDto itemDto) {
        itemCreationService.createItem(itemDto);
        return "Successfully created one item";
    }

    @PutMapping("/update")
    public String updateItem(@RequestBody ItemDto itemDto) {
        itemCreationService.updateItem(itemDto);
        return "Successfully updated item " + itemDto.getItemId().toString();
    }

    @GetMapping("/getAll")
    public List<Item> getAll() {
        return itemCreationService.getAll();
    }

    @GetMapping("/getOne")
    public Item getOne(@RequestParam Long itemId) {
        return itemCreationService.getOne(itemId);
    }

    @DeleteMapping("/delete-one")
    public String deleteOne(@RequestParam Long itemId) {
        itemCreationService.deleteOne(itemId);
        return "Deleted item " + itemId.toString();
    }
}
