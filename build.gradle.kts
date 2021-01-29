plugins {
    idea
    java
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

repositories{
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


dependencyManagement {
    applyMavenExclusions(false)
}

tasks.withType<Test> {
    useJUnitPlatform()
}