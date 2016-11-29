package jjcard.text.game.basic;

import static jjcard.text.game.util.ObjectsUtil.checkArg;

import java.io.PrintStream;
import java.util.Scanner;

import jjcard.text.game.ILocation;
import jjcard.text.game.battle.IBattleSystem;
import jjcard.text.game.battle.impl.BasicBattleSystem;
import jjcard.text.game.impl.Player;
import jjcard.text.game.impl.TextGame;
import jjcard.text.game.parser.ITextParser;
import jjcard.text.game.parser.ITextTokenStream;
import jjcard.text.game.parser.TextToken;
import jjcard.text.game.parser.impl.BasicTextParser;
import jjcard.text.game.util.NotFoundException;
import jjcard.text.game.util.WorldUtil;

public class WumpGame extends TextGame<WumpTokenType, Player>{
	private WorldUtil<Player> worldUtil;
	private PrintStream output = System.out;
	private Scanner inputScanner;
	//TODO how to handle when mob is dead, can't remove it, since still has items...
	private IBattleSystem<Integer> battleSystem = new BasicBattleSystem(
			(a, d, damage) -> {output.println(" you have attacked " + d.getName() + " for " + damage + " damage.\n");output.println("You have slain " + d.getName() + "!");}, 
			(a, d, damage) -> {output.println(" you have attacked " + d.getName() + " for " + damage + " damage.\n");}
	);
	
	/**
	 * 
	 * @param current the current location. Must be non-null
	 * @param playerN
	 * @throws NullPointerException if the <code>current</code> argument or <code>player</code> argument is <code>null</code>
	 */
	public WumpGame(ILocation current, Player player) throws NullPointerException{
		checkArg(current, "current");
		checkArg(player, "player");
		this.player = player;
		worldUtil = new WorldUtil<Player>(current, player);
		parser = new BasicTextParser<WumpTokenType>();
		inputScanner = new Scanner(System.in);
	}
	
	public ILocation getCurrent() {
		return worldUtil.getCurrent();
	}
	public void setTextParser(ITextParser<WumpTokenType> parser) {
		this.parser = parser;
	}
	public void setScanner(Scanner inputScanner){
		this.inputScanner = inputScanner;
	}
	public ITextParser<WumpTokenType> getTextParser() {
		return parser;
	}
	public PrintStream getOuput() {
		return output;
	}

	public void setOutput(PrintStream out) {
		output = out;
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	protected String getInput() {
		return inputScanner.nextLine();
	}

	@Override
	protected void executeCommands(ITextTokenStream<WumpTokenType> stream) {
		TextToken<WumpTokenType> object = stream.getFirstObject();
		String token = object == null ? null : object.getStandardToken();
		if (token != null) {
			if (stream.getVerb() != null){
				switch (stream.getVerb().getType()) {
				case SHOOT:
					 attackMob(token, object);
					 break;
//				case LOOK:
//					 lookAt(token, object);
//					 break;
				case MOVE:
					movePlayer(token, object);
					break;
//				case INFO:
//					 info(token, object);
//					 break;
				case QUIT:
					quitGame();
					break;
				default:
					return;
				}				
			}
//			// For objects that can also be used to infer their verbs
//			switch (object.getType()) {
//			case DIRECTION:
//				movePlayer(token, object);
//				break;
//			case INVENTORY:
//				info(token, object);
//				break;
//			default:
//				return;
//			}

		} else {
			return;
		}
		
	}
	/**
	 * Sets gameOver to true
	 */
	protected void quitGame() {
		gameOver = true;
	}


//	protected void lookAt(String token, TextToken<WumpTokenType> object) {
//		String info = worldUtil.lookAt(object);
//		if (info == null){
//			output.println("You don't see anything like that");
//		} else {
//			output.println(info);
//		}
//		
//	}

//	protected void info(String token, TextToken<WumpTokenType> object) {
//		switch (object.getType()){
//		case INVENTORY:
//			if (player.getInventory().isEmpty()) {
//				output.println("You have nothing in your inventory");
//			} else {
//				output.println(player.inventoryOverview());
//			}
//		case MONEY:
//			output.println(player.getMoney());
//		case HEALTH:
//			output.println(player.getHealth());
//		case MAX_HEALTH:
//			output.println(player.getMaxHealth());
//		default:
//			output.println("Nothing by that name was found");
//		}
//		
//	}

	protected void movePlayer(String token, TextToken<WumpTokenType> object) {
		if (worldUtil.goDirection(token)){
			output.println(worldUtil.getCurrent().showRoom());
		} else {
			output.println("You can't go that direction.");
		}
	}

	protected void attackMob(String token, TextToken<WumpTokenType> object) {
		try {
			worldUtil.attackMob(token, battleSystem);
		} catch (NotFoundException e) {
			output.println("cannot find " + token);
		}
	}

	public boolean isGameOver(){
		return super.isGameOver() || player.isDead();
	}
	@Override
	protected void gameUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void endCleanUp() {
		output.print("Goodbye");
		
	}
}
