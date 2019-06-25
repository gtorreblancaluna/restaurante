package clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 *
 * @author SOPORTE KONESH
 */
public class SMTPAuthenticator  extends Authenticator{
    private String SMTP_AUTH_USER = "";
    private String SMTP_AUTH_PWD = "";
    
    public SMTPAuthenticator() {}

    public SMTPAuthenticator(String user , String pass) {
        this.SMTP_AUTH_USER = user;
        this.SMTP_AUTH_PWD = pass;
    }

        @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.SMTP_AUTH_USER, this.SMTP_AUTH_PWD);
    }
}
