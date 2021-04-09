package de.imedia24.shop.db.entity

import de.imedia24.shop.domain.product.ProductResponse
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "products")
data class ProductEntity @JvmOverloads constructor(
        @Id
        @Column(name = "sku", nullable = false)
        var sku: String,

        @Column(name = "name", nullable = false)
        var name: String,

        @Column(name = "description")
        var description: String? = null,

        @Column(name = "stock")
        var stock: Int,

        @Column(name = "price", nullable = false)
        var price: BigDecimal,

        @UpdateTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: ZonedDateTime,

        @UpdateTimestamp
        @Column(name = "updated_at", nullable = false)
        var updatedAt: ZonedDateTime)
{
    companion object {
                    fun ProductResponse.toProductEntity() = ProductEntity(

                            sku = sku ,
                            name = name,
                            description = description ?: "",
                            stock = stock,
                            price = price,
                            createdAt = ZonedDateTime.now(),
                            updatedAt = ZonedDateTime.now()
                    )
            }
    }

