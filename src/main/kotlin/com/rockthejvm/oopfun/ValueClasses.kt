package com.rockthejvm.oopfun

object ValueClasses {
    //downside = memory overhead
    data class ProductNameOld(val name: String)
    data class ProductDescriptionOld(val description: String)

    //solution
    @JvmInline value class ProductName(val name: String)
    @JvmInline value class ProductDescription(val description: String)

    data class Product(val name: ProductName, val description: ProductDescription) // 40 other fields

    val kotlinProduct = Product(
        ProductName("Kotlin essentials"),
        ProductDescription("Bla bla bla description")
    )

    @JvmStatic
    fun main(args: Array<String>) {
        kotlinProduct
        print("")
    }
}