plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.cdk8s:cdk8s:2.69.24")
    implementation("org.cdk8s:cdk8s-plus-28:2.5.6")
    implementation("software.constructs:constructs:10.4.2")
    implementation("com.google.guava:guava:32.0.1-jre")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}