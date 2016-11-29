package jjcard.text.game.basic;

import java.util.Arrays;

import jjcard.text.game.parser.ITextTokenType;

public enum WumpTokenType implements ITextTokenType {
	//verbs
	SHOOT(false, "shoot", "s"), MOVE(false, "move", "m"), QUIT(false, "quit");
	//attributes
	private final boolean isObject;
	private final String[] defaultWords;
	private final int length;
	
	private WumpTokenType(boolean isObject, String...defaultWords){
		this.isObject = isObject;
		this.defaultWords = defaultWords;
		this.length = defaultWords.length;
	}
	private WumpTokenType(){
		this(true);
	}
	@Override
	public String[] defaultWords() {
		return Arrays.copyOf(defaultWords, length);
	}

	@Override
	public boolean isObject() {
		return isObject;
	}

	@Override
	public boolean isVerb() {
		return !isObject;
	}

}
