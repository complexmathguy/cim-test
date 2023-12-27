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
 * Aggregate handler for RotationSpeed as outlined for the CQRS pattern, all write responsibilities 
 * related to RotationSpeed are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RotationSpeedAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RotationSpeedAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RotationSpeedAggregate(CreateRotationSpeedCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRotationSpeedCommand" );
    	CreateRotationSpeedEvent event = new CreateRotationSpeedEvent(command.getRotationSpeedId(), command.DenominatorMultiplier(), command.DenominatorUnit(), command.Multiplier(), command.Unit());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRotationSpeedCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRotationSpeedCommand" );
    	UpdateRotationSpeedEvent event = new UpdateRotationSpeedEvent(command.getRotationSpeedId(), command.DenominatorMultiplier(), command.DenominatorUnit(), command.Multiplier(), command.Unit(), command.Value());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRotationSpeedCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRotationSpeedCommand" );
        apply(new DeleteRotationSpeedEvent(command.getRotationSpeedId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToRotationSpeedCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToRotationSpeedCommand" );
    	
    	if (  value != null && value.getFloatProxyId() == command.getAssignment().getFloatProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getFloatProxyId() );  
    		
        apply(new AssignValueToRotationSpeedEvent(command.getRotationSpeedId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromRotationSpeedCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromRotationSpeedCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromRotationSpeedEvent(command.getRotationSpeedId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRotationSpeedEvent event) {	
    	LOGGER.info( "Event sourcing CreateRotationSpeedEvent" );
    	this.rotationSpeedId = event.getRotationSpeedId();
        this.denominatorMultiplier = event.getDenominatorMultiplier();
        this.denominatorUnit = event.getDenominatorUnit();
        this.multiplier = event.getMultiplier();
        this.unit = event.getUnit();
    }
    
    @EventSourcingHandler
    void on(UpdateRotationSpeedEvent event) {
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
    void on(AssignValueToRotationSpeedEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToRotationSpeedEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromRotationSpeedEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromRotationSpeedEvent" );
		this.value = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID rotationSpeedId;
    
    private UnitMultiplier denominatorMultiplier;
    private UnitSymbol denominatorUnit;
    private UnitMultiplier multiplier;
    private UnitSymbol unit;
    private FloatProxy value = null;
    private AsynchronousMachine nominalSpeed = null;

    private static final Logger LOGGER 	= Logger.getLogger(RotationSpeedAggregate.class.getName());
}
