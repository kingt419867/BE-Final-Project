package online.checkbook.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import online.checkbook.entity.TraxType;

@Data
@NoArgsConstructor
public class TypeData {

	private Long typeId;
	private String typeCode;

	public TypeData(TraxType traxType) { // constructor
		typeId = traxType.getTypeId();
		typeCode = traxType.getTypeCode();

	} // getTypeId

} // class
