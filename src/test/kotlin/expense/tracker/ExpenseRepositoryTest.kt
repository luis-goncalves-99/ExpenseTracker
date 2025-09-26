package expense.tracker

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: ExpenseRepository

    @BeforeEach
    fun cleanDb() {
        repository.deleteAll()
    }

    @Test
    fun `POST valid expense returns created`() {
        val json = """
            {
                "description": "Taxi",
                "category": "TRANSPORT",
                "amount": 15.0,
                "date": "2025-09-26"
            }
        """.trimIndent()

        mockMvc.perform(post("/expenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.description").value("Taxi"))
    }

    @Test
    fun `POST invalid expense returns bad request`() {
        val json = """
            {
                "description": "",
                "category": "TRANSPORT",
                "amount": -5.0,
                "date": "2025-09-26"
            }
        """.trimIndent()

        mockMvc.perform(post("/expenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `DELETE non-existent expense returns failure`() {
        mockMvc.perform(delete("/expenses/999"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.message").value("Expense not found"))
    }

    @Test
    fun `Get the Sum of all expenses returns success`() {
        val expensesJson = """
        [
            {
                "description": "Taxi",
                "category": "TRANSPORT",
                "amount": 15.0,
                "date": "2025-09-26"
            },
            {
                "description": "Uber",
                "category": "TRANSPORT",
                "amount": 25.0,
                "date": "2025-09-26"
            }
        ]
        """.trimIndent()
        mockMvc.perform(post("/expenses/batch")
            .contentType(MediaType.APPLICATION_JSON)
            .content(expensesJson))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.length()").value(2)) // two expenses created

        // Call endpoint to get total sum
        mockMvc.perform(get("/expenses/total"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").value(40.0))
    }
}
