package jp.leve_five.careerup.Roma;

public class ReplaceArabicToRoman {
	// 数字の文字列をローマ数字に変換
	public String convertNumberToRoman(int numberInt) {
		// 入力値が4000未満もしくは１以上であるか判定
		isNumberWithinNormalLimits(numberInt);
		
		String numberString = String.valueOf(numberInt);
		byte stringBytes[] = numberString.getBytes();
		StringBuffer resultRoman = new StringBuffer();
		int numberDigit = stringBytes.length;
		int byteDigit = 0;
		// ローマ数字の2次元配列　配列のインデックスに合わせて、１〜９、１０〜９０、１００〜９００，１０００〜４０００まで
		String romanTable[][] = {
				{ "", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", },
				{ "", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc", },
				{ "", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm", },
				{ "", "m", "mm", "mmm", "mmmm", "", "", "", "", "", } };

		addRoman(stringBytes, resultRoman, numberDigit, byteDigit, romanTable);
		return resultRoman.toString().toUpperCase();
	}

	private void isNumberWithinNormalLimits(int numberInt) {
		if (numberInt >= 4000 || 0 >= numberInt) {
			throw new RuntimeException("変換範囲外です");
		}
	}

	// StringBuffer romanに入力値の桁に文字列を追加するswitch文
	private void addRoman(byte[] stringBytes, StringBuffer roman,
			int numberDigit, int byteDigit, String[][] romanTable) {
		switch (numberDigit) {
		default:
			throw new RuntimeException("無効な数値です");
		case 4:
			roman.append(romanTable[3][(stringBytes[byteDigit] - '0')]);
			byteDigit++;
		case 3:
			roman.append(romanTable[2][(stringBytes[byteDigit] - '0')]);
			byteDigit++;
		case 2:
			roman.append(romanTable[1][(stringBytes[byteDigit] - '0')]);
			byteDigit++;
		case 1:
			roman.append(romanTable[0][(stringBytes[byteDigit] - '0')]);
		}
	}

}
