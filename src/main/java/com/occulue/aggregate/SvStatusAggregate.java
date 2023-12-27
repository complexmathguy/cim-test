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
 * Aggregate handler for SvStatus as outlined for the CQRS pattern, all write responsibilities 
 * related to SvStatus are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class SvStatusAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public SvStatusAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public SvStatusAggregate(CreateSvStatusCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateSvStatusCommand" );
    	CreateSvStatusEvent event = new CreateSvStatusEvent(command.getSvStatusId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateSvStatusCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateSvStatusCommand" );
    	UpdateSvStatusEvent event = new UpdateSvStatusEvent(command.getSvStatusId(), command.InService());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteSvStatusCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteSvStatusCommand" );
        apply(new DeleteSvStatusEvent(command.getSvStatusId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignInServiceToSvStatusCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignInServiceToSvStatusCommand" );
    	
    	if (  inService != null && inService.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "InService already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignInServiceToSvStatusEvent(command.getSvStatusId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignInServiceFromSvStatusCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignInServiceFromSvStatusCommand" );

    	if (  inService == null )
    		throw new ProcessingException( "InService already has nothing assigned." );  

    	apply(new UnAssignInServiceFromSvStatusEvent(command.getSvStatusId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateSvStatusEvent event) {	
    	LOGGER.info( "Event sourcing CreateSvStatusEvent" );
    	this.svStatusId = event.getSvStatusId();
    }
    
    @EventSourcingHandler
    void on(UpdateSvStatusEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.inService = event.getInService();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignInServiceToSvStatusEvent event ) {	
    	LOGGER.info( "Event sourcing AssignInServiceToSvStatusEvent" );
    	this.inService = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignInServiceFromSvStatusEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignInServiceFromSvStatusEvent" );
		this.inService = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID svStatusId;
    
    private BooleanProxy inService = null;

    private static final Logger LOGGER 	= Logger.getLogger(SvStatusAggregate.class.getName());
}
