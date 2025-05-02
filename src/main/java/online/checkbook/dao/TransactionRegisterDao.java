package online.checkbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import online.checkbook.entity.TransactionRegister;

public interface TransactionRegisterDao extends JpaRepository<TransactionRegister, Long> {

}
