package com.khwela.khwelacore.events

import com.khwela.khwelacore.enums.LuggageSize
import java.time.LocalDate


public class TripRequestedEvent(val tripRequestId : String,
                                val userId: String,
                                val pickup:String,
                                val destination:String,
                                val tripDate: LocalDate,
                                val numberOfPeople:Int,
                                val luggageSize: LuggageSize);

