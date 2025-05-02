package online.checkbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.checkbook.dao.TransactionRegisterDao;

@Service
public class OnlineCheckbookService {

	@Autowired
	TransactionRegisterDao transactionRegisterDao;
}
