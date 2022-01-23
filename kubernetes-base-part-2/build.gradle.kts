plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("kapt")
}

version = "final"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
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