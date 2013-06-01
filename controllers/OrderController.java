package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.Controller;
import models.*;

public class OrderController extends Controller {

	public static Long orderNumber = 10000L;

	// Create Order for Dealer
	public static void orderForDealer(String gasCylinderType1,
			Integer gasCylinderType1Count, String gasCylinderType2,
			Integer gasCylinderType2Count, String gasCylinderType3,
			Integer gasCylinderType3Count, Long id) {
		if (session.contains("authUser")) {

			// The user is authorised, proceed

			// To get the current time
			DateCreator d = new DateCreator();

			Date orderedAt = d.getSystemDate();

		}
	}

	public static void orderForCustomer(Long id) {

		GasCylinder g1 = GasCylinder.findById(id);
		System.out.println("GasCylinder Choosen" + g1);

		Calendar c = Calendar.getInstance();

		Account a = (Account) Cache.get("authUser");

		System.out.println("Booking User" + a.username);
		OrderCylinder o = new OrderCylinder();

		// Check Whether the next order date is reached or not
		OrderCylinder order = OrderCylinder.find("order by orderedAt desc")
				.first();
		System.out.println("The Order Cylinder Class is now ordered" + order);

		OrderCylinder o1 = OrderCylinder.find(
				"byGasCylinderIdAndGasConnectionHolder", g1, a).first();

		System.out.println("Next Order State of Cylinder " + o1);

		Customer customer = Customer.find("byGasConnectionNumber",
				a.uniquenumber).first();
		System.out.println("Customer for the Account" + customer);

		// Get the Corresponding Dealer for whom the order is placed

		System.out.println("Dealer for that Account" + customer.dealerId);

		// He should be allowed to book only if the next order date has reached

		if (o1 != null) {

			if (o1.nextOrderDate.before(c.getTime())) {
				o.gasCylinderId = g1;
				o.orderCount = 1;
				o.orderedAt = c.getTime();
				o.orderstatus = "WAITING";
				o.gasConnectionHolder = a;
				o.ordernumber = ++orderNumber;

				Calendar c1 = Calendar.getInstance();
				c1.setTime(o.orderedAt);
				c1.add(Calendar.DATE, 20);
				o.nextOrderDate = c1.getTime();

				// Place the Order for the Corresponding Dealer
				o.dealerId = customer.dealerId;
				System.out
						.println("Dealer Assigned for the Order" + o.dealerId);
				// Checking the values
				o.save();
				System.out.println("Order Placed" + o);

				SimpleEmail email = new SimpleEmail();
				try {
					email.setFrom("gasmate.geekoders@gmail.com");
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					email.addTo(a.email);
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				email.setSubject("GasMate-Order Details");
				try {
					email.setMsg("Hello"
							+ " "
							+ a.username
							+ "Here is the Details of the order placed by you"
							+ " "
							+ " Order Id :"
							+ o.id
							+ " "
							+ "Order Status"
							+ o.orderstatus
							+ " "
							+ "Next Order Date"
							+ o.nextOrderDate
							+ " Please Check your complete order details on Site");
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Mail.send(email);
				// Once Order is placed, he will be redirected to the book page
				UserControllerMap.booksuccess();
			} else {
				UserControllerMap.bookfail();
			}
		}

		else {

			o.gasCylinderId = g1;
			o.orderCount = 1;
			o.orderedAt = c.getTime();
			o.orderstatus = "WAITING";
			o.gasConnectionHolder = a;
			o.ordernumber = ++orderNumber;

			Calendar c1 = Calendar.getInstance();
			c1.setTime(o.orderedAt);
			c1.add(Calendar.DATE, 20);
			o.nextOrderDate = c1.getTime();
			o.dealerId = customer.dealerId;

			// Checking the values
			System.out.println(o);
			o.save();

			SimpleEmail email = new SimpleEmail();
			try {
				email.setFrom("gasmate.geekoders@gmail.com");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.addTo(a.email);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			email.setSubject("GasMate-Order Details");
			try {
				email.setMsg("Hello" + " " + a.username
						+ "Here is the Details of the order placed by you"
						+ " " + " Order Id :" + o.id + " " + "Order Status"
						+ o.orderstatus + " " + "Next Order Date"
						+ o.nextOrderDate
						+ " Please Check your complete order details on Site");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mail.send(email);

			// Once Order is placed, he will be redirected to the book page
			UserControllerMap.booksuccess();

		}

	}

	public static void confirmOrder(Long id) {

		// Retrieve the Order
		OrderCylinder o = OrderCylinder.findById(id);

		o.orderstatus = "CONFIRM";
		o.save();
		Account a = (Account) Cache.get("authUser");
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(a.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello" + " " + a.username
					+ "Here is the OrderStatus of the order placed by you"
					+ " " + " Order Id :" + o.id + " " + "Order Status"
					+ o.orderstatus + " "
					+ " Please Check your account for details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);

		DealerControllerMap.checkorder();

	}

	public static void deliverOrder(Long id) {
		OrderCylinder o = OrderCylinder.findById(id);
		o.orderstatus = "DISPATCHED";
		o.save();
		Account a = (Account) Cache.get("authUser");
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(a.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello" + " " + a.username
					+ "Here is the OrderStatus of the order placed by you"
					+ " " + " Order Id :" + o.id + " " + "Order Status"
					+ o.orderstatus + " "
					+ " Please Check your complete order details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);

		DealerControllerMap.checkorder();

	}

	public static void discard(Long id) {
		OrderCylinder o = OrderCylinder.findById(id);
		o.orderstatus = "CANCELLED";
		o.save();
		Account a = (Account) Cache.get("authUser");
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(a.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello" + " " + a.username
					+ "Here is the Details of the order placed by you" + " "
					+ " Order Id :" + o.id + " " + "Order Status"
					+ o.orderstatus + " " + "Next Order Date" + o.nextOrderDate
					+ " Please Check your complete order details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);

		DealerControllerMap.checkorder();
	}

	/*
	 * The Above will handle the order for Lpg Cylinders Similarly, below will
	 * handle the order for accessories
	 */

	public static void accessoriesOrder(Long id) {

		// Get the Person who placed the order
		Account a = (Account) Cache.get("authUser");
		Calendar c = Calendar.getInstance();

		if (a != null) {
			Accessories o = Accessories.findById(id);

			OrderAccessories o1 = new OrderAccessories();

			o1.accessoriesId = o;
			o1.accountId = a;
			o1.orderstatus = "APPROVED";
			o1.ordertime = c.getTime();
			o1.orderCount = 1;

			Customer c1 = Customer
					.find("byGasConnectionNumber", a.uniquenumber).first();
			o1.dealerId = c1.dealerId;
			o1.save();
			SimpleEmail email = new SimpleEmail();
			try {
				email.setFrom("gasmate.geekoders@gmail.com");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.addTo(a.email);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			email.setSubject("GasMate-Order Details");
			try {
				email.setMsg("Hello"
						+ " "
						+ a.username
						+ "Here is the Details of the order placed by you"
						+ " "
						+ " Order Id :"
						+ o1.id
						+ " "
						+ "Order Status"
						+ o1.orderstatus
						+ " "
						+ "Accessoried"
						+ o1.accessoriesId.name
						+ " "
						+ " Please Check your complete order details on Site");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mail.send(email);
			// Once Order is placed, he will be redirected to the book page


			UserControllerMap.booksuccess();

		} else {
			Application.pleaselogin();
		}

	}

	public static void orderConformAccessories(Long id) {

		OrderAccessories o = OrderAccessories.findById(id);

		o.orderstatus = "CONFORMED";
		o.save();
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(o.accountId.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello"
					+ " "
					+ o.accountId.username
					+ "Here is the Details of the order placed by you"
					+ " "
					+ " Order Id :"
					+ o.id
					+ " "
					+ "Order Status"
					+ o.orderstatus
					+ " "
					+ "Accessoried"
					+ o.accessoriesId.name
					+ " "
					+ " Please Check your complete order details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);
		// Once Order is placed, he will be redirected to the book page
		

		// The return page should be coded here
		DealerControllerMap.accessoriesorderlist();
	}

	public static void dispatchAccessories(Long id) {
		OrderAccessories o = OrderAccessories.findById(id);

		o.orderstatus = "DISPATCHED";
		o.save();
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(o.accountId.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello"
					+ " "
					+ o.accountId.username
					+ "Here is the Details of the order placed by you"
					+ " "
					+ " Order Id :"
					+ o.id
					+ " "
					+ "Order Status"
					+ o.orderstatus
					+ " "
					+ "Accessoried"
					+ o.accessoriesId.name
					+ " "
					+ " Please Check your complete order details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);
		// Once Order is placed, he will be redirected to the book page
		// The return page should be coded here
		DealerControllerMap.accessoriesorderlist();
	}

}