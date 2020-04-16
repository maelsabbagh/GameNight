/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ludo;
import java.util.Scanner;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Mohamed Amr
 */
public class Ludo extends StateBasedGame{
    public static final String gamename="Game Night";
    public static final int play=0;
    public static final int Ludo_game=10,Ludo_classic=11;
    public static final int Snake_ladder_game=20;
    public static final int Monopoly_game=30;
    private Image board=null;
     Scanner sc = new Scanner(System.in);
/**
    /**
     * @param args the command line arguments
     */
     public Ludo(String name)throws SlickException
     {
        super(gamename);
         System.out.println("Enter the number of players");
         int number_of_player = sc.nextInt();
         this.addState(new Play(play,number_of_player));
     }
     public void initStatesList(GameContainer gc) throws SlickException
     {
         
         
         this.getState(play).init(gc, this);
         this.enterState(play);
     }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        
       
         // TODO code application logic here
//        ludoboard b = new ludoboard();
        AppGameContainer appgc;
        try
        {
            appgc =new AppGameContainer(new Ludo(gamename));
            appgc.setDisplayMode(1100, 1000, false); // (l,w,full screen)
            appgc.start();
        }
        catch(SlickException e)
        {
            e.printStackTrace();
        }
        
        // TODO code application logic here
    }
    
}
