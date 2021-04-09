package de.imedia24.shop.controller

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.context.WebApplicationContext
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList


@ExtendWith
@AutoConfigureMockMvc
class ProductControllerTest {

    @InjectMocks
    var productController: ProductController? = null

    @Mock
    var productEntity: ProductEntity? = null

    @Test
    fun shouldReturnProductsBySkusIfTheyExist() {
        // given
        val product_1 = ProductEntity("1", "Tv", "Samsung 60 px ",
                345.toInt(), 5645.34.toBigDecimal(), ZonedDateTime.now(), ZonedDateTime.now())

        val product_2 = ProductEntity("2", "Tv", "Samsung 60 px ",
                345.toInt(), 5645.34.toBigDecimal(), ZonedDateTime.now(), ZonedDateTime.now())
        val list: MutableList<ProductEntity> = ArrayList<ProductEntity>()
        list.addAll(Arrays.asList(product_1, product_2))

        val skus: MutableList<String> = mutableListOf("1", "2")
        // when
        val result: ResponseEntity<MutableList<ProductResponse>>? =
                productController?.getProductsBySkus(skus)

        // then
        assertThat(result?.body?.size?.toInt()?.equals(2))
        assertThat(result?.body?.get(0)?.sku.equals(product_1.sku))
        assertThat(result?.body?.get(1)?.sku.equals(product_2.sku))
        assertThat(result?.statusCode?.equals(200))

    }
    
    @Test
    fun shouldReturnEmptyListIfProductsDoesntExist() {
        // given
        val skus: MutableList<String> = mutableListOf("1")
        // when
        val result: ResponseEntity<MutableList<ProductResponse>>? =
                productController?.getProductsBySkus(skus)

        assertThat(result?.body?.size?.toInt()?.equals(0))
        assertThat(result?.statusCode?.equals(200))
    }
}

