package github.nibavs.crm_shift_lab;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Аналитика", description = "Эндпоинты для работы с аналитикой")
public class AnalyticsController {
    @Autowired
    TransactionRepository transactionRepository;

    @Operation(summary = "Самый продуктивный продавец за день")
    @GetMapping("/most-productive-seller/day")
    public ResponseEntity<Seller> mostProductiveSellerDay() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        Seller seller = transactionRepository.findTopSellerByTransactionDateBetween(startOfDay, endOfDay);

        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Самый продуктивный продавец за месяц")
    @GetMapping("/most-productive-seller/month")
    public ResponseEntity<Seller> mostProductiveSellerMonth() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1);


        Seller seller = transactionRepository.findTopSellerByTransactionDateBetween(startOfMonth, endOfMonth);

        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Самый продуктивный продавец за квартал")
    @GetMapping("/most-productive-seller/quarter")
    public ResponseEntity<Seller> mostProductiveSellerQuarter() {
        LocalDateTime endDate = LocalDateTime.now();

        int startMonth = (( endDate.getMonthValue() - 1) / 3) * 3 + 1;
        LocalDateTime startDate = endDate.withMonth(startMonth).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        Seller seller = transactionRepository.findTopSellerByTransactionDateBetween(startDate, endDate);

        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Самый продуктивный продавец за год")
    @GetMapping("/most-productive-seller/year")
    public ResponseEntity<Seller> mostProductiveSellerYear() {
        LocalDateTime startOfYear = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfYear = startOfYear.plusYears(1);

        Seller seller = transactionRepository.findTopSellerByTransactionDateBetween(startOfYear, endOfYear);

        return seller != null ? ResponseEntity.ok(seller) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Список продавцов с суммой транзакций меньше указанной за период")
    @GetMapping("/sellers-below-amount")
    public ResponseEntity<List<Seller>> getSellersBelowAmount(
            @Schema(example = "2025-02-14T00:00:00")
            @RequestParam LocalDateTime startDate,
            @Schema(example = "2025-02-15T00:00:00")
            @RequestParam LocalDateTime endDate,
            @Schema(example = "2000")
            @RequestParam double amountThreshold) {

        List<Seller> sellers = transactionRepository
                .findSellersWithTransactionsBelowAmount(startDate, endDate, amountThreshold);

        return sellers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sellers);
    }

}
