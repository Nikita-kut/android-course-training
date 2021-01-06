package com.example.skillbox.kotlin.a08_01_generics

fun main() {
//    val genericObject = GenericsClass(0.0)
////    genericObject.updateItem(2.3)
//
//    val genericObject2 = GenericsClass(0.0)
////    genericObject.updateItem(2.3)
//    val genericObject3 = GenericsClass(0f)
//    val genericObject4 = GenericsClass(0f)
//
////    println(sumGeneric(genericObject, genericObject2))
////    println(sumGeneric(genericObject3, genericObject4))
////
////    updateContrVariant(ContrVariantClass<Any>(111))

}

class GenericsClass<out T : Number>(defaultValue: T) {
    private var item: T = defaultValue
        private set

    fun getItem(): T {
        return item
    }

//    fun updateItem(newItem: T) {
//        item = newItem
//        item.toFloat()
//    }
}

class ContrVariantClass<in T>(defaultValue: T) {
    private var item: T = defaultValue

    fun setItem(newItem: T) {
        item = newItem
    }

//    fun getItem(): T {
//        return item
//    }
}

fun sumGeneric(a: GenericsClass<Number>, b: GenericsClass<Number>): Int {
    return a.getItem().toInt() + b.getItem().toInt()
}

fun <T> printGenericObject(item: T) {
    println(item.toString())

}


//fun updateContrVariant( input: ContrVariantClass<Number>) {
//    input.setItem(2.2)
//}