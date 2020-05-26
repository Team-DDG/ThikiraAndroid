package dependencies

object Versions {
    const val minSdkVersion = 15
    const val minSdkVersionUser = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29

    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"
}