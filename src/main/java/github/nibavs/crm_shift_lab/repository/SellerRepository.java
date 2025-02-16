package github.nibavs.crm_shift_lab.repository;

import github.nibavs.crm_shift_lab.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> { }
