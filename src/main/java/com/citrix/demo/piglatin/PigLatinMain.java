package com.citrix.demo.piglatin;

/**
 * Simple PigLatin quick launch
 * 
 * @author Roman Soukal
 *
 */
public class PigLatinMain {
	private static final String TO_TRANSLATE = "Place the text here, to translate into 'pig-latin'!";

	public static void main(String[] args) {
		PigLatinTranslator translator = new PigLatinTranslator();
		System.out.println(translator.translate(TO_TRANSLATE));
	}
}
