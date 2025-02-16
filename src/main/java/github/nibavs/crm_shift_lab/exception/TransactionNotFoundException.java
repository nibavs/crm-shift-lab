package github.nibavs.crm_shift_lab.exception;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException(Long id) {
    super("Transaction with ID " + id + " not found");
  }
}