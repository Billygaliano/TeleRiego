/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import teleriego.model.Transaction;

/**
 *
 * @author inftel10
 */
@Stateless
public class TransactionFacade extends AbstractFacade<Transaction> {
    @PersistenceContext(unitName = "TeleRiegoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionFacade() {
        super(Transaction.class);
    }
    
}
