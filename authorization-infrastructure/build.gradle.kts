plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
}

dependencyManagement {
    imports {
        mavenBom(Dependencies.SPRING_CLOUD)
    }
}

dependencies {
    implementation(Dependencies.ACTUATOR)
    implementation(Dependencies.WEBFLUX)
    implementation(Dependencies.R2DBC)
    implementation(Dependencies.REACTIVE_MYSQL)
    implementation(Dependencies.KOTLIN_JACKSON)
    implementation(Dependencies.COROUTINE_REACTOR)
    implementation(Dependencies.COROUTINE_JDK)
    implementation(Dependencies.KOTLINX_COROUTINE)
    implementation(Dependencies.COROUTINE_REACTOR_EXTENSION)
    implementation(Dependencies.KOTLIN_STDLIB)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.SPRING_VALIDATION)
    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_APT)
    implementation(Dependencies.MICROMETER)
    kapt(Dependencies.CONFIGURATION_PROCESSOR)
    implementation(Dependencies.OPEN_API_UI)
    implementation(Dependencies.OPEN_API_CORE)
    implementation(Dependencies.CLOUD_CONFIG)
    testImplementation(Dependencies.SPRING_BOOT_TEST)
    testImplementation(Dependencies.COROUTINE_TEST)
    testImplementation(Dependencies.H2)

    implementation(project(":authorization-domain"))
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "error")
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
