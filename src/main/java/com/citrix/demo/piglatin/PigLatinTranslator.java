package com.citrix.demo.piglatin;

/**
 * 
 * Translates String (into “pig-latin”) using the following rules: * 
 * - Words that start with a consonant have their first letter moved to the end of the word and the letters “ay” added to the end.
 *   - Hello becomes Ellohay
 * - Words that start with a vowel have the letters “way” added to the end.
 *   - apple becomes appleway
 * - Words that end in “way” are not modified.
 *   - stairway stays as stairway
 * - Punctuation must remain in the same relative place from the end of the word.
 *   - can’t becomes antca’y
 *   - end. becomes endway.
 * - Hyphens are treated as two words
 *   - this-thing becomes histay-hingtay
 * - Capitalization must remain in the same place. 
 *   (place is considered as index of char without punctuation - to avoid collision of upper case with punctuation)
 *   - Beach becomes Eachbay
 *   - McCloud becomes CcLoudmay
 *
 * @author Roman Soukal
 * 
 */
public class PigLatinTranslator {

	/**
	 * Translates String into “pig-latin”
	 * @param input input String
	 * @return translated String
	 */
	public String translate(String input) {
		if (input==null || input.length()==0) {
			return input;
		}
		
		String[] words = input.split("[ -]");
		String whiteSpaces = input.replaceAll("[^ -]", "");
		
		String[] translatedWords = translateWords(words);
		
		// builds the output from translated words and original whiteSpaces
		StringBuilder result = new StringBuilder();		
		int i = 0;
		for (; i<translatedWords.length-1; i++) {
			result.append(translatedWords[i]);
			result.append(whiteSpaces.charAt(i));
		}
		result.append(translatedWords[i]);
		
		// input ends with white spaces (whiteSpaces are longer than words array)
		if (whiteSpaces.length()>i) {
			result.append(whiteSpaces.substring(i));
		}
		
		return result.toString();
	}
	
	private String[] translateWords(String[] words) {
		String[] newWords = new String[words.length];
		for (int i=0; i<words.length; i++) {
			newWords[i] = translateWord(words[i]);
		}
		
		return newWords;
	}
	
	private String translateWord(String word) {
		// remove punctuation
		String withoutPunctuation = word.replaceAll("[^\\w]", "");
		if (withoutPunctuation.isEmpty()) {
			// punctuation only or empty String
			return word;
		}
		
		String newWord = withoutPunctuation.toLowerCase();
		newWord = applyRules(newWord);
		newWord = backToUpperCase(newWord, withoutPunctuation);
		newWord = returnPunctation(newWord, word);
		return newWord;
	}
	
	private String applyRules(String word) {
		// apply modifying rules
		if (word.endsWith("way")) { 
			// do nothing
		} else if (word.matches("[aeiouy].*")) {
			// word start with a vowel
			word += "way";
		} else {
			// word start with a consonant
			word = word.substring(1) + word.substring(0, 1) + "ay";
		}
		return word;
	}
	
	private String backToUpperCase(String newWord, String originalWord) {
		StringBuilder withUpperCase = new StringBuilder();
		
		int i = 0;
		for (; i<originalWord.length(); i++) {
			if (Character.isUpperCase(originalWord.charAt(i))) {
				withUpperCase.append(Character.toUpperCase(newWord.charAt(i)));
			} else {
				withUpperCase.append(newWord.charAt(i));
			}
		}
		// newWord may be longer than originalWord
		if (newWord.length()>i) {
			withUpperCase.append(newWord.substring(i));
		}
		
		return withUpperCase.toString();
	}
	
	private String returnPunctation(String newWord, String originalWord) {
		StringBuilder withPunctuation = new StringBuilder(newWord);
		
		for (int i=0; i<originalWord.length(); i++) {
			// inserts punctuation to the translated word
			if (!Character.isLetterOrDigit(originalWord.charAt(originalWord.length()-i-1))) {
				withPunctuation.insert(withPunctuation.length()-i, originalWord.charAt(originalWord.length()-i-1));
			}
		}
		return withPunctuation.toString();
	}
}
