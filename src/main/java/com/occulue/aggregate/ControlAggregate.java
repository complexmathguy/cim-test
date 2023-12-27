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
 * Aggregate handler for Control as outlined for the CQRS pattern, all write responsibilities 
 * related to Control are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ControlAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ControlAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ControlAggregate(CreateControlCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateControlCommand" );
    	CreateControlEvent event = new CreateControlEvent(command.getControlId(), command.UnitMultiplier(), command.UnitSymbol());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateControlCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateControlCommand" );
    	UpdateControlEvent event = new UpdateControlEvent(command.getControlId(), command.ControlType(), command.OperationInProgress(), command.TimeStamp(), command.UnitMultiplier(), command.UnitSymbol());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteControlCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteControlCommand" );
        apply(new DeleteControlEvent(command.getControlId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignControlTypeToControlCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignControlTypeToControlCommand" );
    	
    	if (  controlType != null && controlType.getStringProxyId() == command.getAssignment().getStringProxyId() )
    		throw new ProcessingException( "ControlType already assigned with id " + command.getAssignment().getStringProxyId() );  
    		
        apply(new AssignControlTypeToControlEvent(command.getControlId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignControlTypeFromControlCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignControlTypeFromControlCommand" );

    	if (  controlType == null )
    		throw new ProcessingException( "ControlType already has nothing assigned." );  

    	apply(new UnAssignControlTypeFromControlEvent(command.getControlId()));
    }
    @CommandHandler
    public void handle(AssignOperationInProgressToControlCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOperationInProgressToControlCommand" );
    	
    	if (  operationInProgress != null && operationInProgress.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "OperationInProgress already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignOperationInProgressToControlEvent(command.getControlId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOperationInProgressFromControlCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOperationInProgressFromControlCommand" );

    	if (  operationInProgress == null )
    		throw new ProcessingException( "OperationInProgress already has nothing assigned." );  

    	apply(new UnAssignOperationInProgressFromControlEvent(command.getControlId()));
    }
    @CommandHandler
    public void handle(AssignTimeStampToControlCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTimeStampToControlCommand" );
    	
    	if (  timeStamp != null && timeStamp.getDateTimeId() == command.getAssignment().getDateTimeId() )
    		throw new ProcessingException( "TimeStamp already assigned with id " + command.getAssignment().getDateTimeId() );  
    		
        apply(new AssignTimeStampToControlEvent(command.getControlId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignTimeStampFromControlCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignTimeStampFromControlCommand" );

    	if (  timeStamp == null )
    		throw new ProcessingException( "TimeStamp already has nothing assigned." );  

    	apply(new UnAssignTimeStampFromControlEvent(command.getControlId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateControlEvent event) {	
    	LOGGER.info( "Event sourcing CreateControlEvent" );
    	this.controlId = event.getControlId();
        this.unitMultiplier = event.getUnitMultiplier();
        this.unitSymbol = event.getUnitSymbol();
    }
    
    @EventSourcingHandler
    void on(UpdateControlEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.controlType = event.getControlType();
        this.operationInProgress = event.getOperationInProgress();
        this.timeStamp = event.getTimeStamp();
        this.unitMultiplier = event.getUnitMultiplier();
        this.unitSymbol = event.getUnitSymbol();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignControlTypeToControlEvent event ) {	
    	LOGGER.info( "Event sourcing AssignControlTypeToControlEvent" );
    	this.controlType = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignControlTypeFromControlEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignControlTypeFromControlEvent" );
		this.controlType = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOperationInProgressToControlEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOperationInProgressToControlEvent" );
    	this.operationInProgress = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOperationInProgressFromControlEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOperationInProgressFromControlEvent" );
		this.operationInProgress = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignTimeStampToControlEvent event ) {	
    	LOGGER.info( "Event sourcing AssignTimeStampToControlEvent" );
    	this.timeStamp = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignTimeStampFromControlEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignTimeStampFromControlEvent" );
		this.timeStamp = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID controlId;
    
    private UnitMultiplier unitMultiplier;
    private UnitSymbol unitSymbol;
    private StringProxy controlType = null;
    private BooleanProxy operationInProgress = null;
    private DateTime timeStamp = null;

    private static final Logger LOGGER 	= Logger.getLogger(ControlAggregate.class.getName());
}
