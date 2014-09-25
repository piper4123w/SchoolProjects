/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

/*
 * This code is a copy of BoxBugCritter.java and ChameleonCritter.java 
 * from the AP Computer Science GridWorld Case Study
 * The code that this is based on was written by Cay Horstmann, Chris Nevison, and Barbara Cloud Wells
 * 
 * @author Chris Santella
 * With help from Jason Arnold, Steven Mitchell, Dennis Brylow
 * Created Aug 30, 2014
 * Last Modified Sept 3, 2014
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;


import java.awt.Color;

/**
 * This class runs a world that contains Chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class PsychicGeckoTester
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(1,1), new PsychicGecko());
        world.add(new Location(8,7), new PsychicGecko());

        world.add(new Location(1,5), new Rock(Color.RED));
        world.add(new Location(2,8), new Rock(Color.BLUE));
        world.add(new Location(3,2), new Rock(Color.ORANGE));
        world.add(new Location(4,7), new Rock(Color.MAGENTA));
        world.add(new Location(5,0), new Rock(Color.ORANGE));
        world.add(new Location(5,5), new Rock(Color.PINK));
        world.add(new Location(6,7), new Rock(Color.CYAN));
        world.add(new Location(6,2), new Rock(Color.YELLOW));
        world.add(new Location(7,8), new Rock(Color.RED));
        
        world.show();
    }
}