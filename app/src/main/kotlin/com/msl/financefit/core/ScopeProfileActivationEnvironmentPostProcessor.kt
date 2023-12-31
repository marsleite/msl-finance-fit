package com.msl.financefit.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.get

class ScopeProfileActivationEnvironmentPostProcessor: EnvironmentPostProcessor {
  override fun postProcessEnvironment(environment: ConfigurableEnvironment?, application: SpringApplication?) {
    environment?.get("SCOPE")?.also {
      val profile = it.split("-").last()
      environment.addActiveProfile(profile)
    }
  }
}