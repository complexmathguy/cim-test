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
 * Aggregate handler for RotatingMachine as outlined for the CQRS pattern, all write responsibilities 
 * related to RotatingMachine are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class RotatingMachineAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public RotatingMachineAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public RotatingMachineAggregate(CreateRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateRotatingMachineCommand" );
    	CreateRotatingMachineEvent event = new CreateRotatingMachineEvent(command.getRotatingMachineId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateRotatingMachineCommand" );
    	UpdateRotatingMachineEvent event = new UpdateRotatingMachineEvent(command.getRotatingMachineId(), command.RatedPowerFactor(), command.RatedS(), command.RatedU());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteRotatingMachineCommand" );
        apply(new DeleteRotatingMachineEvent(command.getRotatingMachineId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignRatedPowerFactorToRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRatedPowerFactorToRotatingMachineCommand" );
    	
    	if (  ratedPowerFactor != null && ratedPowerFactor.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "RatedPowerFactor already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignRatedPowerFactorToRotatingMachineEvent(command.getRotatingMachineId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRatedPowerFactorFromRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRatedPowerFactorFromRotatingMachineCommand" );

    	if (  ratedPowerFactor == null )
    		throw new ProcessingException( "RatedPowerFactor already has nothing assigned." );  

    	apply(new UnAssignRatedPowerFactorFromRotatingMachineEvent(command.getRotatingMachineId()));
    }
    @CommandHandler
    public void handle(AssignRatedSToRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRatedSToRotatingMachineCommand" );
    	
    	if (  ratedS != null && ratedS.getApparentPowerId() == command.getAssignment().getApparentPowerId() )
    		throw new ProcessingException( "RatedS already assigned with id " + command.getAssignment().getApparentPowerId() );  
    		
        apply(new AssignRatedSToRotatingMachineEvent(command.getRotatingMachineId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRatedSFromRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRatedSFromRotatingMachineCommand" );

    	if (  ratedS == null )
    		throw new ProcessingException( "RatedS already has nothing assigned." );  

    	apply(new UnAssignRatedSFromRotatingMachineEvent(command.getRotatingMachineId()));
    }
    @CommandHandler
    public void handle(AssignRatedUToRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRatedUToRotatingMachineCommand" );
    	
    	if (  ratedU != null && ratedU.getVoltageId() == command.getAssignment().getVoltageId() )
    		throw new ProcessingException( "RatedU already assigned with id " + command.getAssignment().getVoltageId() );  
    		
        apply(new AssignRatedUToRotatingMachineEvent(command.getRotatingMachineId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRatedUFromRotatingMachineCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRatedUFromRotatingMachineCommand" );

    	if (  ratedU == null )
    		throw new ProcessingException( "RatedU already has nothing assigned." );  

    	apply(new UnAssignRatedUFromRotatingMachineEvent(command.getRotatingMachineId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateRotatingMachineEvent event) {	
    	LOGGER.info( "Event sourcing CreateRotatingMachineEvent" );
    	this.rotatingMachineId = event.getRotatingMachineId();
    }
    
    @EventSourcingHandler
    void on(UpdateRotatingMachineEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.ratedPowerFactor = event.getRatedPowerFactor();
        this.ratedS = event.getRatedS();
        this.ratedU = event.getRatedU();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignRatedPowerFactorToRotatingMachineEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRatedPowerFactorToRotatingMachineEvent" );
    	this.ratedPowerFactor = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRatedPowerFactorFromRotatingMachineEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRatedPowerFactorFromRotatingMachineEvent" );
		this.ratedPowerFactor = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRatedSToRotatingMachineEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRatedSToRotatingMachineEvent" );
    	this.ratedS = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRatedSFromRotatingMachineEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRatedSFromRotatingMachineEvent" );
		this.ratedS = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRatedUToRotatingMachineEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRatedUToRotatingMachineEvent" );
    	this.ratedU = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRatedUFromRotatingMachineEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRatedUFromRotatingMachineEvent" );
		this.ratedU = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID rotatingMachineId;
    
    private Simple_Float ratedPowerFactor = null;
    private ApparentPower ratedS = null;
    private Voltage ratedU = null;

    private static final Logger LOGGER 	= Logger.getLogger(RotatingMachineAggregate.class.getName());
}
