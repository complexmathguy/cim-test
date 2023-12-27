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
 * Aggregate handler for WindGenTurbineType3IEC as outlined for the CQRS pattern, all write responsibilities 
 * related to WindGenTurbineType3IEC are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class WindGenTurbineType3IECAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public WindGenTurbineType3IECAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public WindGenTurbineType3IECAggregate(CreateWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateWindGenTurbineType3IECCommand" );
    	CreateWindGenTurbineType3IECEvent event = new CreateWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateWindGenTurbineType3IECCommand" );
    	UpdateWindGenTurbineType3IECEvent event = new UpdateWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.Dipmax(), command.Diqmax());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteWindGenTurbineType3IECCommand" );
        apply(new DeleteWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDipmaxToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDipmaxToWindGenTurbineType3IECCommand" );
    	
    	if (  dipmax != null && dipmax.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Dipmax already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignDipmaxToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDipmaxFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDipmaxFromWindGenTurbineType3IECCommand" );

    	if (  dipmax == null )
    		throw new ProcessingException( "Dipmax already has nothing assigned." );  

    	apply(new UnAssignDipmaxFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }
    @CommandHandler
    public void handle(AssignDiqmaxToWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDiqmaxToWindGenTurbineType3IECCommand" );
    	
    	if (  diqmax != null && diqmax.getPUId() == command.getAssignment().getPUId() )
    		throw new ProcessingException( "Diqmax already assigned with id " + command.getAssignment().getPUId() );  
    		
        apply(new AssignDiqmaxToWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDiqmaxFromWindGenTurbineType3IECCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDiqmaxFromWindGenTurbineType3IECCommand" );

    	if (  diqmax == null )
    		throw new ProcessingException( "Diqmax already has nothing assigned." );  

    	apply(new UnAssignDiqmaxFromWindGenTurbineType3IECEvent(command.getWindGenTurbineType3IECId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateWindGenTurbineType3IECEvent event) {	
    	LOGGER.info( "Event sourcing CreateWindGenTurbineType3IECEvent" );
    	this.windGenTurbineType3IECId = event.getWindGenTurbineType3IECId();
    }
    
    @EventSourcingHandler
    void on(UpdateWindGenTurbineType3IECEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.dipmax = event.getDipmax();
        this.diqmax = event.getDiqmax();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDipmaxToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDipmaxToWindGenTurbineType3IECEvent" );
    	this.dipmax = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDipmaxFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDipmaxFromWindGenTurbineType3IECEvent" );
		this.dipmax = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignDiqmaxToWindGenTurbineType3IECEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDiqmaxToWindGenTurbineType3IECEvent" );
    	this.diqmax = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDiqmaxFromWindGenTurbineType3IECEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDiqmaxFromWindGenTurbineType3IECEvent" );
		this.diqmax = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID windGenTurbineType3IECId;
    
    private PU dipmax = null;
    private PU diqmax = null;

    private static final Logger LOGGER 	= Logger.getLogger(WindGenTurbineType3IECAggregate.class.getName());
}
