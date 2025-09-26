package expense.tracker

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExpenseService(private val repository: ExpenseRepository) {

    // Get all the expenses
    fun getAll() = repository.findAll()

    // Add a new expense
    fun add(expense: Expense): Expense { return  repository.save(expense) }

    // Get expense by ID
    fun getById(id: Long) = repository.findById(id)

    // Delete expense by ID
    fun delete(id: Long): Boolean = repository.existsById(id).also { if (it) repository.deleteById(id)}

    // Update an existing expense
    fun update(expense: ExpenseRequest, id: Long): ApiResponse<Any> {
        val existentExpenseOpt = repository.findById(id)
        if (existentExpenseOpt.isEmpty) {
            return ApiResponse(success = false, "Expense not found", data = id)
        }
        val existentExpense = existentExpenseOpt.get()

        // Update the fields
        existentExpense.description = expense.description
        existentExpense.category = expense.category
        existentExpense.amount = expense.amount
        existentExpense.date = expense.date

        // Save the updated expense
        repository.save(existentExpense)

        return ApiResponse(success = true, "Expense Updated", data = existentExpense)
    }

    fun getByCategory(category: Category): ApiResponse<Any>  {
        val filteredExpenses = repository.findAll().filter {it.category == category }
        val message = if (filteredExpenses.isEmpty()) {
            "No expenses found for category $category"
        }else {
            "Expenses for category $category"
        }
        return ApiResponse(
            success = true,
            message = message,
            data = filteredExpenses)
    }

    fun getAllExpenses(): Double  {
        return repository.getTotalAmount()
    }



    /*
    Future

    Get expenses within a date range.
    Get expenses above/below a certain amount.

    Calculate totals by category.
    Monthly or yearly summaries.

    */
}