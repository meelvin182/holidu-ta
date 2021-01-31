plugins {
    idea
    java
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.palantir.docker") version "0.22.1"
    id("com.palantir.docker-run") version "0.22.1"
}
version = "1.0.0"
docker {
    name = "meelvin182/tree-aggregator:".plus(version)
    uri("meelvin182/tree-aggregator:".plus(version))
    tag("name", "tree-aggregator")
    buildArgs(hashMapOf("name" to "tree-aggregator"))
    copySpec.from("build").into("build")
    pull(true)
    setDockerfile(file("Dockerfile"))
}

dockerRun {
    name = "tree-aggregator"
    image = "meelvin182/tree-aggregator:".plus(version)
    ports("8080:8080")
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

    testImplementation("org.springframework.boot:spring-boot-starter-test"){
      //  exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testCompileOnly("org.projectlombok:lombok:1.18.16")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.16")
}


dependencyManagement {
    applyMavenExclusions(false)
}

tasks.withType<Test> {
    useJUnitPlatform()
}