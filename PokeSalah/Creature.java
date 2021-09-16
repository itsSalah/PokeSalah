//-------------------------------------------------------
//Assignment 4 Class
//Written by: Salahddin Warid (40191626)
//For COMP 248 Section UB Winter 2021
//--------------------------------------------------------

//*****************************************************************************
//Date: 19 April 2021
//Class: Creature class that contains all the methods that are going to 
//be used for our PokeSalah Battle Game.
//*****************************************************************************

import java.util.*;

public class Creature {
	
	//Declaring all static data members
	private static int FOOD2HEALTH=6;
	private static int HEALTH2POWER=4;
	private static int numStillAlive=0;
	//Declaring all private variables
	private String name;
	private int foodUnits;
	private int healthUnits;
	private int firePowerUnits;
	private int foodearned;
	private Date dateCreated;
	private Date dateDied;
	
	//Constructor for the class taking the name of the creature as parameter
	public Creature(String name) {
		numStillAlive += 1;
		this.name = name;
		//Generate a random food unit between 1 and 12
		this.foodUnits = 1 + (int)(Math.random()*((12-1)+1));
		//Generate a random health unit between 1 and 7
		this.healthUnits = 1 + (int)(Math.random()*((7-1)+1));
		//Generate a random fire power units between 0 and 10
		this.firePowerUnits = 0 + (int)(Math.random()*((10-0)+1));
		//Normalize the stats
		this.normalizeUnits();
		//Create a date for when the object is initialized
		this.dateCreated = new Date();
		this.dateDied = null;
	}
	
	//Public mutator
	public void setName(String newName) {
		this.name = newName;
	}
	
	//Public mutator
	public void setHealthUnit(int n) {
		this.healthUnits = n;
	}
	
	//Public mutator
	public void setFoodUnit(int n) {
		this.foodUnits = n;
	}
	
	//Public mutator
	public void reduceFirePowerUnits(int n) {
		this.firePowerUnits -= n;
	}
	
	//Public accessor
	public String getName() {
		return this.name;
	}
	
	//Public accessor
	public int getFoodUnits() {
		return this.foodUnits;
	}
	
	//Public accessor
	public int getHealthUnits() {
		return this.healthUnits;
	}
	
	//Public accessor
	public int getFirePowerUnits() {
		return this.firePowerUnits;
	}
	
	//Public accessor
	public Date getDateCreated() {
		return this.dateCreated;
	}
	
	//Public accessor
	public Date getDateDied() {
		return this.dateDied;
	}
	
	//Public accessor
	public static int numStillAlive() {
		return numStillAlive;
	}
	
	//Public accessor
	public boolean isAlive() {
		return this.dateDied==null;
	}
	
	
	//Generate a random amount of food between 0 and 15 for the creature.
	public void earnFood() {
		//Store the generated number for later in our driver.
		this.foodearned = (int)(Math.random()*((15-0)+1));
		this.foodUnits += foodearned;
		//Normalize the stats
		this.normalizeUnits();
	}
	
	//Public accessor
	public int getfoodearned() {
		return this.foodearned;
	}
	
	//This method serve 2 purposes.
	//1. Determine if a creature is dead (0 health and food)
	//2. Mark the creature as dead and generate the dateDied 
	//using the died() method and remove it from the number
	//of creature still alive in the game (only applied when this method returns
	//true for the first time so we don't overwrite the dateDied everytime we call this method).
	
	public boolean healthFoodUnitsZero() {
		if ((this.healthUnits==0)&&(this.foodUnits==0)) {
			//If the creature is dead then we verify if we marked it as dead already or not
			//if it was still marked as alive then we mark it as dead (and give it a dateDied)
			//and we remove the creature from the number of creatures still alive.
			
			if(this.isAlive()==true) {
				died();
				numStillAlive-=1;
			}
			
			return true;
		}
		else
			return false;
	}
	
	//Method for a creature to attack another creature. The attacking creature loses 2 fire power units
	//for initiation of the attack then steals half of the health units/food units of the attacked creature 
	//(rounded up).
	public void attacking(Creature player) {
		this.foodUnits += Math.ceil(player.foodUnits/2.0);
		this.healthUnits += Math.ceil(player.healthUnits/2.0);
		this.firePowerUnits -= 2;
		//Normalize the stats of the attacking creature
		this.normalizeUnits();
		player.foodUnits -= Math.ceil(player.foodUnits/2.0);
		player.healthUnits -= Math.ceil(player.healthUnits/2.0);
		//Check if the target is dead
		player.healthFoodUnitsZero();
	}
	
	//Method to enter a dateDied when a creature dies
	private void died() {
		this.dateDied = new Date();
		this.isAlive();
	}
	
	//Show the informations about the object
	public String toString() {
		return "Name: "+name+", food units: "+foodUnits+", health units: "+healthUnits
				+ ", firepower units: "+firePowerUnits+", date created: "+ dateCreated
				+", date died: "+dateDied;
	}
	
	//Show the status 
	public String showStatus() {
		return foodUnits+" food units, "+healthUnits+" health units,"+'\n'
			   + firePowerUnits + " fire power units";
	}
	
	//Private method to normalize the stats of the creature
	private void normalizeUnits() {
		//If the user has more than 6 (or equal) food we convert to health
		if (this.foodUnits/FOOD2HEALTH > 0)
		{	
			//We take the integer division by 6 to determine how many
			//stacks of 6 food the creature has to convert in health.
			this.healthUnits += this.foodUnits/FOOD2HEALTH;
			//Remove the food units converted to health
			this.foodUnits -= FOOD2HEALTH*(this.foodUnits/FOOD2HEALTH);
		}
		//If the user has more than 4 (or equal) health we convert to fire power
		if (this.healthUnits/HEALTH2POWER>0) 
		{	
			//Same principle as above.
			this.firePowerUnits += this.healthUnits/HEALTH2POWER;
			this.healthUnits -= HEALTH2POWER*(this.healthUnits/HEALTH2POWER);
		}	
	}
	
}
