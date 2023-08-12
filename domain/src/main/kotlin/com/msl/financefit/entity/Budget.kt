package com.msl.financefit.entity

import java.math.BigDecimal

data class Budget(
  val id: Long,
  val category: ExpenseCategory,
  val valueLimit: BigDecimal,
  val userId: Long
)