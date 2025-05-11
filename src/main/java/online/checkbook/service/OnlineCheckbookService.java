package online.checkbook.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.checkbook.controller.model.AccountListData;
import online.checkbook.controller.model.TransactionRegisterData;
import online.checkbook.controller.model.TypeData;
import online.checkbook.dao.AccountListDao;
import online.checkbook.dao.TransactionRegisterDao;
import online.checkbook.dao.TraxTypeDao;
import online.checkbook.entity.AccountList;
import online.checkbook.entity.TransactionRegister;
import online.checkbook.entity.TraxType;

@Service
public class OnlineCheckbookService {

	@Autowired
	TransactionRegisterDao transactionRegisterDao;
	
	@Autowired
	AccountListDao accountListDao;
	
	@Autowired
	TraxTypeDao traxTypeDao;
	
// TraxType class

	@Transactional(readOnly = false)
	public TypeData saveType(TypeData typeData) {
		//Long typeId = typeData.getTypeId();  // IDE said the typeId wasn't being used.
		TraxType traxType = new TraxType();
		setFieldsInType(traxType, typeData);
		return new TypeData(traxTypeDao.save(traxType));
	} // saveType
	
	private static void setFieldsInType(TraxType traxType, TypeData typeData) {
		traxType.setTypeCode(typeData.getTypeCode());
	} // setFieldsInType
	
	private TraxType createType(Long typeId, String typeCode) {
		TraxType traxType;
		if(Objects.isNull(typeId)) { // The only operation on TraxType.java, besides a Get, will be a Post, so the typeId will always be null, because you're always only creating a new one.  You cannot do a Put or Delete, because as soon as 1 Transaction uses a new typeCode, that typeCode becomes a part of the permanent record.  If you want to modify a typeCode, you must, therefore, create a new one.
			traxType = new TraxType();
		}
		traxType = new TraxType();
		return traxType;
	} // createType

	@Transactional(readOnly = true)
	public List<TypeData> retrieveAllTransactionTypes() {
		// @formatter:off
		return traxTypeDao.findAll().stream().map(tType -> new TypeData(tType)).toList(); // Streams the list of transaction types.  //or .map(TypeData::new)
		// @formatter:on
	} // retrieveAllTransactionTypes
	
// AccountList class
	
	@Transactional(readOnly = false)
	public AccountListData saveAccount(AccountListData accountListData) {
		Long accountId = accountListData.getAccountId();
		AccountList accountList = findOrCreateAccount(accountId);
		
		copyAccountListFields(accountList, accountListData);
		return new AccountListData(accountListDao.save(accountList));
	} // saveAccount	

	private void copyAccountListFields(AccountList accountList, AccountListData accountListData) {
		accountList.setAccountId(accountListData.getAccountId());
		accountList.setAccountName(accountListData.getAccountName());
		accountList.setAccountNumber(accountListData.getAccountNumber());
		accountList.setAccountContact(accountListData.getAccountContact());
	} // copyAccountListFields

	private AccountList findOrCreateAccount(Long accountId) {
		if(Objects.isNull(accountId)) {
			return new AccountList();
		} else {
			return findAccountById(accountId);
		} // if
	} // findOrCreateAccount

	private AccountList findAccountById(Long accountId) {
		return accountListDao.findById(accountId).orElseThrow(() -> new NoSuchElementException("Account " + accountId + " was not found."));
	} // findAccountById
	
	@Transactional(readOnly = true)
	public List<AccountListData> retrieveAllAccounts() {
		//@formatter:off
		return accountListDao.findAll().stream().map(acct -> new AccountListData(acct)).toList(); // Streams the list of accounts
		//@formatter:on
	} // retrieveAllAccounts


	
// TransactionRegister Class
	
	@Transactional(readOnly = false)
	public TransactionRegisterData saveTransaction(TransactionRegisterData transactionRegisterData) {
		Long transactionId = transactionRegisterData.getTransactionId();
		TransactionRegister transactionRegister = findOrCreateTransaction(transactionId);
		copyTransactionRegisterFields(transactionRegister, transactionRegisterData);
		
		return new TransactionRegisterData(transactionRegisterDao.save(transactionRegister));
	} // saveTransaction

	private void copyTransactionRegisterFields(TransactionRegister transactionRegister, TransactionRegisterData transactionRegisterData) {
		transactionRegister.setTransactionId(transactionRegisterData.getTransactionId());
		transactionRegister.setTransactionDate(transactionRegisterData.getTransactionDate());
		transactionRegister.setVerified(transactionRegisterData.isVerified());
		transactionRegister.setPaymentAmount(transactionRegisterData.getPaymentAmount());
		transactionRegister.setDepositAmount(transactionRegisterData.getDepositAmount());
		transactionRegister.setCheckNumber(transactionRegisterData.getCheckNumber());
		//transactionRegister.setTraxTypes(transactionRegisterData.getTraxTypes()); // troubleshooting...
		transactionRegister.setAccountList(transactionRegisterData.getAccountList());
		
	} // copyTransactionRegisterFields

	private TransactionRegister findOrCreateTransaction(Long transactionId) {
		if(Objects.isNull(transactionId)) {
			return new TransactionRegister();
		} else {
			return findTransactionById(transactionId);
		}
	} // findOrCreateTransaction

	private TransactionRegister findTransactionById(Long transactionId) {
		return transactionRegisterDao.findById(transactionId).orElseThrow(() -> new NoSuchElementException("Transaction " + transactionId + " was not found."));
	} // findTransactionById

	@Transactional(readOnly = true)
	public List<TransactionRegisterData> retrieveAllTransactions() {
		//@formatter:off
		return transactionRegisterDao.findAll().stream().map(trans -> new TransactionRegisterData(trans)).toList(); // Streams all the transactions (This could be VERY long.)
		//@formatter:on
	} // retrieveAllTransactions

	@Transactional(readOnly = true)
	public TransactionRegister retrieveTransactionById(TransactionRegisterData transactionRegisterData) {
		Long transactionId = transactionRegisterData.getTransactionId();
		return findTransactionById(transactionId);
	} // retrieveTransactionById

	@Transactional(readOnly = true)
	public TransactionRegisterData retrieveTransactionById(Long transactionId) {

		return new TransactionRegisterData(findTransactionById(transactionId));
	} // retrieveTransactionById

	public void deleteTransactionById(Long transactionId) {
		TransactionRegister transactionRegister = findTransactionById(transactionId);
		transactionRegisterDao.deleteById(transactionId);
	} // deleteTransactionById
	
	
	
	
	
	
} // class
















