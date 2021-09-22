package com.ecomindo.onboarding.sampleapi.dao;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.ecomindo.onboarding.sampleapi.dto.OptionsDTO;
import com.ecomindo.onboarding.sampleapi.model.BooksModel;

@DataJpaTest
public class BooksDaoMockJPATest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BooksDao booksDao;

    @BeforeEach
    public void init() {
    	// given
//    	BooksModel book1 = new BooksModel("test1","test description1", "test author1");
//    	entityManager.persist(book1);
    	
    	BooksModel book2 = new BooksModel("test2","test description2", "test author2");
        entityManager.persist(book2);
        
        entityManager.flush();
    }
    
    @Test
    public void test_findByTitle() {        
        // when
        List<BooksModel> found = booksDao.findByTitle("test2");
        
        BooksModel result = null;
        if(found.size() > 0) {
        	result = found.get(0);
        }

        // then
        assertThat(result.getTitle())
          .isEqualTo("test2");
    }
    
    @Test
    public void test_findDDLTitle() {        
        // when
        List<OptionsDTO> found = booksDao.findDDLTitle();
        
        List<OptionsDTO> expected = new ArrayList<>();
        expected.add(new OptionsDTO("test2".concat(" - ").concat("test author2"), 1));
        
        // then
        assertThat(found.size()).isEqualTo(expected.size());
    }
}
