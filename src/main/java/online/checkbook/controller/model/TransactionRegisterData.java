package online.checkbook.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.checkbook.entity.AccountList;
import online.checkbook.entity.TransactionRegister;
import online.checkbook.entity.TraxType;

@Data
@NoArgsConstructor
public class TransactionRegisterData {

	private Long transactionId;
	private String transactionDate;
	private boolean verified;
	private BigDecimal paymentAmount;
	private BigDecimal depositAmount;
	private Long checkNumber;
	private AccountList accountList;
	
	private Set<TypeData> traxTypes = new HashSet<>();
	
	public TransactionRegisterData(TransactionRegister transactionRegister) {
		transactionId = transactionRegister.getTransactionId();
		transactionDate = transactionRegister.getTransactionDate();
		verified = transactionRegister.isVerified();
		paymentAmount = transactionRegister.getPaymentAmount();
		depositAmount = transactionRegister.getDepositAmount();
		checkNumber = transactionRegister.getCheckNumber();
		
		for(TraxType traxType : transactionRegister.getTraxTypes()) {
			traxTypes.add(new TypeData(traxType));
		} // for
		
		
	} // TransactionRegisterData
	
} // class
