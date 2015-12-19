/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
import teleriego.model.Membership;
import teleriego.model.Transaction;

/**
 *
 * @author inftel10
 */
@Stateless
public class MembershipFacade extends AbstractFacade<Membership> {
    private final long MAXSIZE = 4096;

    public static byte[] getImage(Membership membership) {
        return membership.getImage();
        
    }
    @PersistenceContext(unitName = "TeleRiegoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembershipFacade() {
        super(Membership.class);
    }
    
    public boolean changePassword(String oldPass, String newPass, BigDecimal userMemberNumber){
        Membership userDB = em.find(Membership.class,userMemberNumber);
        Boolean correctUpdate = false;
        
        if(oldPass.equals(userDB.getPassword())){  
            try {  
                userDB.setPassword(newPass);
                em.persist(userDB);
                correctUpdate = true;
            } catch (Exception ex) {
                Logger.getLogger(MembershipFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return correctUpdate;
    }
    
    public boolean testingMemberUser (BigDecimal memberNumber){
        boolean testing=false;
        Membership membership = em.find(Membership.class, memberNumber);
        if(membership!=null){
            testing=true;
        }
        return testing;
    }
    
        
    public boolean autentication(BigDecimal memberNumber,String password){
        boolean passwordAutenticated = false;   
        Membership membership = em.find(Membership.class, memberNumber);
        String passwordDB = membership.getPassword();
        if(passwordDB.equals(password)){
             passwordAutenticated = true;
        }
        return passwordAutenticated;
    }
    
    public Membership getMembership(BigDecimal memberNumber){
        Membership membership = em.find(Membership.class, memberNumber);
        return membership;
    }
    public byte[] getMembershipImage(BigDecimal id) throws IOException {
        
        
       
        if(em.find(Membership.class, id).getImage() == null){
            
            return em.find(Membership.class, BigDecimal.ZERO).getImage();
        }else{
            return em.find(Membership.class, id).getImage();
        }
        
    }

    public int setMembershipImage(BigDecimal membnum, Part part) throws IOException {
//        if(part.getSize() > MAXSIZE){
//            return -1;
//        }
        byte[] b = new byte[(int)part.getSize()];
        int i = part.getInputStream().read(b);
        if(i<0){
            return i;
        }
        System.out.println(i);
        Membership memberBD = em.find(Membership.class, membnum);
        memberBD.setImage(b);
        em.persist(memberBD);
        return i;
    }
    
    public void sendTransactionEmail(BigDecimal idTransaction){
        Transaction transaction = em.find(Transaction.class, idTransaction);
        String servidorSMTP = "smtp.gmail.com";
        String puerto = "587";
        String usuario = "teleriegoinftel@gmail.com";
        String password = "inftelinftel";
   
        String destino = transaction.getMemberNumber().getEmail();
        String asunto ="Pedido "+transaction.getNorder()+" marcado como " + transaction.getStateOrder();
        String mensaje = "El pedido con ID :" + transaction.getLandId().getLandId() +
                "se ha marcado como "+ transaction.getStateOrder() + "de su terreno "+transaction.getLandId().getNameland() + " dispone de "
                + transaction.getLandId().getWMAvailable() + "m^3 de agua";
          Properties props = new Properties();
 
          props.put("mail.debug", "true");
          props.put("mail.smtp.auth", true);
          props.put("mail.smtp.starttls.enable", true);
          props.put("mail.smtp.host", servidorSMTP);
          props.put("mail.smtp.port", puerto);

          Session session = Session.getInstance(props, null);

          try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(
             destino));
           message.setSubject(asunto);
           message.setSentDate(new Date());
           message.setText(mensaje);

           Transport tr = session.getTransport("smtp");
           tr.connect(servidorSMTP, usuario, password);
           message.saveChanges();   
           tr.sendMessage(message, message.getAllRecipients());
           tr.close();

          } catch (MessagingException e) {
           e.printStackTrace();
          }
    }
}
