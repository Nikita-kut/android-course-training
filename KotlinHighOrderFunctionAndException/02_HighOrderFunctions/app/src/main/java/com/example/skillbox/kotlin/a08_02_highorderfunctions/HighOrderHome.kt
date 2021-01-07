package com.example.skillbox.kotlin.a08_02_highorderfunctions

fun main() {
    val queueTest = Queue<String>(mutableListOf())
    queueTest.enqueue("111111")
    queueTest.enqueue("22")
    queueTest.enqueue("333333")
    queueTest.filterQueue()
    println(queueTest)
}

class Queue<T>(list: MutableList<T>) {

    private var queueList: MutableList<T> = list

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {
        return if (queueList.size > 0) queueList.removeAt(0) else null
        /* return queueList.removeFirstOrNull() либо можно так, но это эксперементальный метод
         требует добавление ссылки на эксперементальную библиотеку
         не уверен что можно через него */
    }

    fun filterQueue(customFilter: (listForFilter: MutableList<T>) -> Boolean = {it.toString().length > 5} ) {
        customFilter(queueList)
    }


    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }

}