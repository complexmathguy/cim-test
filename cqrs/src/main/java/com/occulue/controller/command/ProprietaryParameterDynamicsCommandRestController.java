/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity ProprietaryParameterDynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/ProprietaryParameterDynamics")
public class ProprietaryParameterDynamicsCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a ProprietaryParameterDynamics.  if not key provided, calls create, otherwise calls save
     * @param		ProprietaryParameterDynamics	proprietaryParameterDynamics
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateProprietaryParameterDynamicsCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().createProprietaryParameterDynamics( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a ProprietaryParameterDynamics.  if no key provided, calls create, otherwise calls save
     * @param		ProprietaryParameterDynamics proprietaryParameterDynamics
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) UpdateProprietaryParameterDynamicsCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the UpdateProprietaryParameterDynamicsCommand
			// -----------------------------------------------
			completableFuture = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().updateProprietaryParameterDynamics(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "ProprietaryParameterDynamicsController:update() - successfully update ProprietaryParameterDynamics - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a ProprietaryParameterDynamics entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID proprietaryParameterDynamicsId  ) {
    	CompletableFuture<Void> completableFuture = null;
		DeleteProprietaryParameterDynamicsCommand command = new DeleteProprietaryParameterDynamicsCommand( proprietaryParameterDynamicsId );

    	try {
        	ProprietaryParameterDynamicsBusinessDelegate delegate = ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted ProprietaryParameterDynamics with key " + command.getProprietaryParameterDynamicsId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	

    /**
     * save BooleanParameterValue on ProprietaryParameterDynamics
     * @param		command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignBooleanParameterValue")
	public void assignBooleanParameterValue( @RequestBody AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignBooleanParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign BooleanParameterValue", exc );
        }
	}

    /**
     * unassign BooleanParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignBooleanParameterValue")
	public void unAssignBooleanParameterValue( @RequestBody(required=true)  UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignBooleanParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign BooleanParameterValue", exc );
		}
	}
	
    /**
     * save FloatParameterValue on ProprietaryParameterDynamics
     * @param		command AssignFloatParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignFloatParameterValue")
	public void assignFloatParameterValue( @RequestBody AssignFloatParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignFloatParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign FloatParameterValue", exc );
        }
	}

    /**
     * unassign FloatParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignFloatParameterValue")
	public void unAssignFloatParameterValue( @RequestBody(required=true)  UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignFloatParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign FloatParameterValue", exc );
		}
	}
	
    /**
     * save IntegerParameterValue on ProprietaryParameterDynamics
     * @param		command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignIntegerParameterValue")
	public void assignIntegerParameterValue( @RequestBody AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignIntegerParameterValue( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign IntegerParameterValue", exc );
        }
	}

    /**
     * unassign IntegerParameterValue on ProprietaryParameterDynamics
     * @param		 command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignIntegerParameterValue")
	public void unAssignIntegerParameterValue( @RequestBody(required=true)  UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignIntegerParameterValue( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign IntegerParameterValue", exc );
		}
	}
	
    /**
     * save ParameterNumber on ProprietaryParameterDynamics
     * @param		command AssignParameterNumberToProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/assignParameterNumber")
	public void assignParameterNumber( @RequestBody AssignParameterNumberToProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().assignParameterNumber( command );   
		}
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, "Failed to assign ParameterNumber", exc );
        }
	}

    /**
     * unassign ParameterNumber on ProprietaryParameterDynamics
     * @param		 command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand
     */     
	@PutMapping("/unAssignParameterNumber")
	public void unAssignParameterNumber( @RequestBody(required=true)  UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command ) {
		try {
			ProprietaryParameterDynamicsBusinessDelegate.getProprietaryParameterDynamicsInstance().unAssignParameterNumber( command );   
		}
		catch( Exception exc ) {
			LOGGER.log( Level.WARNING, "Failed to unassign ParameterNumber", exc );
		}
	}
	


//************************************************************************    
// Attributes
//************************************************************************
    protected ProprietaryParameterDynamics proprietaryParameterDynamics = null;
    private static final Logger LOGGER = Logger.getLogger(ProprietaryParameterDynamicsCommandRestController.class.getName());
    
}
