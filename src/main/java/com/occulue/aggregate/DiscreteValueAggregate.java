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
 * Aggregate handler for DiscreteValue as outlined for the CQRS pattern, all write responsibilities 
 * related to DiscreteValue are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DiscreteValueAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DiscreteValueAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DiscreteValueAggregate(CreateDiscreteValueCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDiscreteValueCommand" );
    	CreateDiscreteValueEvent event = new CreateDiscreteValueEvent(command.getDiscreteValueId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDiscreteValueCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDiscreteValueCommand" );
    	UpdateDiscreteValueEvent event = new UpdateDiscreteValueEvent(command.getDiscreteValueId(), command.Value());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDiscreteValueCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDiscreteValueCommand" );
        apply(new DeleteDiscreteValueEvent(command.getDiscreteValueId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToDiscreteValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToDiscreteValueCommand" );
    	
    	if (  value != null && value.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignValueToDiscreteValueEvent(command.getDiscreteValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromDiscreteValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromDiscreteValueCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromDiscreteValueEvent(command.getDiscreteValueId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDiscreteValueEvent event) {	
    	LOGGER.info( "Event sourcing CreateDiscreteValueEvent" );
    	this.discreteValueId = event.getDiscreteValueId();
    }
    
    @EventSourcingHandler
    void on(UpdateDiscreteValueEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToDiscreteValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToDiscreteValueEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromDiscreteValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromDiscreteValueEvent" );
		this.value = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID discreteValueId;
    
    private IntegerProxy value = null;

    private static final Logger LOGGER 	= Logger.getLogger(DiscreteValueAggregate.class.getName());
}
