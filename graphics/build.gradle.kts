plugins {
    kotlin("jvm") version "1.3.72"
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":core"))

    implementation("no.tornado:tornadofx:1.7.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
