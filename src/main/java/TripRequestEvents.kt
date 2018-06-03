package com.khwela.khwelacore.events

import com.khwela.khwelacore.enums.LuggageSize
import com.khwela.khwelacore.enums.TripRequestStatus
import java.time.LocalDate


class TripRequestedEvent(val tripRequestId: String,
                         val userId: String,
                         val pickup: String,
                         val destination: String,
                         val tripDate: LocalDate,
                         val numberOfPeople: Int,
                         val luggageSize: LuggageSize);

class TripRequestAssignedEvent(val requestId :String, tripId:String);
class RequestStatusUpatedEvent(val requestId: String, status: TripRequestStatus)




