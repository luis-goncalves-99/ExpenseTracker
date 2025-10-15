package expense.tracker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun home(): Map<String, Any> {
        return mapOf(
            "message" to "Welcome to Kotlin Expense Tracker API",
            "version" to "1.0.0",
            "endpoints" to mapOf(
                "expenses" to "/expenses",
                "database" to "/h2-console"
            ),
            "status" to "running"
        )
    }
}