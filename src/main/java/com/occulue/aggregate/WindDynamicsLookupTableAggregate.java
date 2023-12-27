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
 * Aggregate handler for WindDynamicsLookupTable as outlined for the CQRS pattern, all write responsibilities 
 * related to WindDynamicsLookupTable are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindDynamicsLookupTableAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindDynamicsLookupTableAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindDynamicsLookupTableAggregate(CreateWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindDynamicsLookupTableCommand" );
    	CreateWindDynamicsLookupTableEvent event = new CreateWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.LookupTableFunctionType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindDynamicsLookupTableCommand" );
    	UpdateWindDynamicsLookupTableEvent event = new UpdateWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.Input(), command.LookupTableFunctionType(), command.Output(), command.Sequence());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindDynamicsLookupTableCommand" );
        apply(new DeleteWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignInputToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignInputToWindDynamicsLookupTableCommand" );
    	
    	if (  input != null && input.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Input already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignInputToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignInputFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignInputFromWindDynamicsLookupTableCommand" );

    	if (  input == null )
    		throw new ProcessingException( "Input already has nothing assigned." );  

    	apply(new UnAssignInputFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignOutputToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOutputToWindDynamicsLookupTableCommand" );
    	
    	if (  output != null && output.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "Output already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignOutputToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOutputFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOutputFromWindDynamicsLookupTableCommand" );

    	if (  output == null )
    		throw new ProcessingException( "Output already has nothing assigned." );  

    	apply(new UnAssignOutputFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }
    @CommandHandler
    public void handle(AssignSequenceToWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignSequenceToWindDynamicsLookupTableCommand" );
    	
    	if (  sequence != null && sequence.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "Sequence already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignSequenceToWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignSequenceFromWindDynamicsLookupTableCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignSequenceFromWindDynamicsLookupTableCommand" );

    	if (  sequence == null )
    		throw new ProcessingException( "Sequence already has nothing assigned." );  

    	apply(new UnAssignSequenceFromWindDynamicsLookupTableEvent(command.getWindDynamicsLookupTableId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindDynamicsLookupTableEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindDynamicsLookupTableEvent" );
    	this.windDynamicsLookupTableId = event.getWindDynamicsLookupTableId();
        this.lookupTableFunctionType = event.getLookupTableFunctionType();
    }
    
    @EventSourcingHandler
    void on(UpdateWindDynamicsLookupTableEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.input = event.getInput();
        this.lookupTableFunctionType = event.getLookupTableFunctionType();
        this.output = event.getOutput();
        this.sequence = event.getSequence();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignInputToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignInputToWindDynamicsLookupTableEvent" );
    	this.input = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignInputFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignInputFromWindDynamicsLookupTableEvent" );
		this.input = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOutputToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOutputToWindDynamicsLookupTableEvent" );
    	this.output = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOutputFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOutputFromWindDynamicsLookupTableEvent" );
		this.output = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignSequenceToWindDynamicsLookupTableEvent event ) {	
    	LOGGER.info( "Event sourcing AssignSequenceToWindDynamicsLookupTableEvent" );
    	this.sequence = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignSequenceFromWindDynamicsLookupTableEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignSequenceFromWindDynamicsLookupTableEvent" );
		this.sequence = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windDynamicsLookupTableId;
    
    private WindLookupTableFunctionKind lookupTableFunctionType;
    private Simple_Float input = null;
    private Simple_Float output = null;
    private IntegerProxy sequence = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindDynamicsLookupTableAggregate.class.getName());
}
