
public class Soundex {
	public static char soundexCode(char c) {
		if (c == 'B' || c == 'F' || c == 'P' || c == 'V') {
			return '1';
		} else if (c == 'C' || c == 'G' || c == 'J' || c == 'K' || c == 'Q' || c == 'S' || c == 'X' || c == 'Z') {
			return '2';
		} else if (c == 'D' || c == 'T') {
			return '3';
		} else if (c == 'L') {
			return '4';
		} else if (c == 'M' || c == 'N') {
			return '5';
		} else if (c == 'R') {
			return '6';
		}
		
		return '0';
	}
	
	public static String stringToSoundex(String word) {
		StringBuilder str = new StringBuilder();
		
		int i = 0;
		char[] chars = word.toUpperCase().toCharArray();
		char first = soundexCode(chars[i++]);
		char lastCode = '0';
		for (; i < chars.length; i++) {
			char c = soundexCode(chars[i]);
			if (c != '0' && c != lastCode) {
				str.append(c);
				lastCode = c;
			}
		}
		
		str.append("000");
		if (str.charAt(0) == first) {
			return chars[0] + str.substring(1, 4);
		} else {
			return chars[0] + str.substring(0, 3);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(stringToSoundex("clog"));
		System.out.println(stringToSoundex("dog"));
		System.out.println(stringToSoundex("Erin"));
		System.out.println(stringToSoundex("Ashcraft"));
		System.out.println(stringToSoundex("Pfister"));
		System.out.println(stringToSoundex("Honeyman"));
		System.out.println(stringToSoundex("Tymczak"));
		System.out.println(stringToSoundex("Matthew"));
	}
}
