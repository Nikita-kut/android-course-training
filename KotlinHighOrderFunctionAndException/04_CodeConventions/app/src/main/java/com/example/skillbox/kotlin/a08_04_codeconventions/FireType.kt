package com.example.skillbox.kotlin.a7extensionsobjectsenums

sealed class FireType(
    open val lineSize: Int
) {
    object SingleFire : FireType(lineSize = 1)

    data class LineFire(
        override val lineSize: Int
    ) : FireType(lineSize = lineSize)

    override fun toString(): String {
        return "FireType(lineSize=$lineSize)"
    }
}
