
// This Model holds the GasCylinder details
package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;


@Entity
@Table(name="GasCylinder")
public class GasCylinder extends Model {

	/**
	 * @param gasCylinderType
	 * @param gasCylinderUnit
	 * @param gasCylinderPrice
	 */
	public GasCylinder(String gasCylinderType, Float gasCylinderUnit,
			Integer gasCylinderPrice) {
		this.gasCylinderType = gasCylinderType;
		this.gasCylinderUnit = gasCylinderUnit;
		this.gasCylinderPrice = gasCylinderPrice;
	}

	public GasCylinder() {
		// TODO Auto-generated constructor stub
	}

	// Type of gasCylinder(Domestic,Commercial)
	@Required
	public String gasCylinderType;
	
	//Weight of gasCylinder in kgw;
	@Required
	public Float gasCylinderUnit;
	
	//Price of gasCylinder
	@Required
	public Integer gasCylinderPrice;
}
