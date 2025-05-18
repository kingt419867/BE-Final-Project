package online.checkbook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import online.checkbook.controller.model.AccountListData;
import online.checkbook.controller.model.GetAccountBalance;
import online.checkbook.controller.model.TransactionRegisterData;
import online.checkbook.controller.model.TypeData;
import online.checkbook.service.OnlineCheckbookService;

@RestController
@RequestMapping("/online_checkbook")
@Slf4j
public class OnlineCheckbookController {

	@Autowired
	private OnlineCheckbookService onlineCheckbookService;
	
// TraxType class mapping
	
	@PostMapping("/traxType")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TypeData insertType(@RequestBody TypeData typeData) {
		log.info("Creating type {}", typeData);
		return onlineCheckbookService.saveType(typeData);
	} // insertType
	
	@GetMapping("/traxType")
	public List<TypeData> retrieveAllTransactionTypes() {
		log.info("retrieveAllTransactionTypes called.");
		return onlineCheckbookService.retrieveAllTransactionTypes();
	} // retrieveAllTransactionTypes
	
// AccountList class mapping
	
	@PostMapping("/accountList")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AccountListData insertAccount(@RequestBody AccountListData accountListData) {
		log.info("Creating account {}", accountListData);
		return onlineCheckbookService.saveAccount(accountListData);
	} // insertAccount
	
	@GetMapping("/accountList")
	public List<AccountListData> retrieveAllAccounts() {
		log.info("retrieveAllAccounts called.");
		return onlineCheckbookService.retrieveAllAccounts();
	} // retrieveAllTransactionTypes
	
	
	@PutMapping("/accountList/{accountId}")
	@ResponseStatus(code = HttpStatus.OK)
	public AccountListData updateAccount(@PathVariable Long accountId, @RequestBody AccountListData accountListData) {
		accountListData.setAccountId(accountId);
		log.info("Updating account {}", accountListData);
		return onlineCheckbookService.saveAccount(accountListData);
	} // updateAccount
	
// TransactionRegister class mapping
	
//	@PostMapping("/transactionRegister")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public TransactionRegisterData insertTransaction(@RequestBody TransactionRegisterData transactionRegisterData) {
//		log.info("Creating transaction {}", transactionRegisterData);
//		return onlineCheckbookService.saveTransaction(transactionRegisterData);
//	} // insertTransaction
	
	@PostMapping("/transactionRegister")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TransactionRegisterData insertCombinedTransaction(@RequestBody TransactionRegisterData transactionRegisterData, @RequestBody AccountListData accountListData) { // Do I need to make this a List, or Set, or something?
		log.info("Creating transaction {}", transactionRegisterData);														// This needs to pull in elements from all 3 tables, at once.
		return onlineCheckbookService.saveCombinedTransaction(transactionRegisterData, accountListData);
	} // insertCombinedTransaction
	
	@GetMapping("/transactionRegister")
	public List<TransactionRegisterData> retrieveAllTransactions() {
		log.info("retrieveAllTransactions called.");
		return onlineCheckbookService.retrieveAllTransactions();
	} // retrieveAllTransactions
	
	@GetMapping("/transactionRegister/{transactionId}")
	public TransactionRegisterData retrieveTransactionById(@PathVariable Long transactionId) { // removed from () for testing:  , @RequestBody TransactionRegisterData transactionRegisterData
		//transactionRegisterData.setTransactionId(transactionId);
		log.info("retrieveTransactionById called. Retrieving transaction {}", transactionId);
		return onlineCheckbookService.retrieveTransactionById(transactionId);
	} // retrieveTransactionById
	
	@PutMapping("/transactionRegister/{transactionId}")
	public TransactionRegisterData updateTransactionById(@PathVariable Long transactionId, @RequestBody TransactionRegisterData transactionRegisterData) {
		transactionRegisterData.setTransactionId(transactionId);
		log.info("updateTransactionById called. Modifying transaction {}", transactionRegisterData);
		return onlineCheckbookService.saveTransaction(transactionRegisterData);
	} // updateTransactionById
	
	@DeleteMapping("/transactionRegister/{transactionId}")
	public Map<String, String> deleteTransactionById(@PathVariable Long transactionId) {
		//transactionRegisterData.setTransactionId(transactionId);
		log.info("deleteTransactionById called. Deleting transaction {}", transactionId);
		onlineCheckbookService.deleteTransactionById(transactionId);
		return Map.of("message", "Transaction " + transactionId + " deleted.");
	} // updateTransactionById
	
	@GetMapping("/transactionRegister")
	public GetAccountBalance getAccountBalance() {
		return onlineCheckbookService.getAccountBalance();
	} // getAccountBalance
	
} // class
