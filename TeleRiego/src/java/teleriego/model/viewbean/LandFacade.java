/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.model.viewbean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
    
    public void updateAdminLand(BigDecimal landId,BigInteger quantityWater){
        Land land = em.find(Land.class,landId);
        BigInteger quantityAvailable = land.getWMAvailable();
        BigInteger total = quantityAvailable.add(quantityWater);
        
        land.setWMAvailable(total);
        em.persist(land);
    }
    
    public void updateStateLand(BigDecimal landId, String state){
        Land land = em.find(Land.class,landId);
        
        land.setState(state);
        em.persist(land);
    }
    
    public void updateLastDateIrrigation(BigDecimal landId, Date todayDate) {
        Land land = em.find(Land.class,landId);
        
        land.setLastDateIrrigation(todayDate);
        em.persist(land);
    }
    
    public void updateWMAvailableHumidityLand(BigDecimal landId, BigInteger humidity, BigInteger wMAvailable){
        Land land = em.find(Land.class,landId);
        
        land.setWMAvailable(wMAvailable);
        land.setHumidity(humidity);
        em.persist(land);
        
    }
    
    public boolean getStateIrrigate(BigDecimal landId){
        Land land = em.find(Land.class,landId);
        
        if(land.getState().equals("regando"))
            return true;
        
        return false;
    }

    public String getStateIrrigateString(BigDecimal landId) {
        return em.find(Land.class,landId).getState();
    }

    public boolean thereIsWaterAvailable(BigDecimal landId) {
        int tot = Integer.parseInt(em.find(Land.class, landId).getSquareMeters().toString()) / 1000;
        int wa = Integer.parseInt(em.find(Land.class, landId).getWMAvailable().toString());
        if((wa-tot)<0){
            return false;
        }else{
            return true;
        }
    }

}
