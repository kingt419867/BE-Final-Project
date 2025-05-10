package online.checkbook.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.checkbook.entity.AccountList;
import online.checkbook.entity.TransactionRegister;

@Data
@NoArgsConstructor
public class AccountListData {

	private Long accountId;
	private String accountName;
	private String accountNumber;
	private String accountContact;
	private Set<TransactionRegisterResponse> transactions = new HashSet<>();
	
	public AccountListData(AccountList accountList) { // constructor
		accountId = accountList.getAccountId();
		accountName = accountList.getAccountName();
		accountNumber = accountList.getAccountNumber();
		accountContact = accountList.getAccountContact();
		
		for(TransactionRegister transaction : accountList.getTransactions()) {
			transactions.add(new TransactionRegisterResponse(transaction));
		} // for
	} // AccountListData
	
	@Data
	@NoArgsConstructor
	static class TransactionRegisterResponse {
		private Long transactionId;
		private String transactionDate;
		private boolean verified;
		private BigDecimal paymentAmount;
		private BigDecimal depositAmount;
		private Long checkNumber;
		TransactionRegisterResponse(TransactionRegister transaction) {
			transactionId = transaction.getTransactionId();
			transactionDate = transaction.getTransactionDate();
			verified = transaction.isVerified();
			paymentAmount = transaction.getPaymentAmount();
			depositAmount = transaction.getDepositAmount();
			checkNumber = transaction.getCheckNumber();
		}
	} // TransactionRegister
	
} // class
