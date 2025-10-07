package com.rockthejvm.practice

import java.awt.Dimension
import java.awt.Graphics
import java.util.Scanner
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

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
            print("> ")
            val command = scanner.nextLine()
            println(command)
        }
    }
}