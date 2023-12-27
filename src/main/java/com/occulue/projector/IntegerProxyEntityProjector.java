/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for IntegerProxy as outlined for the CQRS pattern.
 * 
 * Commands are handled by IntegerProxyAggregate
 * 
 * @author your_name_here
 *
 */
@Component("integerProxy-entity-projector")
public class IntegerProxyEntityProjector {
		
	// core constructor
	public IntegerProxyEntityProjector(IntegerProxyRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a IntegerProxy
	 * 
     * @param	entity IntegerProxy
     */
    public IntegerProxy create( IntegerProxy entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a IntegerProxy
	 * 
     * @param	entity IntegerProxy
     */
    public IntegerProxy update( IntegerProxy entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a IntegerProxy
	 * 
     * @param	id		UUID
     */
    public IntegerProxy delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    IntegerProxy entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the IntegerProxy via an FindIntegerProxyQuery
     * @return 	query	FindIntegerProxyQuery
     */
    @SuppressWarnings("unused")
    public IntegerProxy find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a IntegerProxy - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all IntegerProxys
     *
     * @param	query	FindAllIntegerProxyQuery 
     * @return 	List<IntegerProxy> 
     */
    @SuppressWarnings("unused")
    public List<IntegerProxy> findAll( FindAllIntegerProxyQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all IntegerProxy - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final IntegerProxyRepository repository;
    @Autowired
	@Qualifier(value = "aCDCConverter-entity-projector")
	ACDCConverterEntityProjector ACDCConverterProjector;
    @Autowired
	@Qualifier(value = "busNameMarker-entity-projector")
	BusNameMarkerEntityProjector BusNameMarkerProjector;
    @Autowired
	@Qualifier(value = "accumulatorLimit-entity-projector")
	AccumulatorLimitEntityProjector AccumulatorLimitProjector;
    @Autowired
	@Qualifier(value = "command-entity-projector")
	CommandEntityProjector CommandProjector;
    @Autowired
	@Qualifier(value = "aCDCTerminal-entity-projector")
	ACDCTerminalEntityProjector ACDCTerminalProjector;
    @Autowired
	@Qualifier(value = "asynchronousMachine-entity-projector")
	AsynchronousMachineEntityProjector AsynchronousMachineProjector;
    @Autowired
	@Qualifier(value = "nonlinearShuntCompensatorPoint-entity-projector")
	NonlinearShuntCompensatorPointEntityProjector NonlinearShuntCompensatorPointProjector;
    @Autowired
	@Qualifier(value = "powerTransformerEnd-entity-projector")
	PowerTransformerEndEntityProjector PowerTransformerEndProjector;
    @Autowired
	@Qualifier(value = "shuntCompensator-entity-projector")
	ShuntCompensatorEntityProjector ShuntCompensatorProjector;
    @Autowired
	@Qualifier(value = "tapChanger-entity-projector")
	TapChangerEntityProjector TapChangerProjector;
    @Autowired
	@Qualifier(value = "tapChangerTablePoint-entity-projector")
	TapChangerTablePointEntityProjector TapChangerTablePointProjector;
    @Autowired
	@Qualifier(value = "transformerEnd-entity-projector")
	TransformerEndEntityProjector TransformerEndProjector;
    @Autowired
	@Qualifier(value = "excANS-entity-projector")
	ExcANSEntityProjector ExcANSProjector;
    @Autowired
	@Qualifier(value = "pssIEEE2B-entity-projector")
	PssIEEE2BEntityProjector PssIEEE2BProjector;
    @Autowired
	@Qualifier(value = "windDynamicsLookupTable-entity-projector")
	WindDynamicsLookupTableEntityProjector WindDynamicsLookupTableProjector;
    @Autowired
	@Qualifier(value = "proprietaryParameterDynamics-entity-projector")
	ProprietaryParameterDynamicsEntityProjector ProprietaryParameterDynamicsProjector;
    @Autowired
	@Qualifier(value = "diagramObject-entity-projector")
	DiagramObjectEntityProjector DiagramObjectProjector;

    private static final Logger LOGGER 	= Logger.getLogger(IntegerProxyEntityProjector.class.getName());

}
