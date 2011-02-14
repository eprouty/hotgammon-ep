package zetaMon;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;

import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;
import baseMon.Location;
import baseMon.strategies.SetupStrategy;


public class TestZetaMon {
	private SetupStrategy SS;
	private Game game;
	
	@Before public void setup(){
		game = new GameImpl(new ZetaFactory());
		game.newGame();
	}
	
	@Test public void StrategyColorsSetupProperly(){
		SS = new ZetaSetupStrategy();
		
		Hashtable<Location, Color> boardTest = new Hashtable<Location, Color>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				boardTest.put(l, Color.BLACK);
				break;
			case R2:
				boardTest.put(l, Color.BLACK);
				break;
			case R3:
				boardTest.put(l, Color.BLACK);
				break;
			case B1:
				boardTest.put(l, Color.RED);
				break;
			case B2:
				boardTest.put(l, Color.RED);
				break;
			case B3:
				boardTest.put(l, Color.RED);
				break;
			//setup the blank areas with the default information
			default:
				boardTest.put(l, Color.NONE);
			}
		}
		
		assertTrue(boardTest.equals(SS.setupLocationColor()));
	}
	
	@Test public void StrategyCountSetupProperly(){
		SS = new ZetaSetupStrategy();
		
		Hashtable<Location, Integer> boardTest = new Hashtable<Location, Integer>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				boardTest.put(l, 3);
				break;
			case R2:
				boardTest.put(l, 3);
				break;
			case R3:
				boardTest.put(l, 3);
				break;
			case B1:
				boardTest.put(l, 3);
				break;
			case B2:
				boardTest.put(l, 3);
				break;
			case B3:
				boardTest.put(l, 3);
				break;
			//setup the blank areas with the default information
			default:
				boardTest.put(l, 0);
			}
		}
		
		assertTrue(boardTest.equals(SS.setupLocationCount()));
	}
	
	@Test public void ThreeCheckersOnR1(){
		assertEquals(3, game.getCount(Location.R1));
	}
	
	@Test public void BlackCheckerOnR1(){
		assertEquals(Color.BLACK, game.getColor(Location.R1));
	}
	
	@Test public void ThreeCheckersOnB2(){
		assertEquals(3, game.getCount(Location.B2));
	}
	
	@Test public void RedCheckerOnB3(){
		assertEquals(Color.RED, game.getColor(Location.B2));
	}
	
	@Test public void EmptySpaceAtB11(){
		assertEquals(Color.NONE, game.getColor(Location.B11));
		assertEquals(0, game.getCount(Location.B11));
	}
}
