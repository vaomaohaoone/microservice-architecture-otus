plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

version = "stable"
var camunda_version = "7.15.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:$camunda_version")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:$camunda_version")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-rest:$camunda_version")
}

tasks {

    processResources {
        filesMatching("application.yml") {
            expand(project.properties)
        }
    }

    bootJar {
        launchScript()
    }

}