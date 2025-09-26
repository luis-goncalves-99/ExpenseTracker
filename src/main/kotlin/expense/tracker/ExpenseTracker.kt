package expense.tracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpenseTracker

fun main(args: Array<String>) {
    runApplication<ExpenseTracker>(*args)
}