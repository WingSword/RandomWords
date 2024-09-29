plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.walks"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:3.0.0-rc-1")
    implementation("io.ktor:ktor-server-netty:3.0.0-rc-1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}