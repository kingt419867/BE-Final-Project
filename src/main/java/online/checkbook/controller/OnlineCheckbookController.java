package online.checkbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@PostMapping("/transactionRegister")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TransactionRegisterData insertTransaction(@RequestBody TransactionRegisterData transactionRegisterData) {
		log.info("Creating transaction {}", transactionRegisterData);
		return onlineCheckbookService.saveTransaction(transactionRegisterData);
	} // insertTransaction
	
	@GetMapping("/transactionRegister")
	public List<TransactionRegisterData> retrieveAllTransactions() {
		log.info("retrieveAllTransactions called.");
		return onlineCheckbookService.retrieveAllTransactions();
	} // retrieveAllTransactions
	
} // class
