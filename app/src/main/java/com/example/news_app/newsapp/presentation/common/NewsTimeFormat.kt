package com.example.news_app.newsapp.presentation.common

import android.annotation.SuppressLint
import java.time.Instant
import java.time.Duration
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun String.toLocalTimeString(pattern: String = "dd MMM yyyy, hh:mm a"): String {
    return try {
        val instant = Instant.parse(this)
        val formatter = DateTimeFormatter
            .ofPattern(pattern)
            .withZone(ZoneId.systemDefault())
        formatter.format(instant)
    } catch (e: Exception) {
        this // fallback to original string if parse fails
    }
}

@SuppressLint("NewApi")
fun String.timeAgo(): String {
    return try {
        val instant = Instant.parse(this)
        val duration = Duration.between(instant, Instant.now())

        when {
            duration.toMinutes() < 1 -> "Just now"
            duration.toMinutes() < 60 -> "${duration.toMinutes()} min ago"
            duration.toHours() < 24 -> "${duration.toHours()} hr ago"
            duration.toDays() < 7 -> {
            val days = duration.toDays()
            if (days == 1L) "1 day ago" else "$days days ago"
        }
            else -> this.toLocalTimeString("dd MMM yyyy") // older than a week
        }
    } catch (e: Exception) {
        this
    }
}