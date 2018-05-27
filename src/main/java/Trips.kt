package  com.khwela.khwelacore.trips;

import com.khwela.khwelacore.enums.TripRequestStatus
import com.khwela.khwelacore.enums.TripStatus
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.io.Serializable
import java.util.*

class TripOfferedCommand(val id:String,val tripId:String, val offeredBy:String,val from:String, val destination: String, val numberOfSeats: Int, val tripDate: Date);
class TripOfferedEvent(val id:String,val tripId:String, val offeredBy:String,val from:String, val destination: String, val numberOfSeats: Int, val tripDate: Date);

class TripCancelledCommand(val id:String,val tripId:String, val cancelledBy:String);
class TripCancelledEvent(val id:String,val tripId:String, val cancelledBy:String);
class TripStatusChangedEvent(val id:String,val  tripId:String, val previousStatus:TripStatus,val newStatus:TripStatus );

class TripRequestedCommand(val id:String,val userId:String, val from:String,val destination: String, val tripDate: Date);
class TripRequestedEvent(val id:String, val userId:String, val from:String,val destination: String, val tripDate: Date);

class TripUpdatedCommand(val id:String,val tripId:String, val userId:String, val from:String, val destination: String);
class TripUpdatedEvent(val id:String,val tripId:String, val offeredBy:String,val from:String, val destination: String);

class UserNotifiedOfCancellationEvent(val id:String,val tripId:String, val offeredBy:String);

class TripRequestStatusChangedEvent(val id:String,val requestId:Int, val newStaus:TripRequestStatus);

@Aggregate
 class Trip( @AggregateIdentifier  var identifier: String) : Serializable{

    @CommandHandler
    constructor(_command: TripOfferedCommand) : this() {
        apply(TripOfferedEvent(_command.id,_command.tripId,_command.offeredBy,_command.from,_command.destination, _command.numberOfSeats,_command.tripDate));
    }

    @CommandHandler
    constructor(_command: TripRequestedCommand) : this() {
         apply(TripRequestedEvent(UUID.randomUUID().toString(),_command.userId, _command.from, _command.destination, _command.tripDate));
    }

    @CommandHandler
    constructor(_command: TripCancelledCommand) : this() {
         apply(TripCancelledEvent(_command.id,_command.tripId,_command.cancelledBy));
    }

    @CommandHandler
    constructor(_command: TripUpdatedCommand) : this() {
         apply(TripUpdatedEvent(_command.id,_command.tripId,_command.userId,_command.from,_command.destination));
    }

    @EventSourcingHandler
    fun on(event: com.khwela.khwelacore.trips.TripOfferedEvent){
        this.identifier = event.id;
    }

    @EventSourcingHandler
    fun on(event: com.khwela.khwelacore.trips.TripRequestedEvent){
        this.identifier = event.id;
     }

    @EventSourcingHandler
    fun on(event: TripCancelledEvent){
        this.identifier = event.id;
        apply(TripStatusChangedEvent(event.id,event.tripId, TripStatus.AVAILABLE, TripStatus.CANCELLED)).andThenApply {
            UserNotifiedOfCancellationEvent(event.id,event.tripId,event.cancelledBy);
        }
     }

    constructor() : this(UUID.randomUUID().toString());

}
