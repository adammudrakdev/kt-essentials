package com.rockthejvm.practice

import java.awt.Dimension
import java.awt.Graphics
import java.util.Scanner
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants
import kotlin.system.exitProcess

object App {
    private lateinit var frame: JFrame
    private lateinit var imagePanel: ImagePanel

    class ImagePanel(private var image: Image) : JPanel() {
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            // render the picture inside this "graphics"
            image.draw(g)
        }

        override fun getPreferredSize(): Dimension =
            Dimension(image.width, image.height)

        fun replaceImage(newImage: Image) {
            image = newImage
            revalidate()
            repaint()
        }

        fun getImage() = image
    }

    fun loadResource(path: String) {
        val image = Image(Image.loadResource(path))
        if (!this::frame.isInitialized) {
            frame = JFrame("Kotlin Image UI App")
            imagePanel = ImagePanel(image)

            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.contentPane.add(imagePanel)
            frame.pack()
            frame.isVisible = true
        } else {
            imagePanel.replaceImage(image)
            frame.pack()
        }
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("""
            You are hereby presented with examples of possible commands:
            1) exit -> exit the programme;
            2) load test -> load an image to panel;
            3) save test -> save the image from the panel to resources;
            4) crop 0 0 400 400 -> crop x y z w -> crop loaded picture at x + z, y + w 
            5) blend another_test.jpg transparency (OR multiply OR screen)
                -> blend loaded picture with another picture;
            6) invert -> inverts colours of the loaded image;
            7) grayscale -> make the loaded image black&white;
            8) otherwise no operation will be proceeded;
            9) programme only works with jpgs, do not specify file extension;
        """.trimIndent())
        val scanner = Scanner(System.`in`)
        while (true) {
            print("Input your command...\n>")
            val fullCommand = scanner.nextLine()
            val commands = fullCommand.split(" ")

            if (commands.isEmpty()) {
                println("Nothing was entered... Try again...")
                continue
            } else if (commands[0].lowercase().startsWith("exit")) {
                println("Exiting the programme...")
                exitProcess(0)
            } else if (commands[0].lowercase() == "load") {
                try {
                    loadResource(commands[1])
                    println("Successfully loaded ${commands[1]}")
                } catch (_: Exception) {
                    println("Failed to load ${commands[1]}. Such a file might not exist...")
                }
            } else if (!this::frame.isInitialized) {
                println("No image has been loaded...Please try again...")
                continue
            } else {
                if (commands[0].lowercase() == "save") {
                    imagePanel.getImage().saveResources(commands[1])
                    println("Successfully saved ${commands[1]}.jpg")
                } else {
                    imagePanel.replaceImage(
                        Transformation.parse(fullCommand)
                            .process(imagePanel.getImage()))
                    frame.pack()
                }
            }
        }
    }
}