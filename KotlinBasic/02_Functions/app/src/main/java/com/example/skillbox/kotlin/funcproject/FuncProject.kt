package com.example.skillbox.kotlin.funcproject

fun main() {
print(multiply(b = 5))
    print(hasChildAccess(height = 180, weight = 50, age = 13))
    print(functionReturnAny())
}

fun multiply (a: Int=100, b: Int): Int {
    return a * b
}

fun hasChildAccess (height: Int, weight: Int, age: Int): Boolean {
    return height > 150 && weight > 30 && age > 10
}

fun functionReturnAny (): Any {
return "dsd"

}