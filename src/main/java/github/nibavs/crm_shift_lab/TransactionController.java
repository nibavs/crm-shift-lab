package github.nibavs.crm_shift_lab;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionRepository
                .findById(id)
                .map(transaction -> ResponseEntity.ok().body(transaction))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Создать новую транзакцию")
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        transaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(transaction);
    }


}
