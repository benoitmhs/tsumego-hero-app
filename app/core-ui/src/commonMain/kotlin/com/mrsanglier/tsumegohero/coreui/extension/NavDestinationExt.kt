package com.mrsanglier.tsumegohero.coreui.extension

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlin.reflect.KClass

inline fun <reified T : THDestination> NavDestination?.match(): Boolean {
    return this?.hierarchy?.any {
        it.hasRoute(T::class)
    } ?: false
}

fun NavDestination?.match(destinationClass: KClass<out THDestination>): Boolean {
    return this?.hierarchy?.any {
        it.hasRoute(destinationClass)
    } ?: false
}