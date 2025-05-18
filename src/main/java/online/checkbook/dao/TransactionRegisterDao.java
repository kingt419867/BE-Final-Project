package online.checkbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import online.checkbook.controller.model.GetAccountBalance;
import online.checkbook.entity.TransactionRegister;

public interface TransactionRegisterDao extends JpaRepository<TransactionRegister, Long> {
	@NativeQuery("SELECT (SUM(deposit_amount) - SUM(payment_amount)) AS 'Account Balance' FROM transaction_register")
	GetAccountBalance getAccountBalance();
}
