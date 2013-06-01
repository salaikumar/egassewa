
package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="DealerOrder")

public class DealerOrder extends Model {

	
	public GasCylinder gasCylinderType1;
	public Integer gasCylinderType1Count;
	
	public GasCylinder gasCylinderType2;
	public Integer gasCylinderType2Count;
	
	public GasCylinder gasCylinderType3;
	public Integer gasCylinderType3Count;
	
	public Account account;
	
	public Date orderAt;
	
	public	String orderstatus;
	
	}
