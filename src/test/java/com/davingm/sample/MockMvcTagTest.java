package com.davingm.sample;

import com.davingm.sample.model.Category;
import com.davingm.sample.model.Product;
import com.davingm.sample.model.Tag;
import com.davingm.sample.repository.CategoryRepository;
import com.davingm.sample.repository.ProductRepository;
import com.davingm.sample.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTagTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    public void test() throws Exception {
        Category cat = new Category();
        cat.setName("Motor");
        cat = categoryRepository.saveAndFlush(cat);

        Product prod = new Product();
        prod.setNama("yamaha");
        prod.setDeskripsi("motor wok wok");
        prod.setHarga(15000.0);
        prod.setCategory(cat);
        prod = productRepository.saveAndFlush(prod);

        Tag tag = new Tag();
        tag.setName("baru");
        tag = tagRepository.saveAndFlush(tag);

        String reqJson = "{\"tags_id\": [" + tag.getId() + "]}";

        String jsonResponse = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/products/" + prod.getId() + "/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson)
        ).andReturn().getResponse().getContentAsString();

        System.out.println("---- RESPONSE ----");
        System.out.println(jsonResponse);
    }
}
