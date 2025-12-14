package com.sweetshop.controller;

import com.sweetshop.dto.sweet.SweetRequestDTO;
import com.sweetshop.dto.sweet.SweetResponseDTO;
import com.sweetshop.model.Sweet;
import com.sweetshop.service.SweetService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    // 1. Add Sweet
    @PostMapping
    public SweetResponseDTO addSweet(@RequestBody SweetRequestDTO request) {

        Sweet sweet = new Sweet(
                null,
                request.getName(),
                request.getCategory(),
                request.getPrice(),
                request.getQuantity()
        );

        Sweet savedSweet = sweetService.addSweet(sweet);

        return mapToResponseDTO(savedSweet);
    }

    // 2. Get All Sweets
    @GetMapping
    public List<SweetResponseDTO> getAllSweets() {
        return sweetService.getAllSweets()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // 3. Purchase Sweet
    @PostMapping("/{id}/purchase")
    public SweetResponseDTO purchaseSweet(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        Sweet updatedSweet = sweetService.purchaseSweet(id, quantity);
        return mapToResponseDTO(updatedSweet);
    }

    // 4. Search Sweets
    @GetMapping("/search")
    public List<SweetResponseDTO> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return sweetService.searchSweets(name, category, minPrice, maxPrice)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // 5. Delete Sweet
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSweet(@PathVariable Long id) {
        sweetService.deleteSweet(id);
    }

    // 6. Restock Sweet
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/restock")
    public SweetResponseDTO restockSweet(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        Sweet sweet = sweetService.restockSweet(id, quantity);
        return mapToResponseDTO(sweet);
    }



    // Mapping method
    private SweetResponseDTO mapToResponseDTO(Sweet sweet) {
        return new SweetResponseDTO(
                sweet.getId(),
                sweet.getName(),
                sweet.getCategory(),
                sweet.getPrice(),
                sweet.getQuantity()
        );
    }


}
