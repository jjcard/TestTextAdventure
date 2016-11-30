package jjcard.text.game.basic;

import jjcard.text.game.impl.Item;

public class Pit extends Item {

	public Pit() {
		super(new Builder().name("Pit").roomDescription("I feel a draft"));
	}

}
