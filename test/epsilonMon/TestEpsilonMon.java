package epsilonMon;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import baseMon.Game;
import baseMon.GameImpl;


public class TestEpsilonMon {
private Game game;
	
	@Before public void setup() {
	    game = new GameImpl(new EpsilonTestFactory());
	    game.newGame();
	}

	@Test public void randomDieRoll(){
		Random rg = new Random(2);
		game.nextTurn();
		assertArrayEquals(game.diceThrown(), new int[] {rg.nextInt(6) + 1, rg.nextInt(6) + 1});
	}
}
