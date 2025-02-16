package github.nibavs.crm_shift_lab.controller;

import github.nibavs.crm_shift_lab.entity.Seller;
import github.nibavs.crm_shift_lab.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyticsControllerTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AnalyticsController analyticsController;

    @Test
    void testMostProductiveSellerDay_ReturnsSeller() {
        LocalDateTime startDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDay = startDay.plusDays(1);

        Seller mockSeller = new Seller();
        mockSeller.setId(1L);
        mockSeller.setName("Лучший продавец");

        when(transactionRepository.findTopSellerByTransactionDateBetween(startDay, endDay)).thenReturn(mockSeller);

        ResponseEntity<Seller> response = analyticsController.getMostProductiveSellerDay();

        assertNotNull(response.getBody());
        assertEquals("Лучший продавец", response.getBody().getName());
        verify(transactionRepository, times(1)).findTopSellerByTransactionDateBetween(startDay, endDay);
    }

    @Test
    void testMostProductiveSellerMonth_ReturnsSeller() {
        LocalDateTime startMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endMonth = startMonth.plusMonths(1);

        Seller mockSeller = new Seller();
        mockSeller.setId(1L);
        mockSeller.setName("Лучший продавец");

        when(transactionRepository.findTopSellerByTransactionDateBetween(startMonth, endMonth)).thenReturn(mockSeller);

        ResponseEntity<Seller> response = analyticsController.getMostProductiveSellerMonth();

        assertNotNull(response.getBody());
        assertEquals("Лучший продавец", response.getBody().getName());
        verify(transactionRepository, times(1)).findTopSellerByTransactionDateBetween(startMonth, endMonth);
    }

    @Test
    void testMostProductiveSellerYear_ReturnsSeller() {
        LocalDateTime startYear = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endYear = startYear.plusYears(1);

        Seller mockSeller = new Seller();
        mockSeller.setId(1L);
        mockSeller.setName("Лучший продавец");

        when(transactionRepository.findTopSellerByTransactionDateBetween(startYear, endYear)).thenReturn(mockSeller);

        ResponseEntity<Seller> response = analyticsController.getMostProductiveSellerYear();

        assertNotNull(response.getBody());
        assertEquals("Лучший продавец", response.getBody().getName());
        verify(transactionRepository, times(1)).findTopSellerByTransactionDateBetween(startYear, endYear);
    }

    @Test
    void testMostProductiveSellerDay_ReturnsNoContent() {
        LocalDateTime startDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDay = startDay.plusDays(1);

        when(transactionRepository.findTopSellerByTransactionDateBetween(startDay, endDay)).thenReturn(null);

        ResponseEntity<Seller> response = analyticsController.getMostProductiveSellerDay();

        assertEquals(204, response.getStatusCodeValue());
        verify(transactionRepository, times(1)).findTopSellerByTransactionDateBetween(startDay, endDay);
    }
}