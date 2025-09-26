package expense.tracker

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long>{


    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    fun getTotalAmount(): Double
}

