package com.msl.financefit

import com.msl.financefit.application.config.setupEnv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinanceFitApplication

fun main(args: Array<String>) {
  setupEnv()
  runApplication<FinanceFitApplication>(*args)
}
