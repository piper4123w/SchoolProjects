/*
 * AP(r) Computer Science GridWorld Case Study: Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 * 
 * This code is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * 
 * @author Barbara Cloud Wells
 * 
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class PsychicGecko extends Critter {
  public Location otherLocation;

  public void setOtherLocation(Location loc) {
    otherLocation = loc;
  }

  public void act() {
    boolean done = false;
    Location loc = getLocation();

    Actor otherGecko = findOther();
    Location otherLoc = otherGecko.getLocation();
    while (!done) {
      if (geckoIsNeighbor(loc, otherLocation)) {    // if the Geckos are next to eachother
        done = true;
      }

      else if (isFlowerNeighbor(loc) && !done) {   // else if flowers are placed next to Geckos
        Location nextLoc = getFlowerNeighborLocation(loc);
        eatFlower(nextLoc);
        this.moveTo(nextLoc);
      }
      else {
        System.out.println("entering find path method");
        findPath(loc, otherLoc);
      }

      // if (findPath(loc, otherLoc)) { //if find Path between Geckos
      // System.out.println("Found Path");
      // }
      // else {
      // System.out.println("something went wrong");
      // }
    }

  }

  private void eatFlower(Location nextLoc) {
    getGrid().get(nextLoc).removeSelfFromGrid();
    moveTo(nextLoc);
  }

  private Location getFlowerNeighborLocation(Location l) {
    ArrayList<Location> locations = getGrid().getOccupiedAdjacentLocations(l);
    for (Location loc : locations) {
      if (getGrid().get(loc) instanceof Flower) {
        return loc;
      }
    }
    return null;
  }

  public int Direction(Location from, int target) {

    int compAngle;
    int smallest = 361;
    ArrayList<Location> emptyList = getGrid().getEmptyAdjacentLocations(from);
    ArrayList<Location> beansList = BeansList(from);
    if (!emptyList.isEmpty()) {
      for (int j = 0; j < emptyList.size(); j++) {
        // System.out.println("size" + emptyList.size() + "\n j = " + j);
        compAngle = from.getDirectionToward(emptyList.get(j));
        if (compAngle < smallest) {
          smallest = compAngle;
        }
      }
    }
    else if (!beansList.isEmpty()) {
      for (int j = 0; 1 < beansList.size(); j++) {
        compAngle = from.getDirectionToward(beansList.get(j));
        if (compAngle < smallest) {
          smallest = compAngle;
        }
      }
    }
    else {
      return 361;
    }
    int r = smallest + target;
    if (r > 360) {
      r = r - 360;
    }
    else if (r < 0) {
      r = 360 + r;
    }
    return r;
  }

  public ArrayList<Location> BeansList(Location from) {
    ArrayList<Location> beansList = getGrid().getOccupiedAdjacentLocations(from);

    ArrayList<Location> locs = getGrid().getOccupiedAdjacentLocations(from);

    for (Location L : locs) {
      if (getGrid().get(L) instanceof PsychicBean) {
        beansList.clear();
        beansList.add(L);
      }
    }
    return beansList;
  }

  /**
   * Randomly selects a neighbor and changes this critter's color to be the same as that neighbor's. If there are no
   * neighbors, no action is taken.
   */
  public void processActors(ArrayList<Actor> actors) {
    int n = actors.size();
    if (n == 0)
      return;
    int r = (int) (Math.random() * n);

    Actor other = actors.get(r);
    setColor(other.getColor());
  }

  /**
   * Turns towards the new location as it moves.
   */
  public void makeMove(Location loc) {
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
  }

  /**
   * @return
   */
  public Actor findOther() {
    ArrayList<Location> locList = getGrid().getOccupiedLocations();
    for (Location loc : locList) {
      if (getGrid().get(loc) instanceof PsychicGecko && !loc.equals(this.getLocation())) {
        setOtherLocation(loc);
        return getGrid().get(loc);
      }
    }
    return null;
  }

  public boolean findPath(Location from, Location to) {
    System.out.println("From" + from);
    System.out.println("To" + to);
    // System.out.println("Finding path");
    boolean found = false;
    // if (isNeighbor(from, to)) {
    // return true;
    // }
    // else {
    int r = from.getDirectionToward(to);
    // TODO: Change the two things they said they would

    int d = Direction(from, r);
    if (!(beanIsNeighboringSecondGecko(from, otherLocation))) {
      Location next = from.getAdjacentLocation(d);
      if (getGrid().get(next) instanceof PsychicBean) {
        getGrid().get(from).removeSelfFromGrid();
        InvisibleRock rock = new InvisibleRock(from);
        System.out.println("Placed Invis-Rock at " + from);
      }
      else {
        plantPhysicBean(next);
        System.out.println("Planted Bean at " + next);
        findPath(next, to);
        // found = findPath(next, to);
      }
      if (found = true) {
        System.out.println("found");
        // break;
      }

    }

    found = false;
    return found;
  }

  private void plantPhysicBean(Location next) {
    PsychicBean bean = new PsychicBean(next);
    bean.putSelfInGrid(getGrid(), next);

  }

  public boolean geckoIsNeighbor(Location loc1, Location loc2) {
    System.out.println("location checks geckoNeighbors" + loc1 + loc2);
    ArrayList<Location> locations = getGrid().getOccupiedAdjacentLocations(loc1);
    for (Location L : locations) {
      if (getGrid().get(L) instanceof PsychicGecko && !loc1.equals(this.getLocation())) {
        System.out.println("geckos are neighbors");
        return true;
      }
    }
    return false;
  }

  public boolean beanIsNeighboringSecondGecko(Location from, Location loc2) {
    System.out.println("location checks beanNeighbors gecko" + from + otherLocation);
    ArrayList<Location> locations2 = getGrid().getOccupiedAdjacentLocations(from);
    for (int i = 0; i < locations2.size();i++) {
      if(locations2.get(i) == otherLocation) {
        return true;
      }
    }
    return false;
    
  }

  public boolean isFlowerNeighbor(Location loc1) {
    ArrayList<Location> locations = getGrid().getValidAdjacentLocations(loc1);
    for (Location L : locations) {
      if (getGrid().get(L) instanceof Flower && !loc1.equals(this.getLocation())) {
        return true;
      }
    }
    return false;
  }

  private ArrayList<Location> getEmptyAdjacent(Location from) {

    return null;
  }

}

class PsychicBean extends Actor {
  private Location loc;

  public PsychicBean(Location L) {
    loc = L;
  }
}

class InvisibleRock {
  private Location loc;

  public InvisibleRock(Location L) {
    loc = L;
  }
}
