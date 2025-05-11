package online.checkbook.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class AccountList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@EqualsAndHashCode.Exclude
	private String accountName;
	@EqualsAndHashCode.Exclude
	private String accountNumber;
	@EqualsAndHashCode.Exclude
	private String accountContact;
	
	@OneToMany(mappedBy = "accountList", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<TransactionRegister> transactions = new HashSet<>();
} // class
