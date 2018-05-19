import java.util.*;
class Solver{
    State state1;
    char[] valid_actions = new char[4];
    static PriorityQueue<State> openList;
    static ArrayList<String> explored;
    static ArrayList<Character> moves;
    public Solver(char maze[][], int xPos, int yPos, int goalX, int goalY){
        this.explored = new ArrayList<String>();
        state1 = new State(maze,xPos,yPos, goalX, goalY,0,new ArrayList<Character>(),'x');
        System.out.println(">"+state1.pos);
        openList=new PriorityQueue<State>(10, new Comparator<State>() {
        public int compare(State state_1, State state_2) {
            if (state_1.f < state_2.f) return -1;
            else if (state_1.f > state_2.f) return 1;
            return 0;
        }
        
        });
        State goal = astar(state1);
        System.out.println("\nremoved:");
        System.out.println(goal.moves_done);
        moves = goal.moves_done;
    }
  
    public State astar(State state){
        openList.add(state);
        while(!openList.isEmpty()){
            State s = openList.remove();
            explored.add(s.pos);
            if(s.check_goal()) return s;
            actions(s);
            for(int i= 0; i < 4; i++){
                if(valid_actions[i] == 'w' || valid_actions[i] == 'a'|| valid_actions[i] == 's'|| valid_actions[i] == 'd'){
                    State res = result(s,valid_actions[i]);
                    if(!explored.contains(res.pos)){
                        openList.add(res);
                    }
                }
            }

        }
        return state;

    }
    
    public static String mazeToString(State state){
        char[][] m = state.maze;
        String ret = "";
        for(int i = 0; i < Gui.board_size; i++){
            ret+= new String(m[i]);
        }
        return ret;
    }
    public void actions(State state){
        int up,down,left,right;
        up = state.xPos - 1;
        down = state.xPos + 1;
        left = state.yPos - 1;
        right = state.yPos + 1;
        char type;
        
        if(up >= 0){
            type = state.maze[up][state.yPos];
            if(type == 'W'){
                valid_actions[0] = 'x';
            }else{
                valid_actions[0] = 'w';
            }
        }
        if(down < Gui.board_size){
            type = state.maze[down][state.yPos];
            if(type == 'W'){
                valid_actions[1] = 'x';
            }else{
                valid_actions[1] = 's';
            }
        }
        if(left >= 0){
            type = state.maze[state.xPos][left];
            if(type == 'W'){
                valid_actions[2] = 'x';
            }else{
                valid_actions[2] = 'a';
            }
        }
        if(right < Gui.board_size){
            type = state.maze[state.xPos][right];
            if(type == 'W'){
                valid_actions[3] = 'x';
            }else{
                valid_actions[3] = 'd';
            }
        }
        

    }
    public char[][] copy_array(char[][] to_copy){
        char[][] ret = new char[Gui.board_size][Gui.board_size];
        
        for(int i = 0; i < Gui.board_size; i++){
            ret[i] = to_copy[i].clone();
        }
        return ret;
        
    }
    public boolean check_goal(State state){
        if(state.check_goal()) System.out.println("\nGOAL!\n");
        else System.out.println("\nNOT GOAL!\n");
        return true;

    }
    public State result(State state,char move){
        if(state == null) return state;
        switch (move){
            case 'w':
                return up(state);
            case 'a':
                return left(state);
            case 's':
                return down(state);
            case 'd':
                return right(state);
        }
        return null;
    }
    public State up(State state){
        char maze_local[][] = copy_array(state.maze);
        int up = state.xPos - 1;
        State ret = state;
        if(up < 0) return state; //if out of bounds
        char type = maze_local[up][state.yPos];
        if(type != 'W'){
            maze_local[state.xPos][state.yPos] = 'v';
            maze_local[up][state.yPos] = 'P';
            ret = new State(maze_local,up,state.yPos,state.goalX,state.goalY,state.g+1,state.moves_done,'w');
        }
        return ret;
    }
    public State down(State state){
        char maze_local[][] =  copy_array(state.maze);
        int down = state.xPos + 1;
        State ret = state;
        if(down > Gui.board_size-1) return state; //if out of bounds
        char type = maze_local[down][state.yPos];
        if(type != 'W'){
            maze_local[state.xPos][state.yPos] = 'v';
            maze_local[down][state.yPos] = 'P';
            ret = new State(maze_local,down,state.yPos,state.goalX,state.goalY,state.g+1,state.moves_done,'s');
        }

        return ret;
    }
    public State left(State state){
        char maze_local[][] =  copy_array(state.maze);
        int left = state.yPos - 1;
        State ret = state;
        if(left < 0) return state; //if out of bounds
        char type = maze_local[state.xPos][left];
        if(type != 'W'){
            maze_local[state.xPos][state.yPos] = 'v';
            maze_local[state.xPos][left] = 'P';
            ret = new State(maze_local,state.xPos,left,state.goalX,state.goalY,state.g+1,state.moves_done,'a');
        }
        return ret;
    }
    public State right(State state){
        char maze_local[][] =  copy_array(state.maze);
        int right = state.yPos + 1;
        State ret = state;
        if(right > Gui.board_size-1) return state; //if out of bounds
        char type = maze_local[state.xPos][right];
        if(type != 'W'){
            maze_local[state.xPos][state.yPos] = 'v';
            maze_local[state.xPos][right] = 'P';
            ret = new State(maze_local,state.xPos,right,state.goalX,state.goalY,state.g+1,state.moves_done,'d');
        }
        return ret;
    }
}