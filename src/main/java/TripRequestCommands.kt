package com.khwela.khwelacore.commands;

import com.khwela.khwelacore.enums.LuggageSize
import java.time.LocalDate


class RequestTripCommand(var tripRequestId: String,
                         val userId: String,
                         val pickup: String,
                         val destination: String,
                         val tripDate: LocalDate,
                         val numberOfPeople: Int,
                         val luggageSize: LuggageSize)



class AssignTripRequestCommand()

class DirectRequestCommand(var tripId: String,var tripRequestId: String,
                           val userId: String,
                           val pickup: String,
                           val destination: String,
                           val tripDate: LocalDate,
                           val numberOfPeople: Int,
                           val luggageSize: LuggageSize)



