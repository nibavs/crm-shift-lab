package github.nibavs.crm_shift_lab;

import github.nibavs.crm_shift_lab.exception.SellerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@Tag(name = "Продавец", description = "Эндпоинты для работы с продавцами")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Список всех продавцов")
    @GetMapping
    public List<Seller> sellers() {
        return sellerRepository.findAll();
    }

    @Operation(summary = "Информация о конкретном продавце")
    @GetMapping("/{id}")
    public ResponseEntity<Seller> sellerById(@PathVariable Long id) {
        return sellerRepository
                .findById(id)
                .map(seller -> ResponseEntity.ok().body(seller))
                .orElseThrow(() -> new SellerNotFoundException(id));
    }

    @Operation(summary = "Создать нового продавца")
    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }

    @Operation(summary = "Обновить информацию о прдавце")
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        return sellerRepository.findById(id)
                .map(s -> {
                    s.setName(seller.getName());
                    s.setContactInfo(seller.getContactInfo());
                    sellerRepository.save(s);
                    return ResponseEntity.ok().body(s);
                })
                .orElseThrow(() -> new SellerNotFoundException(id));
    }


    @Operation(summary = "Удалить продавца")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        return sellerRepository.findById(id)
                .map(seller -> {
                    sellerRepository.delete(seller);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new SellerNotFoundException(id));
    }

    @Operation(summary = "Получить все транзакции продавца")
    @GetMapping("/{sellerId}/transactions")
    public List<Transaction> sellerTransactions(@PathVariable Long sellerId) {
        return transactionRepository.findAllBySeller(sellerId);
    }
}
