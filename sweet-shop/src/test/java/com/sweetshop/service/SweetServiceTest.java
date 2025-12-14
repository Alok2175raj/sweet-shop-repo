package com.sweetshop.service;

import com.sweetshop.exception.InsufficientStockException;
import com.sweetshop.exception.ResourceNotFoundException;
import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.service.impl.SweetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SweetServiceTest {

    @Mock
    private SweetRepository sweetRepository;

    @InjectMocks
    private SweetServiceImpl sweetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddSweetSuccessfully() {
        Sweet sweet = new Sweet(1L, "Ladoo", "Indian", 10.0, 50);

        when(sweetRepository.save(any(Sweet.class))).thenReturn(sweet);

        Sweet saved = sweetService.addSweet(sweet);

        assertNotNull(saved);
        assertEquals("Ladoo", saved.getName());
        verify(sweetRepository, times(1)).save(sweet);
    }

    @Test
    void shouldReturnAllSweets() {
        when(sweetRepository.findAll())
                .thenReturn(List.of(new Sweet(), new Sweet()));

        List<Sweet> sweets = sweetService.getAllSweets();

        assertEquals(2, sweets.size());
        verify(sweetRepository).findAll();
    }

    @Test
    void shouldPurchaseSweetSuccessfully() {
        Sweet sweet = new Sweet(1L, "Barfi", "Indian", 20.0, 10);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));
        when(sweetRepository.save(any(Sweet.class))).thenReturn(sweet);

        Sweet updated = sweetService.purchaseSweet(1L, 5);

        assertEquals(5, updated.getQuantity());
    }

    @Test
    void shouldThrowExceptionWhenStockInsufficient() {
        Sweet sweet = new Sweet(1L, "Barfi", "Indian", 20.0, 3);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));

        assertThrows(
                InsufficientStockException.class,
                () -> sweetService.purchaseSweet(1L, 5)
        );
    }

    @Test
    void shouldThrowExceptionWhenSweetNotFound() {
        when(sweetRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> sweetService.purchaseSweet(1L, 1)
        );
    }
}
