import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 * @authors Kyle Lawson -- Joaquin Castro
 * References: Chris Santella and Jessie Willard 
 * online references: http://apcentral.collegeboard.com/apc/public/repository/GridWorld_Case_Study_Student_Manual_with_Appendixes_Aug_2007_updated.pdf
 * other References: Stack Overflow
 */
public class PsychicGecko extends Critter {
  ArrayList<Location>  nextSetLocs;
  Flower               flower      = new Flower();
  PsychicBean          psychicBean = new PsychicBean();
  ActorWorld           world       = new ActorWorld();
  private Location     nextLocation;
  private PsychicGecko otherGecko;
  private int          angle;

  public void act() {     //main driver
    otherGecko = findOther();
    if (isNeighbor(getLocation(), otherGecko.getLocation())) {     //if geckos are nextLocation to eachother
      return;
    }
    else if (isNeighboringFlower(getLocation())) {
      nextLocation = getNeighboringFlower(getLocation());
      eatFlower(nextLocation);
      moveTo(nextLocation);
    }
    else {
      findPath(getLocation(), otherGecko.getLocation());
      ArrayList<Location> totalPath = getGrid().getOccupiedLocations();
      for (Location loc : totalPath) {
        if (getGrid().get(loc) instanceof PsychicBean) {
          getGrid().put(loc, new Flower());
        }
      }
    }
  }

  private boolean findPath(Location from, Location to) {
    boolean found = false;
    if (isNeighbor(from, to)) {
      return true;
    }
    else {
      angle = getDirection(from, to);
      nextSetLocs = getGrid().getEmptyAdjacentLocations(from);
      nextSetLocs = sortDirections(from, angle, nextSetLocs);
      for (Location loc : nextSetLocs) {
        nextLocation = loc;
        plantBean(nextLocation);
        found = findPath(nextLocation, to);
        if (found) {
          break;
        }
        removeBean(nextLocation);
      }
      return found;
    }

  }

  private ArrayList<Location> sortDirections(Location from, int angle, ArrayList<Location> orderedList) {
    Location temp = null;
    for (int i = 0; i < orderedList.size(); i++) {

      for (int j = i; j > 0; j--) {

        if (Math.abs(from.getDirectionToward(orderedList.get(j - 1)) - angle % 360) > Math.abs(from
            .getDirectionToward(orderedList.get(j)) - angle % 360)) {

          temp = orderedList.get(j);

          orderedList.set(j, orderedList.get(j - 1));

          orderedList.set(j - 1, temp);
        }
      }
    }
    return orderedList;
  }

  private int getDirection(Location from, Location to) {
    return from.getDirectionToward(to);
  }

  private PsychicGecko findOther() {
    PsychicGecko otherGecko = null;
    ArrayList<Location> locations = getGrid().getOccupiedLocations();
    for (Location loc : locations) {
      if (loc.equals(getLocation())) {}
      else {
        if (getGrid().get(loc) instanceof PsychicGecko) {
          otherGecko = (PsychicGecko) getGrid().get(loc);
          return otherGecko;
        }
      }
    }
    return null;
  }

  private boolean isNeighbor(Location location, Location location2) {
    ArrayList<Location> locations = getGrid().getOccupiedAdjacentLocations(location);
    for (int i = 0; i < locations.size(); i++) {
      if (locations.get(i).equals(location2)) {
        return true;
      }
    }

    return false;
  }

  private void plantBean(Location beanLoc) {
    getGrid().put(beanLoc, new PsychicBean());
  }
  
  private Location getNeighboringFlower(Location location) {
    ArrayList<Location> locations = getGrid().getOccupiedAdjacentLocations(location);
    for (Location loc : locations) {
      {
        if (getGrid().get(loc) instanceof Flower) {
          return loc;
        }
      }
    }
    return null;
  }

  private boolean isNeighboringFlower(Location location) {
    ArrayList<Location> locations = getGrid().getOccupiedAdjacentLocations(location);
    for (Location L : locations) {
      if (getGrid().get(L) instanceof Flower) {
        return true;
      }
    }
    return false;
  }
  
  private void removeBean(Location beanLoc) {
    getGrid().remove(beanLoc);
  }
  
  private void eatFlower(Location nextLoc2) {
    getGrid().remove(nextLoc2);
    return;
  }

}

// ----------------------------------------------------------

class PsychicBean extends Actor {
  Location beanLocation;
  public PsychicBean() {
  }
}
