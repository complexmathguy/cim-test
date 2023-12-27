package com.occulue.aggregate;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

/**
 * Aggregate handler for VolumeFlowRate as outlined for the CQRS pattern, all write responsibilities 
 * related to VolumeFlowRate are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class VolumeFlowRateAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public VolumeFlowRateAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public VolumeFlowRateAggregate(CreateVolumeFlowRateCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateVolumeFlowRateCommand" );
    	CreateVolumeFlowRateEvent event = new CreateVolumeFlowRateEvent(command.getVolumeFlowRateId(), command.DenominatorMultiplier(), command.DenominatorUnit(), command.Multiplier(), command.Unit());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateVolumeFlowRateCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateVolumeFlowRateCommand" );
    	UpdateVolumeFlowRateEvent event = new UpdateVolumeFlowRateEvent(command.getVolumeFlowRateId(), command.DenominatorMultiplier(), command.DenominatorUnit(), command.Multiplier(), command.Unit(), command.Value());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteVolumeFlowRateCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteVolumeFlowRateCommand" );
        apply(new DeleteVolumeFlowRateEvent(command.getVolumeFlowRateId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToVolumeFlowRateCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToVolumeFlowRateCommand" );
    	
    	if (  value != null && value.getFloatProxyId() == command.getAssignment().getFloatProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getFloatProxyId() );  
    		
        apply(new AssignValueToVolumeFlowRateEvent(command.getVolumeFlowRateId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromVolumeFlowRateCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromVolumeFlowRateCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromVolumeFlowRateEvent(command.getVolumeFlowRateId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateVolumeFlowRateEvent event) {	
    	LOGGER.info( "Event sourcing CreateVolumeFlowRateEvent" );
    	this.volumeFlowRateId = event.getVolumeFlowRateId();
        this.denominatorMultiplier = event.getDenominatorMultiplier();
        this.denominatorUnit = event.getDenominatorUnit();
        this.multiplier = event.getMultiplier();
        this.unit = event.getUnit();
    }
    
    @EventSourcingHandler
    void on(UpdateVolumeFlowRateEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.denominatorMultiplier = event.getDenominatorMultiplier();
        this.denominatorUnit = event.getDenominatorUnit();
        this.multiplier = event.getMultiplier();
        this.unit = event.getUnit();
        this.value = event.getValue();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToVolumeFlowRateEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToVolumeFlowRateEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromVolumeFlowRateEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromVolumeFlowRateEvent" );
		this.value = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID volumeFlowRateId;
    
    private UnitMultiplier denominatorMultiplier;
    private UnitSymbol denominatorUnit;
    private UnitMultiplier multiplier;
    private UnitSymbol unit;
    private FloatProxy value = null;
    private GovHydroFrancis qn = null;

    private static final Logger LOGGER 	= Logger.getLogger(VolumeFlowRateAggregate.class.getName());
}
