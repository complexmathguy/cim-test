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

public class GenICompensationForGenJValidator {
		
	/**
	 * default constructor
	 */
	protected GenICompensationForGenJValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public GenICompensationForGenJValidator getInstance() {
		return new GenICompensationForGenJValidator();
	}
		
	/**
	 * handles creation validation for a GenICompensationForGenJ
	 */
	public void validate( CreateGenICompensationForGenJCommand genICompensationForGenJ )throws Exception {
		Assert.notNull( genICompensationForGenJ, "CreateGenICompensationForGenJCommand should not be null" );
//		Assert.isNull( genICompensationForGenJ.getGenICompensationForGenJId(), "CreateGenICompensationForGenJCommand identifier should be null" );
	}

	/**
	 * handles update validation for a GenICompensationForGenJ
	 */
	public void validate( UpdateGenICompensationForGenJCommand genICompensationForGenJ ) throws Exception {
		Assert.notNull( genICompensationForGenJ, "UpdateGenICompensationForGenJCommand should not be null" );
		Assert.notNull( genICompensationForGenJ.getGenICompensationForGenJId(), "UpdateGenICompensationForGenJCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a GenICompensationForGenJ
	 */
    public void validate( DeleteGenICompensationForGenJCommand genICompensationForGenJ ) throws Exception {
		Assert.notNull( genICompensationForGenJ, "{commandAlias} should not be null" );
		Assert.notNull( genICompensationForGenJ.getGenICompensationForGenJId(), "DeleteGenICompensationForGenJCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a GenICompensationForGenJ
	 */
	public void validate( GenICompensationForGenJFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "GenICompensationForGenJFetchOneSummary should not be null" );
		Assert.notNull( summary.getGenICompensationForGenJId(), "GenICompensationForGenJFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Rcij validation for a GenICompensationForGenJ
	 * 
	 * @param	command AssignRcijToGenICompensationForGenJCommand
	 */	
	public void validate( AssignRcijToGenICompensationForGenJCommand command ) throws Exception {
		Assert.notNull( command, "AssignRcijToGenICompensationForGenJCommand should not be null" );
		Assert.notNull( command.getGenICompensationForGenJId(), "AssignRcijToGenICompensationForGenJCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignRcijToGenICompensationForGenJCommand assignment should not be null" );
	}

	/**
	 * handles unassign Rcij validation for a GenICompensationForGenJ
	 * 
	 * @param	command UnAssignRcijFromGenICompensationForGenJCommand
	 */	
	public void validate( UnAssignRcijFromGenICompensationForGenJCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignRcijFromGenICompensationForGenJCommand should not be null" );
		Assert.notNull( command.getGenICompensationForGenJId(), "UnAssignRcijFromGenICompensationForGenJCommand identifier should not be null" );
	}
	/**
	 * handles assign Xcij validation for a GenICompensationForGenJ
	 * 
	 * @param	command AssignXcijToGenICompensationForGenJCommand
	 */	
	public void validate( AssignXcijToGenICompensationForGenJCommand command ) throws Exception {
		Assert.notNull( command, "AssignXcijToGenICompensationForGenJCommand should not be null" );
		Assert.notNull( command.getGenICompensationForGenJId(), "AssignXcijToGenICompensationForGenJCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignXcijToGenICompensationForGenJCommand assignment should not be null" );
	}

	/**
	 * handles unassign Xcij validation for a GenICompensationForGenJ
	 * 
	 * @param	command UnAssignXcijFromGenICompensationForGenJCommand
	 */	
	public void validate( UnAssignXcijFromGenICompensationForGenJCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignXcijFromGenICompensationForGenJCommand should not be null" );
		Assert.notNull( command.getGenICompensationForGenJId(), "UnAssignXcijFromGenICompensationForGenJCommand identifier should not be null" );
	}


}
