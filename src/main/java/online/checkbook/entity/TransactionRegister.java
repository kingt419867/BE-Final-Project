package online.checkbook.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class TransactionRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	@EqualsAndHashCode.Exclude
	private String transactionDate;
	@EqualsAndHashCode.Exclude
	private boolean verified;
	@EqualsAndHashCode.Exclude
	private BigDecimal paymentAmount;
	@EqualsAndHashCode.Exclude
	private BigDecimal depositAmount;
	@EqualsAndHashCode.Exclude
	private Long checkNumber;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private AccountList accountList;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable (
			name = "transaction_types",
			joinColumns = @JoinColumn(name = "transaction_id"),
					inverseJoinColumns = @JoinColumn(name = "type_id")
			)
	private Set<TraxType> traxTypes = new HashSet<>();
} // class
