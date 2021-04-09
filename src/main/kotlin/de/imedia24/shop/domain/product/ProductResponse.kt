package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import org.springframework.format.annotation.NumberFormat
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ProductResponse(
        @Valid
        @get:NotBlank(message = "Sku may not be null")
        @get:Size(max = 16, message = "Sku must be between 1 and 16 characters long")
        val sku: String,

        @get:NotBlank(message = "Name may not be null")
        @get:Size(min=7,message = "At least  7 characters needed!")
        val name: String,

        @get:Size(max = 125, message = "description should be contain less then 126 characters")
        val description: String,

        @get:NotNull(message = "stock may not be null")
        val stock: Int,

        @get:NotNull(message = "Price may not be null")
        val price: BigDecimal
) {
    companion object {
        fun ProductEntity.toProductResponse() = ProductResponse(
                sku = sku,
                name = name,
                description = description ?: "",
                stock = stock,
                price = price
        )
    }
}
