package de.imedia24.shop.db.repository

import de.imedia24.shop.db.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface ProductRepository : CrudRepository<ProductEntity, String> {
    fun findBySku(sku: String): ProductEntity?
    fun findByNameAndDescriptionAndStockAndPrice(name:String , description:String , stock:Int , price:BigDecimal):ProductEntity?
}