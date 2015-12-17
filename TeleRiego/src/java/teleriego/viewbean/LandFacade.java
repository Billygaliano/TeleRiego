/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import teleriego.model.Land;

/**
 *
 * @author inftel10
 */
@Stateless
public class LandFacade extends AbstractFacade<Land> {
    @PersistenceContext(unitName = "TeleRiegoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LandFacade() {
        super(Land.class);
    }
    
    public Land getLand(BigDecimal landId){
        Land specificLand = em.find(Land.class, landId);
    return specificLand;
    }
    
}
