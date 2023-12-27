/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class ProprietaryParameterDynamicsValidator {
		
	/**
	 * default constructor
	 */
	protected ProprietaryParameterDynamicsValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ProprietaryParameterDynamicsValidator getInstance() {
		return new ProprietaryParameterDynamicsValidator();
	}
		
	/**
	 * handles creation validation for a ProprietaryParameterDynamics
	 */
	public void validate( CreateProprietaryParameterDynamicsCommand proprietaryParameterDynamics )throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "CreateProprietaryParameterDynamicsCommand should not be null" );
//		Assert.isNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "CreateProprietaryParameterDynamicsCommand identifier should be null" );
	}

	/**
	 * handles update validation for a ProprietaryParameterDynamics
	 */
	public void validate( UpdateProprietaryParameterDynamicsCommand proprietaryParameterDynamics ) throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "UpdateProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "UpdateProprietaryParameterDynamicsCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a ProprietaryParameterDynamics
	 */
    public void validate( DeleteProprietaryParameterDynamicsCommand proprietaryParameterDynamics ) throws Exception {
		Assert.notNull( proprietaryParameterDynamics, "{commandAlias} should not be null" );
		Assert.notNull( proprietaryParameterDynamics.getProprietaryParameterDynamicsId(), "DeleteProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ProprietaryParameterDynamics
	 */
	public void validate( ProprietaryParameterDynamicsFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ProprietaryParameterDynamicsFetchOneSummary should not be null" );
		Assert.notNull( summary.getProprietaryParameterDynamicsId(), "ProprietaryParameterDynamicsFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign BooleanParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignBooleanParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignBooleanParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignBooleanParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign BooleanParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignBooleanParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign FloatParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignFloatParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignFloatParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignFloatParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignFloatParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignFloatParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign FloatParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignFloatParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign IntegerParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignIntegerParameterValueToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignIntegerParameterValueToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignIntegerParameterValueToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign IntegerParameterValue validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignIntegerParameterValueFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}
	/**
	 * handles assign ParameterNumber validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command AssignParameterNumberToProprietaryParameterDynamicsCommand
	 */	
	public void validate( AssignParameterNumberToProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "AssignParameterNumberToProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "AssignParameterNumberToProprietaryParameterDynamicsCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignParameterNumberToProprietaryParameterDynamicsCommand assignment should not be null" );
	}

	/**
	 * handles unassign ParameterNumber validation for a ProprietaryParameterDynamics
	 * 
	 * @param	command UnAssignParameterNumberFromProprietaryParameterDynamicsCommand
	 */	
	public void validate( UnAssignParameterNumberFromProprietaryParameterDynamicsCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignParameterNumberFromProprietaryParameterDynamicsCommand should not be null" );
		Assert.notNull( command.getProprietaryParameterDynamicsId(), "UnAssignParameterNumberFromProprietaryParameterDynamicsCommand identifier should not be null" );
	}


}
