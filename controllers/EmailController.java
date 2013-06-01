package controllers;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.cache.Cache;
import play.data.validation.Required;
import play.mvc.Controller;
import models.*;
public class EmailController extends Controller{
	public static void email(String to, String mail,String subject) throws EmailException{
	Email email = new SimpleEmail();
	email.setHostName("smtp.gmail.com");
	email.setSmtpPort(587);
	email.setAuthenticator(new DefaultAuthenticator("indgasmate", "sastrasrc"));
	email.setTLS(true);
	email.setFrom("indgasmate@gmail.com");
	email.setSubject(subject);
	email.setMsg(mail);
	email.addTo(to);
	email.send();
	}
}
