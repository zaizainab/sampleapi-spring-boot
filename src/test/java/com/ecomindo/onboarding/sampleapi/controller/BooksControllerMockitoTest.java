package com.ecomindo.onboarding.sampleapi.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecomindo.onboarding.sampleapi.dao.BooksDao;
import com.ecomindo.onboarding.sampleapi.dto.OptionsDTO;
import com.ecomindo.onboarding.sampleapi.services.BooksService;
import com.ecomindo.onboarding.sampleapi.services.BooksServiceImpl;


@SpringBootTest
public class BooksControllerMockitoTest {

	@Mock
	BooksDao booksDao;

	@InjectMocks
	BooksService booksService = new BooksServiceImpl();

	@DisplayName("Test Mock find ddl")
	@Test
	void testFindDDL() {
		List<OptionsDTO> expected = new ArrayList<>();
		expected.add(new OptionsDTO("test2".concat(" - ").concat("test author2"), 1));

		when(booksDao.findDDLTitle()).thenReturn(expected);

		Assertions.assertEquals(expected.size(), booksDao.findDDLTitle().size());
		Assertions.assertArrayEquals(expected.toArray(), booksDao.findDDLTitle().toArray());
	}

//	@DisplayName("Test Mock insert")
//	@Test
//	void test_insert() {
//		List<OptionsDTO> expected = new ArrayList<>();
//		expected.add(new OptionsDTO("test2".concat(" - ").concat("test author2"), 1));
//
//		when(booksService.insert(title, description, author)).thenReturn(expected);
//
//		assertArrayEquals(expected.toArray(), booksDao.findDDLTitle().toArray());
//	}
}
