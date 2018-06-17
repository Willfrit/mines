package main.kotlin

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.stage.Stage
import main.kotlin.controllers.MainController
import javafx.scene.Scene
import javafx.scene.Parent
import javafx.stage.Screen


class MainApp : Application() {

    override fun start(stage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("/main_pane.fxml"))
        val root = loader.load<Parent>()
        val primaryScreenBounds = Screen.getPrimary().visualBounds
        val scene = Scene(root, primaryScreenBounds.width * RATIO_WINDOWS, primaryScreenBounds.height * RATIO_WINDOWS)
        stage.scene = scene
        stage.isResizable = false
        stage.title = "Mines IA"
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java)
        }

        const val RATIO_WINDOWS = 0.8
    }
}