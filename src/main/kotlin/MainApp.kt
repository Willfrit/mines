package main.kotlin

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

class MainApp : Application() {

    override fun start(stage: Stage) {
        val pane = Pane()
        val scene = Scene(pane, 200.0, 200.0)
        stage.scene = scene
        stage.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java)
        }
    }
}