package expense.tracker

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expenses")
class ExpenseController(private val service: ExpenseService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@Valid @RequestBody request: ExpenseRequest): ApiResponse<Expense> {
        val expense = Expense(
            description = request.description,
            category = request.category,
            amount = request.amount,
            date = request.date
        )
        val savedExpense = service.add(expense)
        return ApiResponse(true, "Expense created", savedExpense)
    }

    @PostMapping("/batch")
    fun createMultiple(@Valid @RequestBody requests: List<ExpenseRequest>): ApiResponse<List<Expense>> {
        val savedExpenses = requests.map { req ->
            service.add(Expense(
                description = req.description,
                category = req.category,
                amount = req.amount,
                date = req.date
            ))
        }
        return ApiResponse(success = true, message = "Expenses created", data = savedExpenses)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody request: ExpenseRequest, @PathVariable id: Long): ApiResponse<Any> {
        return service.update(request, id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ApiResponse<Any>{
        if (service.delete(id)) {
            return ApiResponse(success = true, "Expense deleted", data = id)
        }else{
            return ApiResponse(success = false, "Expense not found", data = id)
        }
    }

    @GetMapping("/{category}")
    fun getByCategory(@PathVariable category: Category) = service.getByCategory(category)

    @GetMapping("/total")
    fun getTotal(): ApiResponse<Double> {
        val total = service.getAllExpenses()
        return ApiResponse(
            success = true,
            message = "Total expenses calculated",
            data = total
        )
    }
}