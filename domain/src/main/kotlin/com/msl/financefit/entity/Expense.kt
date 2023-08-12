package com.msl.financefit.entity

import java.math.BigDecimal
import java.time.LocalDateTime

data class Expense(
  val id: Long,
  val category: ExpenseCategory,
  val value: BigDecimal,
  val expenseData: LocalDateTime,
  val userId: Long
)