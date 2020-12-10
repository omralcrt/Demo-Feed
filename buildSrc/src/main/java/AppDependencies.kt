import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"

    //lifecycle
    private val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //lifecycle
    private val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //retrofit
    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    private val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    private val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    //moshi
    private val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    private val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    //dagger
    private val daggerAnnotations = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.dagger}"
    private val daggerProcessor = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.dagger}"

    //hilt
    private val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    private val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    private val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    private val hiltLifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    private val hiltWork = "androidx.hilt:hilt-work:1.0.0-alpha02${Versions.hilt}"


    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(livedata)
        add(viewmodel)
        add(navigationFragment)
        add(navigationUi)
        add(retrofit)
        add(converterMoshi)
        add(loggingInterceptor)
        add(okHttp)
        add(moshi)
        add(moshiCodegen)
        add(daggerAnnotations)
        add(hilt)
        add(hiltLifecycle)
        add(hiltWork)
    }

    val kapts = arrayListOf<String>().apply {
        add(moshiCodegen)
        add(daggerProcessor)
        add(hiltCompiler)
        add(hiltAndroidCompiler)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}