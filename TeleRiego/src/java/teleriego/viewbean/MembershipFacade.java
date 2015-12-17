/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import teleriego.model.Membership;

/**
 *
 * @author inftel10
 */
@Stateless
public class MembershipFacade extends AbstractFacade<Membership> {
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
}
