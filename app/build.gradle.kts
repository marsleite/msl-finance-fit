plugins {
  kotlin("jvm") version "1.9.0"
  kotlin("plugin.spring") version "1.9.0"
  id("java-test-fixtures")
}

apply(plugin = "project-report")

dependencies {
  implementation(project(":domain"))
}

sourceSets {
  create("integrationTest") {
    compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
    runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
    resources.srcDir("src/integrationTest/resources")
  }
}

task<Test>("integrationTest") {
  environment("APPLICATION", "si-fiscal-identities")
  description = "Runs the integration tests"
  group = "verification"
  testClassesDirs = sourceSets["integrationTest"].output.classesDirs
  classpath = sourceSets["integrationTest"].runtimeClasspath
  mustRunAfter(tasks["test"])
  useJUnitPlatform()
}

tasks.test {
  environment("APPLICATION", "msl-finance-fit")
}

tasks {
  bootJar {
    destinationDirectory.set(file("${rootProject.buildDir}/libs"))
  }
  bootRun {
    environment.putIfAbsent("APPLICATION", "msl-finance-fit")
  }
}