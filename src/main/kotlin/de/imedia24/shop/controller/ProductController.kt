package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
            @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if (product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products")
    fun getProductsBySkus(@RequestParam("skus") skus: MutableList<String>):
            ResponseEntity<MutableList<ProductResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsBySkus(skus))
    }

    @PostMapping("/product/add")
    fun addProduct(@RequestBody @Valid productResponse: ProductResponse, result: BindingResult): ResponseEntity<String> {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.globalErrors.toString())
        } else if (productService.checkSkuIfExist(productResponse.sku)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This Sku is already taken by another product!")
        } else if (productService.checkProductIfExist(productResponse)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This Product is already exist!")
        } else
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productResponse))
    }

    @PutMapping("/product/update")
    fun updateProduct(@RequestBody @Valid productResponse: ProductResponse): ResponseEntity<String> {
        if (!productService.checkSkuIfExist(productResponse.sku)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product NOT FOUND with that Sku!")
        } else if (productService.checkProductIfExist(productResponse)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This Product is already exist!")
        } else return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productResponse))
    }
}
