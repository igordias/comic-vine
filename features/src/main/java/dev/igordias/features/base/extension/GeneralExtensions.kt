package dev.igordias.features.base.extension

fun unless(predicate: Boolean?, handler: () -> Unit): Boolean? {
    if (predicate != true) {
        handler.invoke()
    }
    return predicate
}