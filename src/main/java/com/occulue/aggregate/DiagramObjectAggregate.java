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
 * Aggregate handler for DiagramObject as outlined for the CQRS pattern, all write responsibilities 
 * related to DiagramObject are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class DiagramObjectAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public DiagramObjectAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public DiagramObjectAggregate(CreateDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateDiagramObjectCommand" );
    	CreateDiagramObjectEvent event = new CreateDiagramObjectEvent(command.getDiagramObjectId());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateDiagramObjectCommand" );
    	UpdateDiagramObjectEvent event = new UpdateDiagramObjectEvent(command.getDiagramObjectId(), command.DrawingOrder(), command.PolygonFlag(), command.OffsetX(), command.OffsetY(), command.Rotation());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteDiagramObjectCommand" );
        apply(new DeleteDiagramObjectEvent(command.getDiagramObjectId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignDrawingOrderToDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignDrawingOrderToDiagramObjectCommand" );
    	
    	if (  drawingOrder != null && drawingOrder.getIntegerProxyId() == command.getAssignment().getIntegerProxyId() )
    		throw new ProcessingException( "DrawingOrder already assigned with id " + command.getAssignment().getIntegerProxyId() );  
    		
        apply(new AssignDrawingOrderToDiagramObjectEvent(command.getDiagramObjectId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignDrawingOrderFromDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignDrawingOrderFromDiagramObjectCommand" );

    	if (  drawingOrder == null )
    		throw new ProcessingException( "DrawingOrder already has nothing assigned." );  

    	apply(new UnAssignDrawingOrderFromDiagramObjectEvent(command.getDiagramObjectId()));
    }
    @CommandHandler
    public void handle(AssignPolygonFlagToDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPolygonFlagToDiagramObjectCommand" );
    	
    	if (  polygonFlag != null && polygonFlag.getBooleanProxyId() == command.getAssignment().getBooleanProxyId() )
    		throw new ProcessingException( "PolygonFlag already assigned with id " + command.getAssignment().getBooleanProxyId() );  
    		
        apply(new AssignPolygonFlagToDiagramObjectEvent(command.getDiagramObjectId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignPolygonFlagFromDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignPolygonFlagFromDiagramObjectCommand" );

    	if (  polygonFlag == null )
    		throw new ProcessingException( "PolygonFlag already has nothing assigned." );  

    	apply(new UnAssignPolygonFlagFromDiagramObjectEvent(command.getDiagramObjectId()));
    }
    @CommandHandler
    public void handle(AssignOffsetXToDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOffsetXToDiagramObjectCommand" );
    	
    	if (  offsetX != null && offsetX.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "OffsetX already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignOffsetXToDiagramObjectEvent(command.getDiagramObjectId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOffsetXFromDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOffsetXFromDiagramObjectCommand" );

    	if (  offsetX == null )
    		throw new ProcessingException( "OffsetX already has nothing assigned." );  

    	apply(new UnAssignOffsetXFromDiagramObjectEvent(command.getDiagramObjectId()));
    }
    @CommandHandler
    public void handle(AssignOffsetYToDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOffsetYToDiagramObjectCommand" );
    	
    	if (  offsetY != null && offsetY.getSimple_FloatId() == command.getAssignment().getSimple_FloatId() )
    		throw new ProcessingException( "OffsetY already assigned with id " + command.getAssignment().getSimple_FloatId() );  
    		
        apply(new AssignOffsetYToDiagramObjectEvent(command.getDiagramObjectId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignOffsetYFromDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignOffsetYFromDiagramObjectCommand" );

    	if (  offsetY == null )
    		throw new ProcessingException( "OffsetY already has nothing assigned." );  

    	apply(new UnAssignOffsetYFromDiagramObjectEvent(command.getDiagramObjectId()));
    }
    @CommandHandler
    public void handle(AssignRotationToDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignRotationToDiagramObjectCommand" );
    	
    	if (  rotation != null && rotation.getAngleDegreesId() == command.getAssignment().getAngleDegreesId() )
    		throw new ProcessingException( "Rotation already assigned with id " + command.getAssignment().getAngleDegreesId() );  
    		
        apply(new AssignRotationToDiagramObjectEvent(command.getDiagramObjectId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignRotationFromDiagramObjectCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignRotationFromDiagramObjectCommand" );

    	if (  rotation == null )
    		throw new ProcessingException( "Rotation already has nothing assigned." );  

    	apply(new UnAssignRotationFromDiagramObjectEvent(command.getDiagramObjectId()));
    }

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateDiagramObjectEvent event) {	
    	LOGGER.info( "Event sourcing CreateDiagramObjectEvent" );
    	this.diagramObjectId = event.getDiagramObjectId();
    }
    
    @EventSourcingHandler
    void on(UpdateDiagramObjectEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.drawingOrder = event.getDrawingOrder();
        this.polygonFlag = event.getPolygonFlag();
        this.offsetX = event.getOffsetX();
        this.offsetY = event.getOffsetY();
        this.rotation = event.getRotation();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignDrawingOrderToDiagramObjectEvent event ) {	
    	LOGGER.info( "Event sourcing AssignDrawingOrderToDiagramObjectEvent" );
    	this.drawingOrder = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignDrawingOrderFromDiagramObjectEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignDrawingOrderFromDiagramObjectEvent" );
		this.drawingOrder = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignPolygonFlagToDiagramObjectEvent event ) {	
    	LOGGER.info( "Event sourcing AssignPolygonFlagToDiagramObjectEvent" );
    	this.polygonFlag = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignPolygonFlagFromDiagramObjectEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignPolygonFlagFromDiagramObjectEvent" );
		this.polygonFlag = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOffsetXToDiagramObjectEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOffsetXToDiagramObjectEvent" );
    	this.offsetX = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOffsetXFromDiagramObjectEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOffsetXFromDiagramObjectEvent" );
		this.offsetX = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignOffsetYToDiagramObjectEvent event ) {	
    	LOGGER.info( "Event sourcing AssignOffsetYToDiagramObjectEvent" );
    	this.offsetY = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignOffsetYFromDiagramObjectEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignOffsetYFromDiagramObjectEvent" );
		this.offsetY = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignRotationToDiagramObjectEvent event ) {	
    	LOGGER.info( "Event sourcing AssignRotationToDiagramObjectEvent" );
    	this.rotation = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignRotationFromDiagramObjectEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignRotationFromDiagramObjectEvent" );
		this.rotation = null;
	}


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID diagramObjectId;
    
    private IntegerProxy drawingOrder = null;
    private BooleanProxy polygonFlag = null;
    private Simple_Float offsetX = null;
    private Simple_Float offsetY = null;
    private AngleDegrees rotation = null;

    private static final Logger LOGGER 	= Logger.getLogger(DiagramObjectAggregate.class.getName());
}
