package com.khwela.khwelacore.commons

import java.util.ArrayList

object QueryBuilder {
    fun getQueryParts(filters: Map<String, Any>): ArrayList<String> {
        var result = ArrayList<String>()
        filters.keys.forEach { key ->
            result.add("$key = ${filters[key]}")
        }
        return result;
    }
}