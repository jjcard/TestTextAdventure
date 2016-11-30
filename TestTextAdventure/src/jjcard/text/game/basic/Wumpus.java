package jjcard.text.game.basic;

import jjcard.text.game.impl.Mob;

public class Wumpus extends Mob {

	public Wumpus() {
		super(new Builder().name("Wumpus").roomDescription("I smell a wumpus").health(1));
	}

}
