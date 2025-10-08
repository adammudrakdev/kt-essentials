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
        val scanner = Scanner(System.`in`)
        while (true) {
            print("Input your command...\n>")
            val fullCommand = scanner.nextLine()
            if (fullCommand.lowercase().startsWith("exit")) {
                println("Exiting the programme...")
                exitProcess(0)
            }

            if (fullCommand.split(" ").size < 2) {
                println("Command is too short! Please try again!")
                continue
            }
            val partsOfCommand = fullCommand.split(" ")
            val firstPartOfCommand = partsOfCommand[0]
            val secondPartOfCommand = partsOfCommand[1]
            if (firstPartOfCommand == "load") {
                if (!secondPartOfCommand.endsWith(".jpg")) {
                    println("Unknown format exception...Please try again...")
                } else {
                    try {
                        loadResource(secondPartOfCommand)
                        println("Successfully loaded $secondPartOfCommand")
                    } catch (_: Exception) {
                        println("Failed to load $secondPartOfCommand. Such a file might not exit...")
                    }
                }
            } else {
                if (!this::frame.isInitialized) {
                    println("No image has been loaded...Please try again...")
                } else if (firstPartOfCommand == "save") {
                    if (!secondPartOfCommand.endsWith(".jpg")) {
                        println("Unknown format exception...Please try again...")
                    } else {
                        imagePanel.getImage().saveResources(secondPartOfCommand)
                        println("Successfully saved ${fullCommand.split(" ")[1]}")
                    }
                } else {
                    val transformation = Transformation.parse(fullCommand)
                    imagePanel.replaceImage(transformation.process(imagePanel.getImage()))
                    frame.pack()
                }
            }
        }
    }
}