package com.rockthejvm.oop

object Generics {
    // Can store only ints - bad!
    interface MyLinkedIntList {
        fun head(): Int
        fun tail(): MyLinkedIntList
        // fun add(elem: Int): MyLinkedList
        // fun contains(e: MyLinkedList): Boolean
    }

    class EmptyIntList: MyLinkedIntList {
        override fun head(): Int = throw NoSuchElementException()
        override fun tail(): MyLinkedIntList = throw NoSuchElementException()
        // override fun add(elem: Int): MyLinkedList = NonEmptyList(elem, this)
        // override fun contains(e: MyLinkedList): Boolean = false
    }

    class NonEmptyIntList(private val h: Int, private val t: MyLinkedIntList): MyLinkedIntList {
        override fun head(): Int = h
        override fun tail(): MyLinkedIntList = t
    }

    // Might store Person, Animal, Int all together - bad!
    interface MyGeneralList {
        fun head(): Any
        fun tail(): MyGeneralList
    }

    class EmptyGeneralList: MyGeneralList {
        override fun head(): Int = throw NoSuchElementException()
        override fun tail(): MyGeneralList = throw NoSuchElementException()
    }

    class NonEmptyGeneralList(private val h: Int, private val t: MyGeneralList): MyGeneralList {
        override fun head(): Int = h
        override fun tail(): MyGeneralList = t
    }

    //GENERICS
    interface MyLinkedList<T> {
        fun head(): T
        fun tail(): MyLinkedList<T>
    }

    class EmptyList<A> : MyLinkedList<A> {
        override fun head(): A = throw NoSuchElementException()
        override fun tail(): MyLinkedList<A> = throw NoSuchElementException()
    }

    class NonEmptyList<A>(private val h: A, private val t: MyLinkedList<A>): MyLinkedList<A> {
        override fun head(): A = h
        override fun tail(): MyLinkedList<A> = t
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // No generics
        val simpleNumbers = NonEmptyIntList(1,
            NonEmptyIntList(2,
                NonEmptyIntList(3,
                    NonEmptyIntList(4,
                        EmptyIntList()))))

        // With generics
        val simpleNumbers2 = NonEmptyList<Int>(1,
            NonEmptyList(2,
                NonEmptyList(3,
                    NonEmptyList(4,
                        EmptyList<Int>()))))


    }
}