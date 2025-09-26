package expense.tracker

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var description: String = "",
    var amount: Double = 0.0,

    @Enumerated(EnumType.STRING)
    var category: Category,

    var date: LocalDate = LocalDate.now()
) {

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun toString(): String {
        return "Expense(id='$id', description='$description', amount='$amount', category='$category', date='$date"
    }
}
