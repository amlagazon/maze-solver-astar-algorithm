
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*; 
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
class Gui{
    public static char maze[][];
    static JFrame f;
    static int xPos, yPos,goalX, goalY;
    public static int board_size;
    public static JTextArea textArea;
    public Gui(){
        f=new JFrame(); 
        f.setLayout(new BorderLayout());
        this.north_panel();
        f.setSize(700  ,700);
        f.setResizable(false);    
        f.setVisible(true);  

    }
    
    public void north_panel(){
        f.getContentPane().removeAll();
        //----- ------------------NORTH PANEL---------------
        JPanel north = new JPanel();

        JLabel label = new JLabel("Enter preferred size:");
        label.setForeground(Color.white);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(50,25));

        JButton submit = new JButton("Play");

        north.add(label);
        north.add(textArea);
        north.add(submit);
        north.setBackground(Color.gray);
        f.add(north,BorderLayout.CENTER);

        submit.addActionListener(new Submit());
        f.setFocusable(true);

        f.getContentPane().validate();
        f.getContentPane().repaint();

        //----------------------------------------------
    }
    public static void randomize_player_goal(){
        Random rand = new Random();
        int  px = rand.nextInt(Gui.board_size);
        int py = rand.nextInt(Gui.board_size);
        
        xPos = px;
        yPos = py;
        int gx = rand.nextInt(Gui.board_size);
        int gy = rand.nextInt(Gui.board_size);

        while(gx == px && gy == px){
            gx = rand.nextInt(Gui.board_size);
            gy = rand.nextInt(Gui.board_size);
        }
        goalX = gx;
        goalY = gy;

        System.out.println("Player: "+px+" x "+py);
        System.out.println("Goal: "+gx+" x "+gy);
        randomize_map();
        print_array();
        draw();
    }
    public static void randomize_map(){
        maze = new char[board_size][board_size];
        Random rand = new Random();
        for(int i = 0; i < board_size; i++){
            for(int j = 0; j < board_size; j++){
                int p = rand.nextInt(3);
                if(i == xPos && j == yPos) maze[i][j] = 'P';
                else if(i == goalX && j == goalY) maze[i][j] = 'G';
                else if(p==1) maze[i][j] = 'W';
                else maze[i][j] = 't';
            }
        }
    }
    public int up(){
        int up = this.xPos - 1;
        if(up < 0) return 0; //if out of bounds
        char type = maze[up][this.yPos];
        if(type != 'W'){
            maze[this.xPos][this.yPos] = 'v';
            maze[up][this.yPos] = 'P';
            this.xPos = up;
        }
        return 0;
    }
    public int down(){
        int down = this.xPos + 1;
        if(down > this.board_size - 1) return 0; //if out of bounds
        char type = maze[down][this.yPos];
        if(type != 'W'){
            maze[this.xPos][this.yPos] = 'v';
            maze[down][this.yPos] = 'P';
            this.xPos = down;
        }
        return 0;
    }
    public int left(){
        int left = this.yPos - 1;
        if(left < 0) return 0; //if out of bounds
        char type = maze[this.xPos][left];
        if(type != 'W'){
            maze[this.xPos][this.yPos] = 'v';
            maze[this.xPos][left] = 'P';
            this.yPos = left;
        }
        return 0;
    }
   public int right(){
        int right = this.yPos + 1;
        if(right > this.board_size - 1) return 0; //if out of bounds
        char type = maze[this.xPos][right];
        if(type != 'W'){
            maze[this.xPos][this.yPos] = 'v';
            maze[this.xPos][right] = 'P';
            this.yPos = right;
        }
        return 0;
    }
    public int check_goal(int x, int y, int goalx, int goaly){
        int ret = -1;
        if(x == goalx && y == goaly){
            System.out.println("Goal!");
            ret = JOptionPane.showConfirmDialog((Component)null, "Goal! Do you want to play again?", "alert", JOptionPane.OK_OPTION);
            System.out.println("Return: " +ret);
        }
        return ret;

    }
    public static void draw(){
        f.getContentPane().removeAll();
        for(int w = 0; w<board_size; w++){
            for(int x = 0; x<board_size; x++){
                char type = maze[w][x];
                JPanel obj = new JPanel();
                if (type == 'W'){
                    obj.setBackground(Color.black);
                }else if(type == 't'){
                    obj.setBackground(Color.white);
                }else if(type == 'P'){
                    obj.setBackground(Color.red);
                }else if(type == 'G'){
                    obj.setBackground(Color.yellow);
                }else if(type == 'v'){
                    obj.setBackground(Color.green);
                }
            f.add(obj);
            }          

        }
        f.setLayout(new GridLayout(board_size,board_size));
        f.getContentPane().validate();
        f.getContentPane().repaint();
        
    }
    public static void solve(){
        Solver solver = new Solver(maze,xPos, yPos,goalX, goalY);
    }
    public static void print_array(){
        for(int i = 0; i< board_size; i++){
            for(int j = 0; j < board_size; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    public void print_pos(){
        System.out.println(this.xPos+" x "+this.yPos+": "+this.board_size);
    }
    public void check_size(){
        try{
             FileReader inputStream = new FileReader("puzzle.in");
             int c = inputStream.read();
             do{

                 if((char)c == '\n') this.board_size++;
                 c = inputStream.read();

             }while(c!=-1);
             inputStream.close();
        }catch(Exception e){

        }

    }
    
}