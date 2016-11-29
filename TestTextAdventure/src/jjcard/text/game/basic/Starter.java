package jjcard.text.game.basic;

import jjcard.text.game.ILocation;
import jjcard.text.game.impl.Player;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WumpGame game = new WumpGame(generateMap(), getPlayer());
		
		game.setOutput(System.out);
		
		game.play();
		
		

	}
	
	private static ILocation generateMap(){
		return null;
	}
	private static Player getPlayer(){
		return null;
	}

}
