/*
 * Copyright (C) 2018 Turtle Creek Valley
Council of Governments, PA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tcvcog.tcvce.application;

import com.tcvcog.tcvce.coordinators.ViolationCoordinator;
import com.tcvcog.tcvce.domain.IntegrationException;
import com.tcvcog.tcvce.entities.CodeSet;
import com.tcvcog.tcvce.entities.CodeViolation;
import com.tcvcog.tcvce.entities.EnforcableCodeElement;
import com.tcvcog.tcvce.integration.CodeIntegrator;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.application.FacesMessage;

/**
 *
 * @author sylvia
 */
public class ViolationSelectElementBB extends BackingBeanUtils implements Serializable {

    private EnforcableCodeElement selectedViolatedEnfElement;
    private LinkedList<EnforcableCodeElement> enfElementList;
    private CodeSet currentCodeSet;

    /**
     * Creates a new instance of ViolationSelectElementBB
     */
    public ViolationSelectElementBB() {
    }

    public String useSelectedElement() {
        SessionManager sm = getSessionManager();
        ViolationCoordinator vc = getViolationCoordinator();
        CodeViolation cv;
        if (selectedViolatedEnfElement != null && sm.getVisit() != null) {
             cv = vc.generateNewCodeViolation(sm.getVisit().getActiveCase(), 
                    selectedViolatedEnfElement);
            sm.getVisit().setActiveCodeViolation(cv);
//            System.out.println("ViolationSelectElementBB.useSelectedElement | Selected Enf Element: "
//                    + selectedViolatedEnfElement.getCodeElement().getOrdchapterTitle());
            return "violationAdd";

        } else {
            getFacesContext().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Ooops: Can't continue -- Please select an element from the list.", ""));
            return "";
        }

    }

    /**
     * @return the selectedViolatedEnfElement
     */
    public EnforcableCodeElement getSelectedViolatedEnfElement() {
        return selectedViolatedEnfElement;
    }

    /**
     * @return the enfElementList
     */
    public LinkedList<EnforcableCodeElement> getEnfElementList() {
        SessionManager sm = getSessionManager();
        CodeIntegrator integrator = getCodeIntegrator();
        CodeSet codeSet = sm.getVisit().getActiveCodeSet();
        System.out.println("ViolationSelectElement.getElementList| retrievedset: " + codeSet);

        if (codeSet != null) {
            int setID = codeSet.getCodeSetID();
            try {
                enfElementList = integrator.getEnforcableCodeElementList(setID);
//                System.out.println("ViolationSelectElement.getElementList| retrievdedList: "
//                        + enfElementList.peek().getCodeElement().getOrdchapterTitle());
//                getFacesContext().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN, 
//                                "Loaded Enforcable Code Elements for set named: " +codeSet.getCodeSetName() , ""));
            } catch (IntegrationException ex) {
                System.out.println(ex.toString());
                getFacesContext().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "No Enforcable Code Elements were found with SetID: " + setID, ""));
            }
        }
        return enfElementList;
    }

    /**
     * @param selectedViolatedEnfElement the selectedViolatedEnfElement to set
     */
    public void setSelectedViolatedEnfElement(EnforcableCodeElement selectedViolatedEnfElement) {
        this.selectedViolatedEnfElement = selectedViolatedEnfElement;
    }

    /**
     * @param enfElementList the enfElementList to set
     */
    public void setEnfElementList(LinkedList<EnforcableCodeElement> enfElementList) {
        this.enfElementList = enfElementList;
    }

    /**
     * @return the currentCodeSet
     */
    public CodeSet getCurrentCodeSet() {
        return currentCodeSet;
    }

    /**
     * @param currentCodeSet the currentCodeSet to set
     */
    public void setCurrentCodeSet(CodeSet currentCodeSet) {
        this.currentCodeSet = currentCodeSet;
    }

}
