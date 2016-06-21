/** Roshan Koirala
    Dr. Cordova
    CSCI 4065
    04/13/2016
    Homework 8
    
    Program Description - A simulation Program to draw random samples from the probability distributions 
                         and find the chance of winning a game for a famous Dart Thrower
*/


// importing required utilities
import jdistlib.*;
import java.util.*;

/**
   Class  that uses Java Distribution library to draw random sample and calculate the average
   distance missed by a dart thower and his opponent and predict the result of the game
   
  */
public class KoiralaHW8{

   
   // Drawing random samples from the dritribution provided
   
   // For the Famous Dart Thrower
   private static Normal famousVerticalMiss = new Normal(0,0.25);
   private static Exponential famousHorizontalMiss = new Exponential(0.15);

   // For his opponent
   private static Uniform opponentVerticalMiss = new Uniform(0,0.2);
   private static Normal opponentHorizontalMiss = new Normal(0,0.3);


/**
   Main method to calculate the average missed distance and number of matches won by both the players
 */
   public static void main(String[] args){
           
      final int n = 9000;
      
      int counter = 0;  // counter for 9000 throws for each player
      
      // Creating Array object to store missed distance in each throw for each player
      double[] totalMissFamous = new double[n];
      double[] totalMissOpponent = new double[n];
      
     // variables to store the total number of wins for each player 
      int famousGameWin = 0; 
      int opponentGameWin = 0; 
      
      // Loop to instanstiate the Arrays with missed distance of each throw
      for (int i = 0; i < 1000; ++i)       // For 1000 iterations of game
      {
         int famousThrowWin = 0;
         int opponentThrowWin = 0; 
         
         for ( int j = 0; j < 9; ++j)    // For nine throws in each game
         {
         
            // Calculating and storing the resultant missed distance of each throw for the famous player
            
            double vMissF = famousVerticalMiss.random();
            double hMissF = famousHorizontalMiss.random();
            double resultantMissFamous = Math.sqrt((vMissF*vMissF)+(hMissF*hMissF));
            totalMissFamous[counter] = resultantMissFamous;
         
         
           // Calculating and storing the resultant missed distance of each throw for his chief opponent
            double vMissO = opponentVerticalMiss.random();
            double hMissO = opponentHorizontalMiss.random();
            double resultantMissOpponent = Math.sqrt((vMissO*vMissO)+(hMissO*hMissO));
            totalMissOpponent[counter]= resultantMissOpponent;
         
            counter++;
         
            // Calculating the number of Throws won by each player in a game
            if( resultantMissFamous < resultantMissOpponent )
            {
               famousThrowWin++;
            }
            else
            {
            opponentThrowWin++;
            }
          }
         
          // Calculating the number of games won by each player in a tournament

         if ( famousThrowWin > opponentThrowWin )
         {
            famousGameWin++;
         }
         else{
         opponentGameWin++;
         }
                         
      }
      
            
      // Calculating Mean and Standard deviation for the Famous Player
      double famousMean = StdStats.mean(totalMissFamous);  
      double famousStdDev = StdStats.stddev(totalMissFamous); 
      
      // Calculating Mean and Standard deviation for his chief opponent
      double opponentMean = StdStats.mean(totalMissOpponent);
      double opponentStdDev = StdStats.stddev(totalMissOpponent); 
      
      
      // Displaying the average distance missed with 95% confidence interval
      System.out.printf("After 1000 matches, average total distance missed by the Famous Dart thrower, expressed as 95 pct confidence Interval: %4.2f +/- %4.4f\n", famousMean, 1.96 * famousStdDev/Math.sqrt(n));
      System.out.printf("After 1000 matches, average total distance missed by his chief Opponent, expressed as 95 pct confidence Interval: %4.2f +/- %4.4f\n", opponentMean, 1.96 * opponentStdDev/Math.sqrt(n));
      System.out.println();
      
      // Displaying the number of games won by each player in a tournament of 1000 games
      System.out.println("The number of matches won by the Famous Dart thrower is " + famousGameWin);
      System.out.println("The number of matches won by his chief opponent is " + opponentGameWin);
       
   } // End of class
  
}


/**
 Summary -   With the original technique applied, the opponent of the famous player always beats the famous player in a tournament of thousand matches.
             The opponent's average missed distance is .1 inche less than that of the famous player in the run of 1000 games. 
             In a tournament of 1000, the winning percentage for the famous player is about 45 %
            
 
 
 Recommendation- By altering the technique, the average distance missed by both the opponent is equal(.28 inches)  with 95 confidence interval. 
                  This is better than the previous technique. Also, the winning percentage is slighly better than in the original technique.
                  Hence, I recommend altering the technique to improve the overall chance winning the game. 
  
  
 Explanation - The average distance missed by the Famous player originally was 0.29 inches, which lowered to 0.28 
               when the techique was changed. Also the winning percentage is more of 50- 50 % by changing the technique which earlier was 45-55% for the famous player and his opponent. 
               The changes are very litte but it increases the winning chance for the famous player in a long run. 

*/