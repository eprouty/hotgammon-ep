package backgamMonTest;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;


public class TestBackgamMon {
	private Game game;
	  
	@Before public void setup() {
		game = new GameImpl(new TestBackgamFactory());
		game.newGame();
	}
	
	@Test public void testTurnSelection(){
		game.nextTurn();
		Random rg = new Random(2);
		Color c;
		if (rg.nextInt(6) + 1 < rg.nextInt(6) + 1){
			c = Color.BLACK;
		} else {
			c = Color.RED;
		}
		assertEquals(c, game.getPlayerInTurn());
	}
}
