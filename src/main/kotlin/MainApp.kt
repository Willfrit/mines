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
        val scene = Scene(root, WIDTH_WINDOW, HEIGHT_WINDOW)
        stage.scene = scene
        stage.isResizable = true
        stage.title = "Mines IA"
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java)
        }

        const val RATIO_WINDOWS = 0.9
        val SCREEN_BOUND = Screen.getPrimary().visualBounds!!
        val WIDTH_WINDOW = SCREEN_BOUND.width * RATIO_WINDOWS
        val HEIGHT_WINDOW = SCREEN_BOUND.height * RATIO_WINDOWS
    }
}