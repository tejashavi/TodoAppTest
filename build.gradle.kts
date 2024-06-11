// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.2.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath ("io.realm:realm-gradle-plugin:10.18.0")
    }
}