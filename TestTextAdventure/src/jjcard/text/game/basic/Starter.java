package jjcard.text.game.basic;

import java.util.Random;

import jjcard.text.game.ILocation;
import jjcard.text.game.impl.Player;

public class Starter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();	
		WumpGame game = new WumpGame(generateMap(r), getPlayer());
		
		game.setOutput(System.out);
		
		game.play();
		
		

	}
	
	private static ILocation generateMap(Random r){
		MazeLocation start = new MazeLocation(r.nextInt(20));
		return start;
	}
	private static Player getPlayer(){
		Player player = new Player.Builder().name("player").build();
		return player;
	}

}
