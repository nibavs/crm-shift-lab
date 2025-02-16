package github.nibavs.crm_shift_lab.controller;

import github.nibavs.crm_shift_lab.entity.Seller;
import github.nibavs.crm_shift_lab.exception.SellerNotFoundException;
import github.nibavs.crm_shift_lab.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private SellerController sellerController;

    @Test
    void testGetAllSellers_ReturnsAllSellers() {
        Seller seller1 = new Seller();
        seller1.setId(1L);

        Seller seller2 = new Seller();
        seller2.setId(2L);

        List<Seller> sellers = Arrays.asList(seller1, seller2);
        when(sellerRepository.findAll()).thenReturn(sellers);

        List<Seller> response = sellerController.getAllSellers();

        assertNotNull(response);
        assertEquals(2, response.size());
        verify(sellerRepository, times(1)).findAll();
    }
    @Test
    void testGetSellerById_ReturnsSeller() {
        Long sellerId = 1L;
        Seller mockSeller = new Seller();
        mockSeller.setId(sellerId);
        mockSeller.setName("Продавец 1");

        when(sellerRepository.findById(sellerId)).thenReturn(Optional.of(mockSeller));

        ResponseEntity<Seller> response = sellerController.getSellerById(sellerId);

        assertNotNull(response.getBody());
        assertEquals("Продавец 1", response.getBody().getName());
        verify(sellerRepository, times(1)).findById(sellerId);
    }

    @Test
    void testGetSellerById_ThrowsSellerNotFoundException() {
        Long sellerId = 3L;
        when(sellerRepository.findById(sellerId)).thenReturn(Optional.empty());

        assertThrows(SellerNotFoundException.class, () -> sellerController.getSellerById(sellerId));

        verify(sellerRepository, times(1)).findById(sellerId);
    }
}
