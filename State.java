import java.lang.Math;
import java.util.*;
class State{
    public int xPos, yPos, goalX, goalY;
    double f,g,h;
    public char maze[][];
    public ArrayList<Character> moves_done = null;
    public String pos;
    public State(char maze[][], int xPos, int yPos, int goalX, int goalY, double cost, ArrayList<Character> prev_moves, char new_move){
        this.moves_done = new ArrayList<Character>(prev_moves);
        this.moves_done.add(new_move);
        this.xPos = xPos;
        this.yPos = yPos;
        this.goalX = goalX;
        this.goalY = goalY;
        this.maze = maze;
        this.g = cost;
        pos = pos_to_string();
        compute_h();
        print_f();
    }
    public String pos_to_string(){
        return String.valueOf(xPos)+String.valueOf(yPos);
    }
    public void print_f(){
        this.f = this.g + this.h;
    }
    public void compute_h(){
        double s1 = Math.pow((xPos - goalX),2);
        double s2 = Math.pow((yPos - goalY),2);
        this.h = Math.sqrt(s1+s2);
    }
    public boolean check_goal(){
        if(this.xPos == this.goalX && this.yPos == this.goalY){
            System.out.println("Goal!");
            return true;
        }
        return false;

    }
    public void print_status(){
        System.out.println("Player: "+xPos+" x "+yPos);
        System.out.println("Goal: "+goalX+" x "+goalY);
    }
    public void print_array(){
        for(int i = 0; i< Gui.board_size; i++){
            for(int j = 0; j < Gui.board_size; j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }
    }
}