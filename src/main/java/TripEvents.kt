


package  com.khwela.khwelacore.events;

import com.khwela.khwelacore.enums.TripStatus
import java.time.LocalDate
import java.util.*

class TripOfferedEvent(val tripId:String,
                       val offeredBy:String,
                       val pickup:String,
                       val destination:String,
                       val numberOfSeats:Int,
                       val tripDate: LocalDate);


class TripCancelledEvent(val tripId:String,
                        val userId:String,
                        val cancellationReason:String);

class TripStatusUpatedEvent(val tripId: String,
                              val userId: String,
                              val newStatus: TripStatus);