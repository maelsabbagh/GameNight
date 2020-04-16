/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;
import java.util.Random; 
/**
 *
 * @author Mohamed Amr
 */
public class Dice
{
 int number;
 Random rand;
 Dice()
 {
     number =0;
     rand = new Random();
 }
 public int throw_dice()
 {
     number= rand.nextInt(5)+1;
     return number;
 }
}

