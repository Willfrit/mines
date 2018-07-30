package network

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import kotlin.concurrent.thread


class MinesServer(port:Int) {

    init {
        val threadServ = thread {
            println("Start Server, port = " + port.toString())
            var serverSocket : ServerSocket? = null
            try {
                serverSocket = ServerSocket(port)

                println("Socket Create, wait client")

                val clientSocket = serverSocket.accept()
                val outBuf = PrintWriter(clientSocket.getOutputStream(), true)
                val inBuf = BufferedReader(
                        InputStreamReader(clientSocket.getInputStream()))

                println("Client connected")
                var inputLine : String? = inBuf.readLine()
                while (true) {
                    if (inputLine == "BYE") break
                    inputLine = inBuf.readLine()
                }
                println("Client send BYE : Close socket")
            } catch (e: IOException) {
                println(e.stackTrace)
            } finally {
                serverSocket?.close()
            }
        }
    }

}