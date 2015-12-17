/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

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
    
}
