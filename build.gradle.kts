import java.util.*

group = "com.segittur"
version = "0.0.1-SNAPSHOT"

var buildDir = layout.buildDirectory.get().asFile.absolutePath
var projectDir = layout.buildDirectory.get().asFile.absolutePath

var gitPropertiesFile = "${buildDir}/resources/main/git.properties"
var checkStyleConfig = "${projectDir}/config/checkstyle/checkstyle.xml"
var docsDir = "${projectDir}/docs"

plugins {
    checkstyle
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.4")
    testImplementation("org.testcontainers:testcontainers:1.19.1")
    testImplementation("org.testcontainers:junit-jupiter:1.19.1")
    testImplementation("org.testcontainers:mongodb:1.19.1")
}

gitProperties {
    failOnNoGitDirectory = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

checkstyle {
    isShowViolations = true
}

tasks.named("checkstyleMain") {
    enabled = true
}

tasks.named("checkstyleTest") {
    enabled = true
}

// Sets the version based git info
val setVersion by tasks.register("setVersion") {
    // get git properties
    val props = Properties().apply {
        load(file(gitPropertiesFile).inputStream())
    }

    val gitCommitIdAbbrev = props.getProperty("git.commit.id.abbrev")
    val gitClosestTagName = props.getProperty("git.closest.tag.name")
    val gitClosestTagCommitCount = props.getProperty("git.closest.tag.commit.count")
    version = "$gitClosestTagName $gitClosestTagCommitCount $gitCommitIdAbbrev"
    println("Version: $version")
}

tasks.named("setVersion").configure {
    dependsOn("processResources")
    dependsOn("generateGitProperties")
}

tasks.named("prepareKotlinBuildScriptModel").configure{
    dependsOn("processResources")
    dependsOn("generateGitProperties")
}

tasks.javadoc {
    file(docsDir)
}