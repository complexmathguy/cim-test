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
 * Aggregate handler for ACDCTerminal as outlined for the CQRS pattern, all write responsibilities 
 * related to ACDCTerminal are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ACDCTerminalAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ACDCTerminalAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ACDCTerminalAggregate(CreateACDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateACDCTerminalCommand" );
    	CreateACDCTerminalEvent event = new CreateACDCTerminalEvent(command.getACDCTerminalId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateACDCTerminalCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateACDCTerminalCommand" );
    	UpdateACDCTerminalEvent event = new UpdateACDCTerminalEvent(command.getACDCTerminalId(), command.SequenceNumber());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteACDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteACDCTerminalCommand" );
        apply(new DeleteACDCTerminalEvent(command.getACDCTerminalId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignSequenceNumberToACDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSequenceNumberToACDCTerminalCommand" );
    	
    	if (  sequenceNumber != null && sequenceNumber.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "SequenceNumber already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignSequenceNumberToACDCTerminalEvent(command.getACDCTerminalId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSequenceNumberFromACDCTerminalCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSequenceNumberFromACDCTerminalCommand" );

    	if (  sequenceNumber == null )
    		throw new ProcessingException( "SequenceNumber already has nothing assigned." );  

    	apply(new UnAssignSequenceNumberFromACDCTerminalEvent(command.getACDCTerminalId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateACDCTerminalEvent event) {	
    	LOGGER.info( "Event sourcing CreateACDCTerminalEvent" );
    	this.aCDCTerminalId = event.getACDCTerminalId();
    }
    
    @EventSourcingHandler
    void on(UpdateACDCTerminalEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.sequenceNumber = event.getSequenceNumber();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignSequenceNumberToACDCTerminalEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSequenceNumberToACDCTerminalEvent" );
    	this.sequenceNumber = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSequenceNumberFromACDCTerminalEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSequenceNumberFromACDCTerminalEvent" );
		this.sequenceNumber = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID aCDCTerminalId;
    
    private IntegerProxy sequenceNumber = null;

    private static final Logger LOGGER 	= Logger.getLogger(ACDCTerminalAggregate.class.getName());
}
