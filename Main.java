import java.awt.*; 
import java.awt.event.*;
import java.util.concurrent.*;
public class Main extends Thread{
    public static void main(String[] args){
        Gui gui = new Gui();
        Gui.f.addKeyListener(new KeyListener(){
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()==KeyEvent.VK_W){
                gui.up();
                gui.draw();
            }else if(ke.getKeyCode()==KeyEvent.VK_S){
                gui.down();
                gui.draw();
            }else if(ke.getKeyCode()==KeyEvent.VK_A){
                gui.left();
                gui.draw();
            }else if(ke.getKeyCode()==KeyEvent.VK_D){
                gui.right();
                gui.draw();
            }else if(ke.getKeyCode()==KeyEvent.VK_R){
                //if unsolvable, the player can reset the game
                    System.out.println("Key R pressed");
                    Gui.randomize_player_goal();
                    Gui.draw();
			}else if(ke.getKeyCode()==KeyEvent.VK_Q){
                //quits the game
                    
                Gui.f.dispose();
			}else if(ke.getKeyCode()==KeyEvent.VK_Q){
                //quits the game
                    
                Gui.f.dispose();
			}else if(ke.getKeyCode()==KeyEvent.VK_L){
                //solve the puzzle
                System.out.println("A* Algorithm solving...");
                Gui.solve();
                try{
                        for(int i = 0; i < Solver.moves.size(); i++){
                            char m = Solver.moves.get(i);
                            
                            switch(m){
                                case 'w':
                                
                                    gui.up();
                                    gui.draw();
                        
                                    break;
                                case 'a':
                                    gui.left();
                                    gui.draw();
                                    break;
                                case 's':
                                    gui.down();
                                    gui.draw();
                                    break;
                                case 'd':
                                    gui.right();
                                    gui.draw();
                                    break;
                            }
                        
                    }
                    }catch(Exception e){

                    }
                
			}
            int ret = gui.check_goal(Gui.xPos, Gui.yPos, Gui.goalX, Gui.goalY);
            if(ret == 0){
                Gui.randomize_player_goal();
                Gui.draw();
            }else if (ret == 1){
                Gui.f.dispose();
            }
            }
        public void keyTyped(KeyEvent ke){}
        public void keyReleased(KeyEvent ke){}
        });
       
        Gui.f.setFocusable(true);
        
    }
}