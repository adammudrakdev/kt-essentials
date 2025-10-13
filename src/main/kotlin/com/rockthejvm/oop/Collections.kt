package com.rockthejvm.oop

object Collections {
    //lists
    fun demoLists() {
        val aList: List<Int> = listOf(1, 2, 3)
        val thirdElement: Int = aList[2]
        val length: Int = aList.size

        println(aList)

        val find3 = aList.indexOf(3) // 2 because present, be -1 if not
        val sublist = aList.subList(1, 2)

        println(sublist)

        val has3 = aList.contains(3)
        val with4 = aList.plus(4) // New list, original remains untouched

        println(with4)

        val mutableList = mutableListOf(1, 2, 3)
        mutableList.add(0, 42)
        mutableList.removeAt(0)
        mutableList[1] = 56

        println(mutableList)
    }

    //arrays
    fun demoArrays() {
        val anArray = arrayOf(1, 2, 3)
        val thirdElement: Int = anArray[2]
        val length = anArray.size
        anArray[1] = 100
        for (element in anArray) {
            println(element)
        }
    }

    //sets
    fun demoSets() {
        val aSet = setOf(1, 2, 3, 4, 1, 2, 3)
        println(aSet) // 1, 2, 3, 4

        val contains1 = 1 in aSet
        val contains1_old = aSet.contains(1)

        val add7 = aSet.plus(7)
        println(add7)
        val add8 = add7 + 8
        println(add8)

        val without3 = add8.minus(3)
        println(without3)
        val without4_old = without3 - 4
        println(without4_old)

        val combined = aSet + setOf(10, 11, 12)
        println(combined)
        val anotherCombined = combined.plus(setOf(13, 14, 15))
        println(anotherCombined)

        val diff = anotherCombined.minus(setOf(13, 14, 15))
        println(diff)

        val intersect = diff.intersect(setOf(1, 2, 3, 4, 5, 6, 7, 8))
        println(intersect)

        //mutable
        val mutableSet = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8)
        mutableSet.add(42)
        println(mutableSet)
        mutableSet.remove(42)
        println(mutableSet)

        val setWithNotGuaranteedOrder = HashSet<String>()
        setWithNotGuaranteedOrder.add("j")
        setWithNotGuaranteedOrder.add("i")
        setWithNotGuaranteedOrder.add("h")
        setWithNotGuaranteedOrder.add("g")
        setWithNotGuaranteedOrder.add("f")
        setWithNotGuaranteedOrder.add("e")
        setWithNotGuaranteedOrder.add("d")
        setWithNotGuaranteedOrder.add("c")
        setWithNotGuaranteedOrder.add("b")
        setWithNotGuaranteedOrder.add("a")

        println(setWithNotGuaranteedOrder) // order is changed to alphabetical - due to hashes of these strings
    }

    //maps
    fun demoMaps() {
        val aMap = mapOf(1 to "one", 2 to "two", 3 to "three")
        val anotherMap = mapOf(Pair(1, "one"), Pair(2, "two"), Pair(3, "three"))

        val stringOfNumber = aMap[1]
        println(stringOfNumber)

        val newMap = aMap.plus(Pair(4, "four"))
        for ((key, value) in newMap) {
            println("$key -> $value")
        }
        val pairs = aMap.toList()
        println(pairs)
        println(pairs.toMap())
    }


    @JvmStatic
    fun main(args: Array<String>) {
        demoMaps()
    }
}