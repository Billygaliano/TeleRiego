/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teleriego.devicesimulator;

import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import teleriego.controller.ServletStartIrrigation;
import teleriego.controller.ServletStopIrrigation;
import teleriego.model.Land;
import teleriego.viewbean.LandFacade;

/**
 *
 * @author inftel12
 */
public class DeviceSimulator implements Runnable{
    LandFacade landFacade = lookupLandFacadeBean();
    BigDecimal landId;
    Thread threadIrrigation;

    public DeviceSimulator(BigDecimal landId) {
        this.landId = landId;
        threadIrrigation = new Thread(this);
        threadIrrigation.start();
    }
    
    @Override
    public void run() {
        landFacade.updateStateLand(landId, "regando");
        
        Land specificLand = landFacade.getLand(landId);
        BigInteger wMAvailable = specificLand.getWMAvailable();
        BigInteger humidity = specificLand.getHumidity(); 
        int trueWMAvailable = wMAvailable.intValue();
        int trueHumidity = humidity.intValue();
        int area = specificLand.getSquareMeters().intValue();
        int spentCubicMetersPerPulse = area/1000;
        
        while (specificLand.getState().equals("regando") && landFacade.thereIsWaterAvailable(wMAvailable)) {
            specificLand = landFacade.getLand(landId);
            
            System.out.println("Resta: " + (trueWMAvailable - spentCubicMetersPerPulse) + "\n");
            
            if((trueWMAvailable - spentCubicMetersPerPulse) <= 0){
                landFacade.updateStateLand(landId, "parado");
                specificLand.setState("parado");
            }
            if((trueWMAvailable - spentCubicMetersPerPulse) >= 0){
                trueWMAvailable = trueWMAvailable - spentCubicMetersPerPulse;
                trueHumidity++;

                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServletStartIrrigation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("Nombre del terreno: " + specificLand.getNameland() + " - Humedad: " + trueHumidity + " - Agua disponible: " + trueWMAvailable + " - Estado: " + specificLand.getState() + "\n");

            humidity = BigInteger.valueOf(trueHumidity);
            wMAvailable = BigInteger.valueOf(trueWMAvailable);
            landFacade.updateWMAvailableHumidityLand(landId, humidity, wMAvailable);
        }        
    }    

    private LandFacade lookupLandFacadeBean() {
        try {
            Context c = new InitialContext();
            return (LandFacade) c.lookup("java:global/TeleRiego/LandFacade!teleriego.viewbean.LandFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
