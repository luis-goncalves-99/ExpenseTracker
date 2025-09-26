package expense.tracker

import jakarta.persistence.Enumerated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class ExpenseRequest (
    @field:NotBlank
    @field:Size(max = 255)
    val description: String,

    val category: Category,

    @field:Positive
    val amount: Double,

    @field: PastOrPresent
    val date: LocalDate
)