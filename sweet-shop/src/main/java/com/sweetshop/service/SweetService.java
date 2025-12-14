package com.sweetshop.service;

import com.sweetshop.model.Sweet;

import java.util.List;

public interface SweetService {

    Sweet addSweet(Sweet sweet);

    List<Sweet> getAllSweets();

    Sweet purchaseSweet(Long sweetId, int quantity);

    List<Sweet> searchSweets(String name, String category, Double minPrice, Double maxPrice);

    void deleteSweet(Long id);

    Sweet restockSweet(Long id, int quantity);

}
