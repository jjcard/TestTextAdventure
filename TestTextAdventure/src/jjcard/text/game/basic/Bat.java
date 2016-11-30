package jjcard.text.game.basic;

import jjcard.text.game.impl.Mob;

public class Bat extends Mob {

	public Bat() {
		super(new Builder().name("Bat").roomDescription("Bats nearby"));
	}

}
