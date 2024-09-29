package org.walks
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

val dataMap = mutableMapOf<String, String>()

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
fun main() {
    val name = "Kotlin"
    //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
    // 查看 IntelliJ IDEA 建议如何修正。
    println("Hello, " + name + "!")

    embeddedServer(Netty, port = 8080) {
        routing {
            // 这里将是处理特定路由的逻辑
            post("/getRandomWord") {
                val parameters = call.receiveParameters()
                val key = parameters["key"]
                val password = parameters["password"]

                if (key!= null && password!= null) {
                    // 获取到了key和password参数将它们组合成唯一标识
                    val uniqueIdentifier = "$key:$password"
                    if (dataMap.containsKey(uniqueIdentifier)) {
                        handleExistingIdentifier(call,uniqueIdentifier)
                    } else {
                        // 如果不存在，生成随机码，这里简单生成一个四位数字的随机码示例
                        handleNewIdentifier(call = call,uniqueIdentifier)
                    }
                } else {
                    if (key == null) {
                        call.respondText("缺少key参数", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                    if (password == null) {
                        call.respondText("缺少password参数", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                }
            }
        }
    }.start(wait = true)
}

private suspend fun handleExistingIdentifier(call: ApplicationCall, uniqueIdentifier: String) {
    val randomCode = dataMap[uniqueIdentifier] !!
    call.respondText(randomCode)
}

private suspend fun handleNewIdentifier(call: ApplicationCall, uniqueIdentifier: String) {
    try {
        // 如果不存在，生成随机码，这里生成一个四位纯数字随机码
        val randomCode = (1000..9999).random(Random.Default).toString()
        dataMap[uniqueIdentifier] = randomCode
        call.respondText(randomCode)
    } catch (e: Exception) {
        call.respondText("随机码生成失败", status = HttpStatusCode.InternalServerError)
    }
}

