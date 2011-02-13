package baseMon.strategies;

import java.util.Hashtable;

import baseMon.Color;
import baseMon.Location;

public interface SetupStrategy {
	public Hashtable<Location, Color> setupLocationColor();
	public Hashtable<Location, Integer> setupLocationCount();
}
