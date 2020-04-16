/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

/**
 *
 * @author Mohamed Amr
 */
import java.util.Vector;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Play  extends BasicGameState
{
    
     ludoboard b ;
    LudoPlayer players[];
    Piece p;
    int number_of_players;
    Dice dice;
    private String mouse=" start";
    char[] colors = {'Y','R','G','B'};
    int arr_x[];
    int arr_y[];
    String winner= "NONE";
    int m,n;
   int ii,jj,kk;
   String s;
   Image im;
    public Play(int state,int n)throws SlickException 
    {
        number_of_players= n; 
        arr_x = new int[16];
      arr_y = new int[16];
      //players = new LudoPlayer('R');
     /* for(int i=0;i<n;i++)
      {
          players[i]=new LudoPlayer(colors[i]);
      }*/
      
      arr_x[0]=270;arr_x[1]=365;arr_x[2]=365;arr_x[3]=270; // yellow x
      arr_y[0]=735;arr_y[1]=735;arr_y[2]=642;arr_y[3]=642; // yelllow Y
      
      arr_x[4]=705;arr_x[5]=798;arr_x[6]=798;arr_x[7]=705; // Red X
      arr_y[4]=295;arr_y[5]=295;arr_y[6]=200; arr_y[7]=200; // Red Y    
      
      arr_x[8]=270;arr_x[9]=365;arr_x[10]=365;arr_x[11]=270; // Green X
      arr_y[8]=295;arr_y[9]=295;arr_y[10]=200;arr_y[11]=200; // Green Y
      
      arr_x[12]=705;arr_x[13]=798; arr_x[14]=798;arr_x[15]=705; //Blue x
      arr_y[12]=735;arr_y[13]=735;arr_y[14]=642;arr_y[15]=642; // blue Y


        /*
          y_1=new Coordinates(270,735);
        y_2=new Coordinates(365,735);
        y_3=new Coordinates(365,642);
        y_4=new Coordinates(270,642);
         b_1=new Coordinates(705,735);
        b_2=new Coordinates(798,735);
        b_3=new Coordinates(798,642);
        b_4=new Coordinates(705,642);
        
        g_1=new Coordinates(270,295);
        g_2=new Coordinates(365,295);
        g_3=new Coordinates(365,200);
        g_4=new Coordinates(270,200);
        
        r_1=new Coordinates(705,295);
        r_2=new Coordinates(798,295);
        r_3=new Coordinates(798,200);
        r_4=new Coordinates(705,200);
        */
                              
       
       
      
       
     
      
    }
    @Override
    public int getID()
    {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
      //  b.mousePositions();
        //b.cells();
        
        
         
        
           b = new ludoboard();
           b.cells();
           b.mousepositions();
           b.yellowpath();
           b.redpath();
           b.bluepath();
           b.greenpath();
          players = new LudoPlayer[number_of_players];
         for(int i=0;i<number_of_players;i++)
        {
            players[i] = new LudoPlayer(colors[i]);
        }
         int x=0;
         for(int i=0;i<number_of_players;i++)
         {
             for(int j=0;j<4;j++)
             {
                players[i].pieces[j].id=x;
                x++;
             }
         }
         for(int i=0;i<92;i++)
         {
             b.cell_pieces[i].clear();
         }
       for(int i=0;i<16;i++)
       {
           b.cell_pieces[i].add(i);
       }
            
          p = new Piece('R', 0);
          m=arr_x[0];n=arr_y[0];
         // ii=0;jj=0;kk=0;
       /*   
           for(int i=0;i<b.full_mouse_path_x.size();i++)
        {
            System.out.println(b.full_mouse_path_x.get(i) + " " +b.full_mouse_path_y.get(i));
            
        }
          */
          
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
    {
        im = new Image("D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Green_piece_5050.png");
        grphcs.drawString(mouse, 150, 10);
       //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Red_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Yellow_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Blue_piece_5050.png"
        //"D:\\Net Beans\\Ludo\\build\\classes\\ludo\\Green_piece_5050.png"
       
       // Image board=new Image("D:\\Net Beans\\Ludo\\build\\classes\\ludo\\ludo.PNG");
       // grphcs.drawImage(board, 175, 125);
        grphcs.drawImage(b.board_image, 175, 125);
       // players[ii].pieces[jj].piece_image.draw(arr_x[kk],arr_y[kk]);
       
        int k=0;
          for(int i=0;i<number_of_players;i++)
        {
            for(int j=0;j<4;j++)
            {
             //players[i].board=new board();
              //  players[i].board.cells();
               players[i].pieces[j].piece_image.draw(arr_x[k],arr_y[k]);
               //playes[i].pieces[j].cells = -k;
               
               k++;
               
            }
        }
        
        
       
           
     
       
       
      
       
              //   p.piece_image.draw(m, n);
            
        
        
    }
  

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
       
        int xpos=Mouse.getX();
        int ypos=Mouse.getY();
        mouse=" xpos= "+xpos+" ypos= "+ypos;
      Input inp = gc.getInput();
      
      char turn='Y';
      char step=3;
      int src_cell_indexi=-1;
      int targ_cell_indexi=-1;
      int piece_id=-1;
      int factor=25;
      for(int j=0;j<b.full_mouse_path_x.size();j++)
      {
    
          
          float cmpx = b.full_mouse_path_x.get(j);
          float cmpy = b.full_mouse_path_y.get(j);
          float xmin = cmpx-25,xmax=cmpx+25,ymin=cmpy-25,ymax=cmpy+25;
          if(xpos>=xmin&&xpos<=xmax &&ypos>=ymin &&ypos<=ymax&&Mouse.isButtonDown(0)) 
          {
              Mouse.setCursorPosition(1050, 800);
               src_cell_indexi=j;// get cell index in the full path
          }
      }
              if(turn=='Y' || turn=='y')
              {
                  if(src_cell_indexi<16 && src_cell_indexi!=-1)  // still out of the game
                  {
                      //get piece id
                      int k=-1;
                      for(k=0;k<b.cell_pieces[src_cell_indexi].size();k++)
                      {
                           piece_id = (int)b.cell_pieces[src_cell_indexi].get(k); //get piece id
                      
                          if(piece_id>=0 && piece_id<=3)    //found true
                          {
                             break; 
                          }
                      }
                        if(piece_id>=0 && piece_id<=3)    //found true
                          {
                            
                            int targ_x = b.YELLOWX[0];   // get positions of the target cell;
                            int targ_y = b.YELLOWY[0];
                            
                            int minx = targ_x-factor,maxx=targ_x+factor;
                            int miny = targ_y-factor,maxy=targ_y+factor;
                            
                            float posx=-1;
                            float posy=-1;
                            int z=-1;
                            for( z=0;z<92;z++)
                            {
                                 posx = (float)b.full_path_x.get(z);
                                 posy=(float) b.full_path_y.get(z);
                                 
                                // System.out.println("pos x  " + posx+ " " + "posY " +posy  );
                                
                                 if(posx>=minx &&posx<=maxx &&posy>=miny &&posy<=maxy )
                                 {
                                     targ_cell_indexi = z;
                                     
                                     break;
                                 }
                            }
                           // System.out.println(z);
                            if(true) //check the cell number
                            {
                               
                                b.cell_pieces[src_cell_indexi].remove(k);  //remove from piece from the sourc cell
                                b.cell_pieces[targ_cell_indexi].add(piece_id);//add piece to the target cell
                                arr_x[piece_id]=b.YELLOWX[0];
                                arr_y[piece_id]=b.YELLOWY[0];
                                // set target cell 
                                for(int ii=0;ii<4;ii++)
                                {
                                    if(players[0].pieces[ii].id ==piece_id)
                                    {
                                        players[0].pieces[ii].color_path_id = 0;
                                    }
                                }
                            }
                            else // not applicable -> safe zone
                            {
                                
                            }
                            
                          }
                      
                      
                      
                      
                          
                      
                  }
                  else
                  {
                       //get piece id
                      if(src_cell_indexi!=-1)
                      {
                      int k=-1;
                      for(k=0;k<b.cell_pieces[src_cell_indexi].size();k++)
                      {
                           piece_id = (int)b.cell_pieces[src_cell_indexi].get(k); //get piece id
                           System.out.println(piece_id);
                      
                          if(piece_id>=0 && piece_id<=3)    //found true
                          {
                              //System.out.println("piece id " + piece_id);
                             break; 
                          }
                      }
                      if(piece_id>=0 &&piece_id<=3)
                      {
                        int curr_index;
                        int targ_index=-1;
                        
                          for(int ii=0;ii<4;ii++)  // get piece current location on the colored path
                          {
                               //System.out.println("Players[0].pieces id : " +players[0].pieces[ii].id );
                              if(players[0].pieces[ii].id==piece_id)
                              {
                               
                                  curr_index = players[0].pieces[ii].color_path_id;
                                  targ_index=curr_index +step;
                                //  System.out.println("curr_index on the full path" + curr_index );
                                  
                                  break;
                              }
                          }
                          if(targ_index!=-1)
                          {
                          int targ_x = b.YELLOWX[targ_index];
                          int targ_y = b.YELLOWY[targ_index];
                          int minx = targ_x-factor,maxx=targ_x+factor;
                          int miny = targ_y-factor,maxy=targ_y+factor;
                          
                            float posx=-1; // pos x in the full path
                            float posy=-1; //pos y in the full path
                            int z=-1;
                            for( z=0;z<92;z++)
                            {
                                 posx = (float)b.full_path_x.get(z);
                                 posy=(float) b.full_path_y.get(z);
                                
                                 if(posx>=minx &&posx<=maxx &&posy>=miny &&posy<=maxy )
                                 {
                                     targ_cell_indexi = z;
                                     
                                     break;
                                 }
                            }
                            if(false) // attack condition
                            {
                                
                            }
                            else
                            {
                              b.cell_pieces[src_cell_indexi].remove(k);  //remove from piece from the sourc cell
                               b.cell_pieces[targ_cell_indexi].add(piece_id);//add piece to the target cell
                                
                                
                                arr_x[piece_id]=b.YELLOWX[targ_index];
                                arr_y[piece_id]=b.YELLOWY[targ_index]; 
                               
                                for(int ii=0;ii<4;ii++)
                                {
                                    if(players[0].pieces[ii].id ==piece_id)
                                    {
                                       
                                        players[0].pieces[ii].color_path_id = targ_index;
                                    }
                                }
                                
                            }
                          }
                            
                            
                          
                          }
                      }
                      
                  }
              }
             
          
      
      
      
    /*  if((xpos >=435 &&xpos<= 535 )&&(ypos>=705 &&ypos<=805)&&Mouse.isButtonDown(0))
      {
         
      }
      */
      }
     /* if(inp.isKeyDown(inp.KEY_UP))
      {
          m = 485;
          n=755;
      }
      
          
       
        
        
    }
 */
    
    
 
}

