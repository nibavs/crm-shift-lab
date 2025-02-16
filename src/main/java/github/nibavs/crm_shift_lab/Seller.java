package github.nibavs.crm_shift_lab;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Table(name = "seller")
@Schema(description = "Сущность продавца")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID Продавца(автоматически генерируется на стороне сервера)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Имя продавца", example = "Иван Петров")
    private String name;


    @Schema(description = "Контактные данные", example = "contact_email@test.com")
    private String contactInfo;

    @Column(nullable = false)
    @Schema(description = "Дата и время регистрации продавца в системе", example = "2025-02-14T18:20:52.79444", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime registrationDate;

    @PrePersist
    public void prePersist() {
        registrationDate = LocalDateTime.now();
    }

    public Seller() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
