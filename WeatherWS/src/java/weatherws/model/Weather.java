/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherws.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author inftel11
 */
@Entity
@Table(name = "WEATHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Weather.findAll", query = "SELECT w FROM Weather w"),
    @NamedQuery(name = "Weather.findByDateWeather", query = "SELECT w FROM Weather w WHERE w.dateWeather = :dateWeather"),
    @NamedQuery(name = "Weather.findByTmin", query = "SELECT w FROM Weather w WHERE w.tmin = :tmin"),
    @NamedQuery(name = "Weather.findByTmax", query = "SELECT w FROM Weather w WHERE w.tmax = :tmax"),
    @NamedQuery(name = "Weather.findByPrecipitations", query = "SELECT w FROM Weather w WHERE w.precipitations = :precipitations"),
    @NamedQuery(name = "Weather.findByProbability", query = "SELECT w FROM Weather w WHERE w.probability = :probability"),
    @NamedQuery(name = "Weather.findByPrediction", query = "SELECT w FROM Weather w WHERE w.prediction = :prediction"),
    @NamedQuery(name = "Weather.findByIdWeather", query = "SELECT w FROM Weather w WHERE w.idWeather = :idWeather")})
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_WEATHER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateWeather;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TMIN")
    private double tmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TMAX")
    private double tmax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIPITATIONS")
    private double precipitations;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROBABILITY")
    private BigInteger probability;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PREDICTION")
    private String prediction;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_WEATHER")
    private BigDecimal idWeather;

    public Weather() {
    }

    public Weather(BigDecimal idWeather) {
        this.idWeather = idWeather;
    }

    public Weather(BigDecimal idWeather, Date dateWeather, double tmin, double tmax, double precipitations, BigInteger probability, String prediction) {
        this.idWeather = idWeather;
        this.dateWeather = dateWeather;
        this.tmin = tmin;
        this.tmax = tmax;
        this.precipitations = precipitations;
        this.probability = probability;
        this.prediction = prediction;
    }

    public Date getDateWeather() {
        return dateWeather;
    }

    public void setDateWeather(Date dateWeather) {
        this.dateWeather = dateWeather;
    }

    public double getTmin() {
        return tmin;
    }

    public void setTmin(double tmin) {
        this.tmin = tmin;
    }

    public double getTmax() {
        return tmax;
    }

    public void setTmax(double tmax) {
        this.tmax = tmax;
    }

    public double getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(double precipitations) {
        this.precipitations = precipitations;
    }

    public BigInteger getProbability() {
        return probability;
    }

    public void setProbability(BigInteger probability) {
        this.probability = probability;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public BigDecimal getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(BigDecimal idWeather) {
        this.idWeather = idWeather;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWeather != null ? idWeather.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Weather)) {
            return false;
        }
        Weather other = (Weather) object;
        if ((this.idWeather == null && other.idWeather != null) || (this.idWeather != null && !this.idWeather.equals(other.idWeather))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "weatherws.model.Weather[ idWeather=" + idWeather + " ]";
    }
    
}
