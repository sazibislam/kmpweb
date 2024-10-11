import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.serializer.plugin)
}

group = "com.blog.example"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("example", includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.kotlinx.serializer)
        }

        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
            implementation(libs.kotlinx.serializer)

            // implementation(project(":worker"))
        }
        jvmMain.dependencies {
            implementation(libs.kobweb.api) // Provided by Kobweb backend at runtime
            compileOnly(libs.kmongo.database) //db
            // implementation(libs.mongodb.kotlin.driver)
            implementation(libs.kotlinx.serializer)
        }
    }
}
