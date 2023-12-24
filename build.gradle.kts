import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.21"
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

buildscript {
	dependencies {
		this.classpath("io.github.gradle-nexus:publish-plugin:1.1.0")
	}
}


apply(from = file("${rootDir}/scripts/publish-maven.gradle"))
apply(from = "publish.gradle")

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
	testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

