
package  com.khwela.khwelacore.commands;

import com.khwela.khwelacore.enums.TripStatus
import java.time.LocalDate
import java.util.*


class OfferTripCommand(var tripId:String,
                       val offeredBy:String,
                       val pickup:String,
                       val destination:String,
                       val numberOfSeats:Int,
                       val tripDate: LocalDate);


class CancelTripCommand(val tripId:String,
                        val userId:String,
                        val cancellationReason:String);

class UpdateTripStatusCommand(val tripId: String,
                              val userId: String,
                              val newStatus: TripStatus);








