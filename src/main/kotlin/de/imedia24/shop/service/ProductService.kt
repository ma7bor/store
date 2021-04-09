package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.ProductEntity.Companion.toProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.ZonedDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        return productRepository.findBySku(sku)?.toProductResponse()
    }

    fun checkSkuIfExist(sku: String): Boolean {
        var productEntity: ProductEntity? = productRepository.findBySku(sku)
        if (productEntity != null) {
            return true
        } else return false
    }
    fun checkProductIfExist(productResponse: ProductResponse): Boolean {
        var productEntity: ProductEntity? = productRepository.
        findByNameAndDescriptionAndStockAndPrice(productResponse.name, productResponse.description ,
                productResponse.stock , productResponse.price)
        if (productEntity != null) {
            return true
        } else return false
    }

    fun getProductsBySkus(skus: MutableList<String>): MutableList<ProductResponse> {
        var productResponses = mutableListOf<ProductResponse>()
        for (sku: String in skus) {
            var productResponse: ProductResponse? = findProductBySku(sku)
            if (productResponse != null) {
                productResponses.add(productResponse)
            }
        }
        return productResponses
    }

    fun addProduct(productResponse: ProductResponse): String {
        productRepository.save(productResponse.toProductEntity())
        return "Product added successfully"
    }

    fun updateProduct(productResponse: ProductResponse): String {
        val productEntity: ProductEntity = productRepository.findBySku(productResponse.sku)!!
        productEntity.name = productResponse.name
        productEntity.description = productResponse.description
        productEntity.stock = productResponse.stock
        productEntity.price = productResponse.price
        productEntity.updatedAt = ZonedDateTime.now()
        productRepository.save(productEntity)
        return "Product updated successfully"
    }
}
