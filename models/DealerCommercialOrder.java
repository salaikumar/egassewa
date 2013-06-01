package models;
import java.util.Date;

import java.util.List;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.mvc.Controller;
import models.*;

@Entity
public class DealerCommercialOrder extends Model {
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
