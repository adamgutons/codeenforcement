/*
 * Copyright (C) 2017 cedba
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

import com.tcvcog.tcvce.coordinators.CodeCoordinator;
import com.tcvcog.tcvce.coordinators.UserCoordinator;
import com.tcvcog.tcvce.integration.CodeIntegrator;
import com.tcvcog.tcvce.integration.MunicipalityIntegrator;
import com.tcvcog.tcvce.util.Constants;
import com.tcvcog.tcvce.integration.PostgresConnectionFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener; 

import javax.servlet.annotation.WebListener;



/**
 *
 * @author cedba
 */
@WebListener
public class Initializer implements ServletContextListener{

    /**
     * Creates a new instance of Initializer
     */
    public Initializer() {
        System.out.println("Creating Initializer Bean");
        
    
    }
    
   @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Intilizer.contextInitialized -- start");
        
        ServletContext servletContext = event.getServletContext();
        UserCoordinator userCoordinator = new UserCoordinator();
        
        System.out.println("Intilizer.contextInitialized -- creating DB Connection");
        PostgresConnectionFactory con = new PostgresConnectionFactory();
        servletContext.setAttribute("dBConnection", con);
        // this setAttribute system is not working as planned.
        
        //servletContext.setAttribute(Constants.USER_COORDINATOR_SCOPE, userCoordinator);
        servletContext.setAttribute(Constants.USER_COORDINATOR_KEY, userCoordinator);
        
        CodeCoordinator codeCoordinator = new CodeCoordinator();
        servletContext.setAttribute("codeCoordinator", codeCoordinator);
        
        CodeIntegrator codeIntegrator = new CodeIntegrator();
        servletContext.setAttribute("codeIntegrator", codeIntegrator);
        
        MunicipalityIntegrator munigrator = new MunicipalityIntegrator();
        servletContext.setAttribute("municipalitygrator", munigrator);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event){
        
    }
    
    
    
}