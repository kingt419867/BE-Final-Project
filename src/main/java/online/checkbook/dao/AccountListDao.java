package online.checkbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import online.checkbook.entity.AccountList;

public interface AccountListDao extends JpaRepository<AccountList, Long> {

}
