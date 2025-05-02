package online.checkbook.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;
	@EqualsAndHashCode.Exclude
	private String typeCode;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "types")
	private Set<TransactionRegister> transactions = new HashSet<>();
} // class
