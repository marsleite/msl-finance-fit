package com.msl.financefit.entity

import java.time.LocalDateTime

data class Notification(
  val id: Long,
  val type: NotificationType,
  val notifyDate: LocalDateTime,
  val message: String
)