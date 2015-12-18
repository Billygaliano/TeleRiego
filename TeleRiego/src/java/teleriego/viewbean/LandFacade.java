/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.viewbean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
    
    public boolean suggestIrrigation(BigInteger humidity, Date lastDateIrrigation, BigInteger wMAvailable, JsonArray weatherPredictions){     
        
        if(!thereIsWaterAvailable(wMAvailable)){
            return false;
        }
        else if(thereIsNoHumidity(humidity) && thereIsRain(weatherPredictions) < 5){
            return true;
        }        
        else if(thereIsABitOfHumidity(humidity)  && calculateDifOnMonths(lastDateIrrigation) > 1 && thereIsRain(weatherPredictions) < 3){
            return true;
        }      
        else if(thereIsEnoughtHumidity(humidity) && calculateDifOnMonths(lastDateIrrigation) > 3 && thereIsRain(weatherPredictions) < 1){
            return true;
        }     
        else if(thereIsFullHumidity(humidity) && calculateDifOnMonths(lastDateIrrigation) > 7 && thereIsRain(weatherPredictions) == 0){
            return true;
        }
        
        return false;
    }
    
    private boolean thereIsNoHumidity(BigInteger humidity){
        if(BigInteger.valueOf(20).compareTo(humidity) > 0)
            return true;
        return false;
    }
    
    private boolean thereIsABitOfHumidity(BigInteger humidity){
        if((BigInteger.valueOf(20).compareTo(humidity) < 0) && BigInteger.valueOf(60).compareTo(humidity) > 0)
            return true;
        return false;
    }
    
    private boolean thereIsEnoughtHumidity(BigInteger humidity){
        if((BigInteger.valueOf(60).compareTo(humidity) < 0) && BigInteger.valueOf(80).compareTo(humidity) > 0)
            return true;
        return false;
    }
    
    private boolean thereIsFullHumidity(BigInteger humidity){
        if((BigInteger.valueOf(80).compareTo(humidity) < 0) && BigInteger.valueOf(100).compareTo(humidity) > 0)
            return true;
        return false;
    }
    
    private int calculateDifOnMonths(Date lastDateIrrigation) {
        Date now = new Date();
        int months = 0;
        
        long dif = 0;
        dif = now.getTime() - lastDateIrrigation.getTime();
        months = (int) (dif / (1000 * 60 * 60 * 24 * 30));
        
        return months;
    }
    
    private boolean thereIsWaterAvailable(BigInteger wMAvailable){
        if(BigInteger.valueOf(0).compareTo(wMAvailable) < 0)
            return true;
        return false;
    }
    
    private float thereIsRain(JsonArray weatherPredictions){
        float averageRain = 0;
        
        for (JsonObject wheaterPredictionPerDay : weatherPredictions.getValuesAs(JsonObject.class)) {
            float precipitations = wheaterPredictionPerDay.getInt("precipitations");
            int probability = wheaterPredictionPerDay.getInt("probability");
            
            averageRain = averageRain + (precipitations*(probability/100));
        }
        
        return averageRain;
    }
    
}
