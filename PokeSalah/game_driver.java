//-------------------------------------------------------
//Assignment 4 Class
//Written by: Salahddin Warid (40191626)
//For COMP 248 Section UB Winter 2021
//--------------------------------------------------------

//*****************************************************************************
//Date: 19 April 2021
//Driver: Driver code used to play the PokeSalah Battle Game. Contains all
//the visual interface and game code.
//*****************************************************************************

import java.util.*;

public class game_driver {

	public static void main(String[] args) {
		
		//Introduction message
		System.out.println("----------------------------------------------------");
		System.out.println("     Welcome to PokeSalah Battle Game               ");
		System.out.println("----------------------------------------------------");
		
		//Initialize the scanner and declare the variables.
		Scanner keyIn = new Scanner(System.in);
		int n_creatures, current,option,target,attack_rnd; 
		String name;
		boolean target_dead=false;
		
		//Prompts the user to enter a valid number of creatures to play with.
		//Repeats until we have a valid input.
		do
		{
			System.out.print("How many creatures would you like to have (minimum 2, maximum 8)? ");
			n_creatures  = keyIn.nextInt();
			if (n_creatures<2 || n_creatures >8) {
			System.out.println("*** Illegal number of creatures requested ***");
			}
		}while(n_creatures<2 || n_creatures >8);
		
		//Consume the next line in the buffer.
		keyIn.nextLine();
		
		//Create an array to store the requested amount of creatures by the user.
		Creature[] Board = new Creature[n_creatures];
		
		//Loop to ask the user for the name of each creature, initialize them and display their
		//infos.
		
		for(int i=0;i<Board.length;i++) 
		{
			System.out.print("What is the name of creature "+(i+1)+"? ");
			name = keyIn.nextLine();
			Board[i] = new Creature(name);
			System.out.println();
			System.out.println("Food units"+'\t'+"Health units"+'\t'+"Fire power units"+'\t'+"Name");
		    System.out.println("_ _ _ _ _ "+'\t'+"_ _ _ _ _ _ "+'\t'+"_ _ _ _ _ _ _ _ "+'\t'+"_ _ _");
			System.out.println(Board[i].getFoodUnits()+"	 	"+Board[i].getHealthUnits()
								+"	 	"+Board[i].getFirePowerUnits()
								+"                       "+Board[i].getName());
			System.out.println("Date Created: "+Board[i].getDateCreated());
			System.out.println("Date died: "+
							  (Board[i].getDateDied()==null ? "is still alive":Board[i].getDateDied()));
			System.out.println();
		}
		
		
		//Generate an index between 0 and n_creatures-1 (index in our array Board) to determine
		//which creature is starting to play.
		current = (int)(Math.random()*(n_creatures));
		
		//Loop for the game that goes on until there is only 1 creature left.
		do {
			
		//Loop for the current player taking his turn.
		do {
		
			//Print the current options for the player.
			//Prompt the user to select a valid option
			do {
				System.out.println("Creature #"+(current+1)+": "+Board[current].getName()
					   +", what do you want to do?");
				System.out.println("\t"+"1. How many creatures are alive?");
				System.out.println("\t"+"2. See my status");
				System.out.println("\t"+"3. See status of all players");
				System.out.println("\t"+"4. Change my name");
				System.out.println("\t"+"5. Work for some food");
				System.out.println("\t"+"6. Attack another creature (Warning! may turn against you)");
				System.out.println();
				System.out.print("Your Choice please > ");
				option = keyIn.nextInt();
				//Consume the buffer again.
				keyIn.nextLine();
				System.out.println();
			}while(option!=1&&option!=2&&option!=3&&option!=4&&option!=5&&option!=6);
			
			
			//Option displays the number of creatures still alive.
			if (option==1)
			{
				System.out.println("\t"+"Number of creature alive "+Creature.numStillAlive());
				System.out.println();
			}
			
			//Option to display only the info of the creature taking turn.
			else if (option==2)
			{
				System.out.println("Food units"+'\t'+"Health units"+'\t'+"Fire power units"+'\t'+"Name");
			    System.out.println("_ _ _ _ _ "+'\t'+"_ _ _ _ _ _ "+'\t'+"_ _ _ _ _ _ _ _ "+'\t'+"_ _ _");
				System.out.println(Board[current].getFoodUnits()+"	 	"+Board[current].getHealthUnits()
									+"	 	"+Board[current].getFirePowerUnits()
									+"                       "+Board[current].getName());
				System.out.println("Date Created: "+Board[current].getDateCreated());
				System.out.println("Date died: "+
								  (Board[current].getDateDied()==null ? "is still alive":Board[current].getDateDied()));
				System.out.println();
			}
			
			//Option to display the info of all the creatures in the board.
			else if (option==3) 
			{
				for(int i=0;i<Board.length;i++) 
				{
					System.out.println("Food units"+'\t'+"Health units"+'\t'+"Fire power units"+'\t'+"Name");
				    System.out.println("_ _ _ _ _ "+'\t'+"_ _ _ _ _ _ "+'\t'+"_ _ _ _ _ _ _ _ "+'\t'+"_ _ _");
					System.out.println(Board[i].getFoodUnits()+"	 	"+Board[i].getHealthUnits()
										+"	 	"+Board[i].getFirePowerUnits()
										+"                       "+Board[i].getName());
					System.out.println("Date Created: "+Board[i].getDateCreated());
					System.out.println("Date died: "+
									  (Board[i].getDateDied()==null ? "is still alive":Board[i].getDateDied()));
					System.out.println();
				}
			}
			
			//Option to change the name of the creature taking turn.
			else if (option==4) 
			{
				System.out.println("Your name is currently "+"\""+Board[current].getName()+"\"");
				System.out.print("What is the new name: ");
				name = keyIn.nextLine();
				Board[current].setName(name);
				System.out.println();

			}
			//Option for the creature to earn food instead of attacking and display the status changes
			else if (option==5) 
			{	
				System.out.print("Your status before working for food: "+Board[current].getFoodUnits()
						+" food units, "+Board[current].getHealthUnits()+" health units, "
						+Board[current].getFirePowerUnits()+" fire power units ...");
				Board[current].earnFood();
				System.out.println(" You earned "+Board[current].getfoodearned()+" food "
								 + ((Board[current].getfoodearned()>1) ? "units.":"unit." ));
				System.out.print("Your status after working for food: "+Board[current].getFoodUnits()
						+" food units, "+Board[current].getHealthUnits()+" health units, "
						+Board[current].getFirePowerUnits()+" fire power units.");
				System.out.println();
				System.out.println();
			}
			
			//Option for the creature to attack another creature.
			else if (option==6)
			{
				//Loop to keep prompting the user to enter the index of an alive creature (other than itself)
				do {
				System.out.print("Who do you want to attack? (enter a number from 1 to "+n_creatures
						+ " other than yourself("+(current+1)+"): ");
				//Take the user input and convert it to an array index (for our array Board).
				target = keyIn.nextInt()-1;
				System.out.println();
				//Initialize a boolean to false (supposing the target is alive).
				target_dead = false;
				
				//Make sure the user wants to attack a creature with an existing index
				if (target>n_creatures-1 || target<0) {
					System.out.println("The creature does not exist. Try again ...");
					System.out.println();
				}
				//Make sure the user doesn't attack himself
				else if (target==current) {
					System.out.println("You can't attack yourself dummy ! Try again ...");
					System.out.println();
				}
				//Make sure the creature doesn't attack a dead target.
				else if (Board[target].healthFoodUnitsZero()) {
					System.out.println("Attacking a dead target... have mercy ! Try again ...");
					System.out.println();
					//Indicate that the target is dead:
					target_dead = true;
				}
				
				//In the rare case that earnfood would make the creature kill itself 
				//the previous else if would mark it as dead when targeted then we would leave the loop
				//in the next step.
				else if (Creature.numStillAlive()==1) {
					break;
				}
				
				//Else means that the creature being targeted is a valid target.
				else 
				{	
					//Generate a number from 0,1,2
					attack_rnd = (int)(Math.random()*(3));
					//Here is the 1/3 that the creature ends up being attacked by their target.
					if (attack_rnd == 0)
					{	
						System.out.println("You tried to attack "+Board[target].getName()+" but he ended up trying to attack you instead...");
						
						//If the targeted creature as enough fire power to attack back then it does its attack (and we display status).
						//We display the status of the player taking turn after getting attacked (and prior).
						
						if(Board[target].getFirePowerUnits()>=2) 
						{   
							System.out.println(Board[target].getName()+"'s attack was super dupper effective !");
							System.out.println("Your status before getting attacked: "+Board[current].getFoodUnits()
							+" food units, "+Board[current].getHealthUnits()+" health units, "
							+Board[current].getFirePowerUnits()+" fire power units.");
						
							Board[target].attacking(Board[current]);
							
							System.out.println("Your status after getting attacked: "+Board[current].getFoodUnits()
							+" food units, "+Board[current].getHealthUnits()+" health units, "
							+Board[current].getFirePowerUnits()+" fire power units.");
							
							//Check if the creature getting attacked is dead. If it is then output a message to the player.
							if(Board[current].healthFoodUnitsZero()) 
							{
								System.out.println();
								System.out.println("----->"+Board[current].getName()+" has fainted.");
								System.out.println();
							}
							
						}
						else
							//In the case that the creature reverting the attack doesn't have enough fire power to attack
							//we simply out put the following message
							System.out.println(Board[target].getName()+"'s fire power was too low to attack you. Lucky!");
						
						System.out.println();
					}
					
					//If the creature proceed to attack and has enough fire power to do so. "Attack normally"
					//Showing the status of the creature taking turn as usual.
					else if (attack_rnd != 0 && Board[current].getFirePowerUnits()>=2) 
					{
						System.out.println("..... You are attacking "+Board[target].getName()+"!");
						System.out.println("Your status before attacking: "+Board[current].getFoodUnits()
						+" food units, "+Board[current].getHealthUnits()+" health units, "
						+Board[current].getFirePowerUnits()+" fire power units.");
						
						Board[current].attacking(Board[target]);
						
						System.out.println("Your status after attacking: "+Board[current].getFoodUnits()
						+" food units, "+Board[current].getHealthUnits()+" health units, "
						+Board[current].getFirePowerUnits()+" fire power units.");
						System.out.println();

						//Check if the target being attacked is dead, if it is then prompt it to the user.
						if(Board[target].healthFoodUnitsZero()) 
						{	
							System.out.println();
							System.out.println("----->"+Board[target].getName()+" has fainted.");
							System.out.println();
						}
					}
					
					//Last case scenario, the targeted creature attacks the attacker because the attacker doesn't have 2 or more fire power
					//to initiate the attack.
					else if (attack_rnd != 0 && Board[current].getFirePowerUnits()<2)
					{
						System.out.println("That was not a good idea ... you only had "+Board[current].getFirePowerUnits()+" Fire Power units !!!");
						
						//If the targeted creature also doesn't have enough fire power units to attack back
						//then nothing happens we only prompts the user and we move on.
						if (Board[target].getFirePowerUnits()<2) 
						{
							System.out.println("Lucky you, the odds were that the other player attacks you, but "+Board[target].getName()+" doesn't"
									+" have enough fire power to attack you! So is status quo!!");
							System.out.println();

						}
						
						//If the targeted creature has enough fire power then it attacks you back.
						else 
						{
							System.out.println("....... Oh No!!! You are being attacked by "+Board[target].getName()+"!");
							System.out.println("Your status before getting attacked: "+Board[current].getFoodUnits()
							+" food units, "+Board[current].getHealthUnits()+" health units, "
							+Board[current].getFirePowerUnits()+" fire power units.");
						
							Board[target].attacking(Board[current]);
							
							System.out.println("Your status after getting attacked: "+Board[current].getFoodUnits()
							+" food units, "+Board[current].getHealthUnits()+" health units, "
							+Board[current].getFirePowerUnits()+" fire power units.");
							System.out.println();

							//Check if the attacked creature is dead and prompt it to the user if it is.
							if(Board[current].healthFoodUnitsZero()) 
							{	
								System.out.println();
								System.out.println("----->"+Board[current].getName()+" has fainted.");
								System.out.println();
							}
						}
					}
				
				}
			//As mentioned above keep prompting the user for a valid choice of target.
			}while(target>n_creatures-1 || target<0 || target==current || target_dead);
			//End of the option 6.
			}
		//The creature can repeat its turn if it choices between option 1 and 4.
		}while(option==1 || option == 2 || option ==3 || option ==4);
		
		//If there is more than 1 creature alive determine the next player taking turn.
		if(Creature.numStillAlive()>1) 
		{
			
			do {
				//If the next index is out of bound then we go back to the first index.
				if (current+1>n_creatures-1) 
				{
					current = 0;
				}
				//Else we simply go to the next index.
				else
					current += 1;
				//Repeat until we find a creature that is alive.
				}while(!Board[current].isAlive());
		}
		
		//Loop to stay in the game as long as there is more than one creature alive.
		}while(Creature.numStillAlive()>1);
		
		//When there is only one creature alive we output a game over message with the status
		//of every creature that was in the game.
		
		System.out.println("GAME OVER!!!!");
		System.out.println();
		
		//Print the status of all creatures.
		for(int i=0;i<Board.length;i++) 
		{
			System.out.println("Food units"+'\t'+"Health units"+'\t'+"Fire power units"+'\t'+"Name");
		    System.out.println("_ _ _ _ _ "+'\t'+"_ _ _ _ _ _ "+'\t'+"_ _ _ _ _ _ _ _ "+'\t'+"_ _ _");
			System.out.println(Board[i].getFoodUnits()+"	 	"+Board[i].getHealthUnits()
								+"	 	"+Board[i].getFirePowerUnits()
								+"                       "+Board[i].getName());
			System.out.println("Date Created: "+Board[i].getDateCreated());
			System.out.println("Date died: "+
							  (Board[i].getDateDied()==null ? "is still alive":Board[i].getDateDied()));
			System.out.println();
		}
		
		//Close the scanner and the program is done.
		keyIn.close();
	}

}
