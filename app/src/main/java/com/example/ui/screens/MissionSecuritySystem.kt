package com.example.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

object MissionSecuritySystem {
    var lockEndTimeMillis by mutableStateOf(0L)

    fun isLocked(): Boolean {
        return System.currentTimeMillis() < lockEndTimeMillis
    }

    fun lock() {
        lockEndTimeMillis = System.currentTimeMillis() + 2 * 60 * 1000 // 2 minutes
    }

    fun unlock() {
        lockEndTimeMillis = 0L
    }
}
