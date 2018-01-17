/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcvcog.tcvce.application;

import com.tcvcog.tcvce.domain.IntegrationException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.LinkedList;
import com.tcvcog.tcvce.entities.Property;
import javax.faces.event.ActionEvent;
import com.tcvcog.tcvce.integration.PropertyIntegrator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;

/** 
 *
 * @author Eric Darsow
 */
public class PropSearchBB extends BackingBeanUtils implements Serializable {

    private String parid;
    private String address;
    private String addrPart;
    Connection con = null;
    
    private Property selectedProperty;
    private LinkedList<Property> propList;
    private UIInput addressInput;
    
    private int selectedMuniCode;
    
    /**
     * Creates a new instance of PropSearchBean
     */
    public PropSearchBB() {

        
    } // close constructor
    
    public String searchForPropertiesAllMunis(){
        System.out.println("PropSearchBean.searchForProperties");
        PropertyIntegrator pi = new PropertyIntegrator();
        
        try {
            propList = pi.searchForProperties(addrPart);
            getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Your search completed with " + propList.size() + " results", ""));
            
        } catch (IntegrationException ex) {
            System.out.println(ex);
            getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        " Unable to complete a property search! ", ""));
        }
        return "";
    }
    
    public void searchForPropertiesSingleMuni(ActionEvent event){
        System.out.println("PropSearchBean.searchForProperties");
        PropertyIntegrator pi = new PropertyIntegrator();
        
        try {
            propList = pi.searchForProperties(addrPart);
            getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Your search completed with " + propList.size() + " results", ""));
            
        } catch (IntegrationException ex) {
            System.out.println(ex);
            getFacesContext().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        " Unable to complete a property search! ", ""));
        }
    }
    
    public String viewProperty(){
        SessionManager sm = getSessionManager();
        sm.getVisit().setActiveProp(selectedProperty);
        return "propertyProfile";
    }

    /**
     * @return the parid
     */
    public String getParid() {
        return this.parid;
    }

    /**
     * @param parid the parid to set
     */
    public void setParid(String parid) {
        this.parid = parid;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the addrPart
     */
    public String getAddrPart() {
        return addrPart;
    }

    /**
     * @param addrPart the addrPart to set
     */
    public void setAddrPart(String addrPart) {
        this.addrPart = addrPart;
    }

    /**
     * @return the propList
     */
    public List<Property> getPropList() {
        return propList;
    }

    /**
     * @param propList the propList to set
     */
    public void setPropList(LinkedList<Property> propList) {
        this.propList = propList;
    }

    /**
     * @return the selectedProperty
     */
    public Property getSelectedProperty() {
        return selectedProperty;
    }

    /**
     * @param selectedProperty the selectedProperty to set
     */
    public void setSelectedProperty(Property selectedProperty) {
        this.selectedProperty = selectedProperty;
    }

    /**
     * @return the addressInput
     */
    public UIInput getAddressInput() {
        return addressInput;
    }

    /**
     * @param addressInput the addressInput to set
     */
    public void setAddressInput(UIInput addressInput) {
        this.addressInput = addressInput;
    }

    /**
     * @return the selectedMuniCode
     */
    public int getSelectedMuniCode() {
        return selectedMuniCode;
    }

    /**
     * @param selectedMuniCode the selectedMuniCode to set
     */
    public void setSelectedMuniCode(int selectedMuniCode) {
        this.selectedMuniCode = selectedMuniCode;
    }
    
    
    
}