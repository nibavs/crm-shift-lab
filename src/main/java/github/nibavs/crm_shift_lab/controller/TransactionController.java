package github.nibavs.crm_shift_lab.controller;

import github.nibavs.crm_shift_lab.entity.Transaction;
import github.nibavs.crm_shift_lab.repository.TransactionRepository;
import github.nibavs.crm_shift_lab.exception.TransactionNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Транзакция", description = "Эндпоинты для работы с транзакциями")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Список всех транзакций")
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Operation(summary = "Получить информацию о конкретной транзакции по ID")
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Operation(summary = "Создать новую транзакцию")
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
