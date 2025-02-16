package github.nibavs.crm_shift_lab.repository;

import github.nibavs.crm_shift_lab.entity.Seller;
import github.nibavs.crm_shift_lab.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySeller(Long seller);

    @Query("""
            SELECT s
            FROM Seller s
            JOIN Transaction t ON t.seller = s.id
                AND t.transactionDate BETWEEN :startDate AND :endDate
            GROUP BY s
            ORDER BY SUM(t.amount) DESC LIMIT 1""")
    Seller findTopSellerByTransactionDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("""
            SELECT s
            FROM Seller s
            LEFT JOIN Transaction t ON t.seller = s.id
                AND t.transactionDate BETWEEN :startDate AND :endDate
            GROUP BY s
            HAVING COALESCE(SUM(t.amount), 0) < :amountThreshold
            ORDER BY s.id""")

    List<Seller> findSellersWithTransactionsBelowAmount(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("amountThreshold") double amountThreshold);
}
