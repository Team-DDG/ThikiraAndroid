package dependencies

object Dep {
    object GradlePlugin {
        const val gradle = "com.android.tools.build:gradle:3.5.3"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val googleService = "com.google.gms:google-services:4.3.3"
    }

    object Kotlin {
        const val version = "1.3.61"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

        object Room {
            private const val version = "2.2.3"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }
    }

    object Firebase {
        const val storage = "com.google.firebase:firebase-storage:19.1.1"
    }

    object Retrofit {
        private const val version = "2.7.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        const val logging = "com.squareup.okhttp3:logging-interceptor:4.3.1"
    }
}
