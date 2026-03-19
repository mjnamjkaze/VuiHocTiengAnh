# ProGuard rules for VuiHocTiengAnh

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Compose
-dontwarn androidx.compose.**

# Kotlin
-dontwarn kotlin.**
-dontwarn kotlinx.**
