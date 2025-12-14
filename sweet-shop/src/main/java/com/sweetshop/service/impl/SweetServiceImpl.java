package com.sweetshop.service.impl;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.service.SweetService;
import org.springframework.stereotype.Service;
import com.sweetshop.exception.ResourceNotFoundException;
import com.sweetshop.exception.InsufficientStockException;


import java.util.List;

@Service
public class SweetServiceImpl implements SweetService {

    private final SweetRepository sweetRepository;

    public SweetServiceImpl(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    @Override
    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    @Override
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    @Override
    public Sweet purchaseSweet(Long sweetId, int quantity) {
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found with id: " + sweetId));

        if (sweet.getQuantity() < quantity) {
            throw new InsufficientStockException("Only " + sweet.getQuantity() + " items available");
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        return sweetRepository.save(sweet);
    }

    @Override
    public List<Sweet> searchSweets(String name, String category, Double minPrice, Double maxPrice) {

        if (name != null && !name.isBlank()) {
            return sweetRepository.findByNameContainingIgnoreCase(name);
        }

        if (category != null && !category.isBlank()) {
            return sweetRepository.findByCategoryIgnoreCase(category);
        }

        if (minPrice != null && maxPrice != null) {
            return sweetRepository.findByPriceBetween(minPrice, maxPrice);
        }

        return sweetRepository.findAll();
    }

    @Override
    public void deleteSweet(Long id) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found with id: " + id));
        sweetRepository.delete(sweet);
    }

    @Override
    public Sweet restockSweet(Long id, int quantity) {
        Sweet sweet = sweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found with id: " + id));

        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }




}
