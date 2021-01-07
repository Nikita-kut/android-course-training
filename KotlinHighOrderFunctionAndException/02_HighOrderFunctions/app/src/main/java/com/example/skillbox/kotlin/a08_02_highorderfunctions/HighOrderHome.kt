package com.example.skillbox.kotlin.a08_02_highorderfunctions

fun main() {
    val queueTest = Queue<String>(listOf())
    queueTest.enqueue("111111")
    queueTest.enqueue("22")
    queueTest.enqueue("333333")
    println(queueTest.filterQueue { it.length > 5 })
}

class Queue<T>(list: List<T>) {

    private var queueList = list.toMutableList()

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {
        return if (queueList.size > 0) queueList.removeAt(0) else null
        /* return queueList.removeFirstOrNull() либо можно так, но это эксперементальный метод
         требует добавление ссылки на эксперементальную библиотеку
         не уверен что можно через него */
    }

    fun filterQueue(predicate: (T) -> Boolean): Queue<T> {
        val queueForAdd = mutableListOf<T>()
        queueList.forEachIndexed { index, it ->
            if (predicate(queueList[index])) {
                queueForAdd.add(it)
            }
        }
        return Queue(queueForAdd)
    }


    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }

}