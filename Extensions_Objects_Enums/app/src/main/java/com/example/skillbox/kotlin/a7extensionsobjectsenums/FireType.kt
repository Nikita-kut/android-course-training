package com.example.skillbox.kotlin.a7extensionsobjectsenums

sealed class FireType {

    object SingleFire : FireType() {

    }

    data class LineFire(
            val lineSize: Int
    ) : FireType() {

    }
}