package  com.khwela.khwelacore.trips;

import com.khwela.khwelacore.enums.TripStatus
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import java.io.Serializable

import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

class TripOfferedCommand(val tripId:String, val offeredBy:String,val from:String, val destination: String);
class TripOfferedEvent(val tripId:String, val offeredBy:String,val from:String, val destination: String);

class TripCancelledCommand(val tripId:String, val cancelledBy:String);
class TripCancelledEvent(val tripId:String, val cancelledBy:String);
class TripStatusChangedEvent(val  tripId:String, val previousStatus:TripStatus,val newStatus:TripStatus );


class TripRequestedCommand(val userId:String, val from:String,val destination: String, val tripDate: Date);
class TripRequestedEvent(val userId:String, val from:String,val destination: String, val tripDate: Date);

class TripUpdatedCommand(val tripId:String, val userId:String, val from:String, val destination: String);
class TripUpdatedEvent(val tripId:String, val offeredBy:String,val from:String, val destination: String);

@Aggregate
class Trip( @AggregateIdentifier var tripId: String) : Serializable{

    @CommandHandler
    constructor(_command: TripOfferedCommand) : this() {
         apply(TripOfferedEvent(_command.tripId,_command.offeredBy,_command.from,_command.destination));
    }

    @CommandHandler
    constructor(_command: TripCancelledCommand) : this() {
        apply(TripCancelledEvent(_command.tripId,_command.cancelledBy));
    }

    @CommandHandler
    constructor(_command: TripRequestedCommand) : this() {
        apply(TripRequestedEvent(_command.userId,_command.from,_command.destination,_command.tripDate));
    }

    @CommandHandler
    constructor(_command: TripUpdatedCommand) : this() {
        apply(TripUpdatedEvent(_command.tripId,_command.userId, _command.from,_command.destination));
    }

    @EventSourcingHandler
    fun on(event: TripOfferedEvent){
         println("Event raised ${event.tripId}")
    }

    @EventSourcingHandler
    fun on(event: TripCancelledEvent){

        //Change trip status
        apply(TripStatusChangedEvent(event.tripId, TripStatus.AVAILABLE, TripStatus.CANCELLED))
        println("Event raised ${event.tripId}")
    }

    constructor() : this("");

}
