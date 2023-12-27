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
 * Aggregate handler for AnalogValue as outlined for the CQRS pattern, all write responsibilities 
 * related to AnalogValue are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class AnalogValueAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public AnalogValueAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public AnalogValueAggregate(CreateAnalogValueCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateAnalogValueCommand" );
    	CreateAnalogValueEvent event = new CreateAnalogValueEvent(command.getAnalogValueId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateAnalogValueCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateAnalogValueCommand" );
    	UpdateAnalogValueEvent event = new UpdateAnalogValueEvent(command.getAnalogValueId(), command.Value());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteAnalogValueCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteAnalogValueCommand" );
        apply(new DeleteAnalogValueEvent(command.getAnalogValueId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignValueToAnalogValueCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignValueToAnalogValueCommand" );
    	
    	if (  value != null && value.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Value already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignValueToAnalogValueEvent(command.getAnalogValueId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignValueFromAnalogValueCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignValueFromAnalogValueCommand" );

    	if (  value == null )
    		throw new ProcessingException( "Value already has nothing assigned." );  

    	apply(new UnAssignValueFromAnalogValueEvent(command.getAnalogValueId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateAnalogValueEvent event) {	
    	LOGGER.info( "Event sourcing CreateAnalogValueEvent" );
    	this.analogValueId = event.getAnalogValueId();
    }
    
    @EventSourcingHandler
    void on(UpdateAnalogValueEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.value = event.getValue();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignValueToAnalogValueEvent event ) {	
    	LOGGER.info( "Event sourcing AssignValueToAnalogValueEvent" );
    	this.value = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignValueFromAnalogValueEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignValueFromAnalogValueEvent" );
		this.value = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID analogValueId;
    
    private Simple_Float value = null;

    private static final Logger LOGGER 	= Logger.getLogger(AnalogValueAggregate.class.getName());
}
