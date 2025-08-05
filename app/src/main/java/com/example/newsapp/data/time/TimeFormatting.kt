package com.example.newsapp.data.time

import java.time.Duration
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


fun String.toRelativeTime(): String{
    return try {
        val formatter= DateTimeFormatter.ISO_DATE_TIME
        val dateTime= OffsetDateTime.parse(this, formatter)
        val publishedInstance=dateTime.toInstant()

        val now= Instant.now()
        val duration = Duration.between(publishedInstance,now)
        val seconds = duration.seconds

        when{
            seconds<60 -> "Just now"
            seconds<60*60 ->{
                val minutes=seconds/60
                if (minutes==1L) "1 minute ago" else "${minutes} minutes ago"
            }
            seconds<60*60*24 ->{
                val hours=seconds/3600
                if (hours==1L) "1 hour ago" else "${hours} hours ago"}
            seconds < 60 * 60 * 24 * 7 -> {
                val days=seconds/3600*24
                if (days==1L) "1 day ago" else "${days} days ago"
            }
            else->{
                val date = dateTime.toLocalDate()
                date.toString()
            }
        }

    }catch (e: Exception){
        "${e.message}"
    }
}