package com.example.skillbox.kotlin.a08_01_generics

import kotlin.random.Random

fun main() {
    val intListForPrint = filterList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    val doubleListForPrint = filterList(listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0))
    intListForPrint.forEach { println(it) }
    doubleListForPrint.forEach { println(it) }

    val queue = Queue<String>()


    queue.enqueue("Test1")
    queue.enqueue("Test2")
    queue.enqueue("Test3")
    queue.enqueue("Test4")
    queue.enqueue("Test5")
    queue.enqueue("Test6")
    println(queue)
    queue.dequeue()
    println(queue)
    queue.dequeue()
    queue.dequeue()
    queue.dequeue()
    queue.dequeue()
    queue.dequeue()
    println(queue)
    queue.dequeue()
    queue.dequeue()
    println(queue)

}

fun <T : Number> filterList(list: List<T>): List<T> {
    return list.filter { it.toInt() % 2 == 0 }
}

class Queue<T>() {

    private var queueList: MutableList<T> = mutableListOf()

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {
        return if (queueList.size > 0) queueList.removeAt(0) else null
        /* return queueList.removeFirstOrNull() либо можно так, но это эксперементальный метод
         требует добавление ссылки на эксперементальную библиотеку
         не уверен что можно через него */
    }

    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }

}

sealed class Result<out T, in R> {

    data class Success<T, R>(val successResult: T) : Result<T, R>()

    data class Error<T, R>(val errorResult: R) : Result<T, R>()
}

fun returnResult(): Result<Int, String> {
    val randomInt = (1..10).random()
    return if (randomInt in 1..5) Result.Success<Int, String>(3) else Result.Error<Int, String>("Error")
}


