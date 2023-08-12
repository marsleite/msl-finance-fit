import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.1.2"
  id("io.spring.dependency-management") version "1.1.2"
  id("idea")
  kotlin("jvm") version "1.9.0"
  kotlin("plugin.spring") version "1.9.0"
  id("org.jetbrains.kotlinx.kover") version "0.7.1"
}

allprojects {
  apply(plugin = "kotlin")
  apply(plugin = "project-report")
  apply(plugin = "org.jetbrains.kotlinx.kover")
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web") {
      exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("commons-collections:commons-collections:3.2.2")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.mockk:mockk:1.13.3")
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "17"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
      events("PASSED", "SKIPPED", "FAILED")
    }
  }
}

dependencies{
  kover(project("domain"))
  kover(project("app"))
}

val excludeCoverage = listOf(
  "**/*\$logger\$*.class",
  "com.msl.financefit.FinanceFitApplication.kt",
  "com.msl.financefit.FinanceFitApplicationKt",
  "com.msl.financefit.application.config.*",
  "com.msl.financefit.entity.*"
)

koverReport {
  defaults{
    filters{
      excludes{
        classes(excludeCoverage)
      }
    }
    html{
      onCheck = true
      setReportDir(layout.buildDirectory.dir("reports/jacoco/test/html"))
    }
    xml{
      onCheck = true
      setReportFile(layout.buildDirectory.file("reports/jacoco/test/jacocoTestReport.xml"))
    }
  }
}

tasks.register("jacocoTestReport") {
  dependsOn("test", "koverHtmlReport", "koverXmlReport")
}

tasks {
  bootJar { enabled = false }
  bootRun { enabled = false }
}