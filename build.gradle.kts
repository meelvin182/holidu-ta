plugins {
    idea
    java
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.16")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok:1.18.16")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.16")
}


dependencyManagement {
    applyMavenExclusions(false)
}

tasks.withType<Test> {
    useJUnitPlatform()
}