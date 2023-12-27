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
 * Aggregate handler for GenICompensationForGenJ as outlined for the CQRS pattern, all write responsibilities 
 * related to GenICompensationForGenJ are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class GenICompensationForGenJAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public GenICompensationForGenJAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public GenICompensationForGenJAggregate(CreateGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateGenICompensationForGenJCommand" );
    	CreateGenICompensationForGenJEvent event = new CreateGenICompensationForGenJEvent(command.getGenICompensationForGenJId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateGenICompensationForGenJCommand" );
    	UpdateGenICompensationForGenJEvent event = new UpdateGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.Rcij(), command.Xcij());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteGenICompensationForGenJCommand" );
        apply(new DeleteGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRcijToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRcijToGenICompensationForGenJCommand" );
    	
    	if (  rcij != null && rcij.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Rcij already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignRcijToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRcijFromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRcijFromGenICompensationForGenJCommand" );

    	if (  rcij == null )
    		throw new ProcessingException( "Rcij already has nothing assigned." );  

    	apply(new UnAssignRcijFromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }
    @CommandHandler
    public void handle(AssignXcijToGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignXcijToGenICompensationForGenJCommand" );
    	
    	if (  xcij != null && xcij.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Xcij already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignXcijToGenICompensationForGenJEvent(command.getGenICompensationForGenJId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignXcijFromGenICompensationForGenJCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignXcijFromGenICompensationForGenJCommand" );

    	if (  xcij == null )
    		throw new ProcessingException( "Xcij already has nothing assigned." );  

    	apply(new UnAssignXcijFromGenICompensationForGenJEvent(command.getGenICompensationForGenJId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateGenICompensationForGenJEvent event) {	
    	LOGGER.info( "Event sourcing CreateGenICompensationForGenJEvent" );
    	this.genICompensationForGenJId = event.getGenICompensationForGenJId();
    }
    
    @EventSourcingHandler
    void on(UpdateGenICompensationForGenJEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.rcij = event.getRcij();
        this.xcij = event.getXcij();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRcijToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRcijToGenICompensationForGenJEvent" );
    	this.rcij = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRcijFromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRcijFromGenICompensationForGenJEvent" );
		this.rcij = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignXcijToGenICompensationForGenJEvent event ) {	
    	LOGGER.info( "Event sourcing AssignXcijToGenICompensationForGenJEvent" );
    	this.xcij = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignXcijFromGenICompensationForGenJEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignXcijFromGenICompensationForGenJEvent" );
		this.xcij = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID genICompensationForGenJId;
    
    private PU rcij = null;
    private PU xcij = null;

    private static final Logger LOGGER 	= Logger.getLogger(GenICompensationForGenJAggregate.class.getName());
}
