package com.msl.financefit.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.SpringApplication
import org.springframework.mock.env.MockEnvironment

class ScopeProfileActivationEnvironmentPostProcessorTest {

  private val processor = ScopeProfileActivationEnvironmentPostProcessor()

  @Test
  fun `postProcessEnvironment should not add active profile when there is no SCOPE`() {
    val environment = MockEnvironment()
    val application = SpringApplication()
    processor.postProcessEnvironment(environment, application)
    Assertions.assertTrue(environment.activeProfiles.isEmpty())
  }

  @Test
  fun `postProcessEnvironment should add active profile with SCOPE value when there is no - separator`() {
    val environment = MockEnvironment().withProperty("SCOPE", "api")
    val application = SpringApplication()
    processor.postProcessEnvironment(environment, application)
    Assertions.assertEquals("api", environment.activeProfiles[0])
  }

  @Test
  fun `postProcessEnvironment should add active profile with SCOPE suffix separated by -`() {
    val environment = MockEnvironment().withProperty("SCOPE", "my-api-test")
    val application = SpringApplication()
    processor.postProcessEnvironment(environment, application)
    Assertions.assertEquals("test", environment.activeProfiles[0])
  }
}