package github.nibavs.crm_shift_lab;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Schema(description = "Сущность транзакции")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID Транзакции(автоматически генерируется на стороне сервера)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID продавца, который осуществил транзакцию", example = "1")
    private Long seller;

    @Schema(description = "Сумма транзакции", example = "420.99")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Тип оплаты", example = "CASH", allowableValues = {"CASH", "CARD", "TRANSFER"})
    private PaymentType paymentType;

    @Schema(description = "Дата и время совершения транзакции(автоматически генерируется на стороне сервера)", example = "2025-02-14T18:20:52.79444", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime transactionDate;

    @PrePersist
    public void prePersist() {
        transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
