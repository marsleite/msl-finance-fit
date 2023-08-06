package com.msl.financefit

import com.msl.financefit.application.config.setupEnv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableR2dbcAuditing
class FinanceFitApplication

fun main(args: Array<String>) {
  setupEnv()
  runApplication<FinanceFitApplication>(*args)
}
