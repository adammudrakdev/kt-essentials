package com.rockthejvm.oop

object Delegation {

    interface TextTransformer {
        val id: String
        fun transform(text: String): String
    }

    open class Translator(open val from: String, open val to: String) : TextTransformer {
        override val id: String = "Translator $from -> $to"
        override fun transform(text: String): String =
            "[$id] translating from $from to $to: $text"
    }


    // Clean inheritance overrides initial values
    class QuickTranslator(override val from: String, override val to: String): Translator(from, to) {
        override val id: String = "QuickTranslator $from -> $to"
    }

    class GPT4: TextTransformer {
        override val id: String = "GPT4"
        override fun transform(text: String): String =
            "[$id] something an AI would say"
    }

    val transformer: TextTransformer = Translator("English", "Romanian")
    val transformedText = transformer.transform("This is a Kotlin lesson")

    //composition vs inheritance - what to choose?
    class TextProcessor(private val transformer: TextTransformer = GPT4()) {
        fun process(text: String): String = transformer.transform(text)
    }

    // Note how decorator is not able to change anything!
    class TextProcessorDecorator(private val transformer: TextTransformer = GPT4()): TextTransformer {
        override val id: String = "new transformer.id!!!" // It uses value from param above ^
        override fun transform(text: String): String = transformer.transform(text) + " as Decorator"
    }

    class TextProcessorV2(private val transformer: TextTransformer = GPT4()): TextTransformer by transformer

    class TextProcessorV3(private val transformer: TextTransformer = GPT4()): TextTransformer by transformer {
        override fun transform(text: String): String = transformer.transform(text) + " as Decorator with by"
    }


    val transformedText_V2 = TextProcessor(
        Translator("English", "Romanian"))
        .process("This is a Kotlin lesson")
    val transformedText_V3 = TextProcessorV2(
        Translator("English", "Romanian"))
        .transform("This is a Kotlin lesson")
    val transformedText_V4 = TextProcessorDecorator(Translator("English", "Romanian")).transform("This is a Kotlin lesson")
    val transformedText_V5 = TextProcessorV3(Translator("English", "Romanian"))
        .transform("This is a Kotlin lesson")

    @JvmStatic
    fun main(args: Array<String>) {
        println(transformedText)
        println(transformedText_V2)
        println(transformedText_V3)
        println(transformedText_V4)
        println(transformedText_V5)

        println("Quick translator: ${QuickTranslator("English", "Romanian").transform("This is a Kotlin lesson")}")
    }
}