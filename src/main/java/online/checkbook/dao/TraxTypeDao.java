package online.checkbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import online.checkbook.entity.TraxType;

public interface TraxTypeDao extends JpaRepository<TraxType, Long> {

}
