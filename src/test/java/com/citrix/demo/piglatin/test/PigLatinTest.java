package com.citrix.demo.piglatin.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.citrix.demo.piglatin.PigLatinTranslator;

/**
 * Tests PigLatinTranslator
 * 
 * @author Roman Soukal
 *
 */
@RunWith(Parameterized.class)
public class PigLatinTest {
	private PigLatinTranslator translator;
	private String input;
	private String expected;
	
	@Before
	public void createTranslator() {
		translator = new PigLatinTranslator();
	}
	
	public PigLatinTest(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}
	
	@Parameterized.Parameters
	public static List<String[]> getData() {
		return Arrays.asList(new String[][] {
			{null, null},
			{"", ""},
			{" . ", " . "},
			{" a ", " away "},
			{"s", "say"},
			{"1s", "1s"},
			{" 500 ", " 500 "},
			{"Hello", "Ellohay"},
			{"apple", "appleway"},
			{"stairway", "stairway"},
			{"stairway  ", "stairway  "},
			{"  stairway", "  stairway"},
			{"  stairway  ", "  stairway  "},
			{"can't", "antca'y"},
			{"he'lLO", "ellO'Hay"}, // test of Case and punctuation collision
			{"end.", "endway."},
			{"'end'", "end'way'"},
			{"'Stairway'", "'Stairway'"},
			{"this-thing", "histay-hingtay"},
			{"Beach", "Eachbay"},
			{"McCloud", "CcLoudmay"},
			{"Hello apple, stairway can't really-never end.", "Ellohay appleway, stairway antca'y eallyray-evernay endway."},
			{"McCl:oud - - - can't  -", "CcLoud:may - - - antca'y  -"},
		});
	}
	
	@Test
	public void test() {
		assertEquals(expected, translator.translate(input));
	}
}
