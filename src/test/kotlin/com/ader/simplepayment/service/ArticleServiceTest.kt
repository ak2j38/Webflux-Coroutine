package com.ader.simplepayment.service

import com.ader.simplepayment.repository.ArticleRepository
import com.ader.simplepayment.service.dto.ArticleCreateRequest
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleServiceTest(
    @Autowired private val articleService: ArticleService,
    @Autowired private val articleRepository: ArticleRepository
) : StringSpec({

    "create and get" {
        val previous = articleRepository.count()
        val article = articleService.create(
            ArticleCreateRequest(
                title = "test title",
                content = "test content",
                authorId = 1,
            )
        )
        val current = articleRepository.count()
        previous + 1 shouldBe current
        article.id shouldBe articleService.get(article.id).id
    }

    "getAll" {
        val created = articleService.create(
            ArticleCreateRequest(
                title = "test title",
                content = "test content",
                authorId = 1,
            )
        )
        articleService.getAll(null).toList().size shouldBeGreaterThan 0
    }
})