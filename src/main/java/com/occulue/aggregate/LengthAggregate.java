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
 * Aggregate handler for Length as outlined for the CQRS pattern, all write responsibilities 
 * related to Length are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class LengthAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public LengthAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public LengthAggregate(CreateLengthCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateLengthCommand" );
    	CreateLengthEvent event = new CreateLengthEvent(command.getLengthId(), command.Multiplier(), command.Unit());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateLengthCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateLengthCommand" );
    	UpdateLengthEvent event = new UpdateLengthEvent(command.getLengthId(), command.Multiplier(), command.Unit(), command.Value());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteLengthCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteLengthCommand" );
        apply(new DeleteLengthEvent(command.getLengthId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToLengthCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToLengthCommand" );
    	
    	if (  value != null && value.getFloatProxyId() == command.getAssignment().getFloatProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getFloatProxyId() );  
    		
        apply(new AssignValueToLengthEvent(command.getLengthId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromLengthCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromLengthCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromLengthEvent(command.getLengthId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateLengthEvent event) {	
    	LOGGER.info( "Event sourcing CreateLengthEvent" );
    	this.lengthId = event.getLengthId();
        this.multiplier = event.getMultiplier();
        this.unit = event.getUnit();
    }
    
    @EventSourcingHandler
    void on(UpdateLengthEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.multiplier = event.getMultiplier();
        this.unit = event.getUnit();
        this.value = event.getValue();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToLengthEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToLengthEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromLengthEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromLengthEvent" );
		this.value = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID lengthId;
    
    private UnitMultiplier multiplier;
    private UnitSymbol unit;
    private FloatProxy value = null;
    private DCLineSegment length = null;
    private MutualCoupling distance11 = null;
    private MutualCoupling distance12 = null;
    private MutualCoupling distance21 = null;
    private MutualCoupling distance22 = null;
    private GovHydroFrancis h1 = null;
    private GovHydroFrancis h2 = null;
    private GovHydroFrancis hn = null;
    private GovHydroFrancis zsfc = null;

    private static final Logger LOGGER 	= Logger.getLogger(LengthAggregate.class.getName());
}
