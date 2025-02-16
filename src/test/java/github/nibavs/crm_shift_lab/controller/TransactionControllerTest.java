package github.nibavs.crm_shift_lab.controller;

import github.nibavs.crm_shift_lab.entity.Transaction;
import github.nibavs.crm_shift_lab.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionController transactionsController;

    @Test
    void testGetAllTransactions_ReturnsTransactions() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> response = transactionsController.getAllTransactions();

        assertNotNull(response);
        assertEquals(2, response.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionById_ReturnsTransaction() {
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(2L);

        when(transactionRepository.findById(2L)).thenReturn(Optional.of(mockTransaction));

        Transaction response = transactionsController.getTransactionById(mockTransaction.getId());

        assertNotNull(response);
        assertEquals(2L, response.getId());
        verify(transactionRepository, times(1)).findById(mockTransaction.getId());

    }
}
