
import java.awt.*; 
import java.awt.event.*;
public class Submit implements ActionListener{
    public void actionPerformed( ActionEvent e )
    {     
        Gui.board_size = Integer.parseInt(Gui.textArea.getText());
        Gui.randomize_player_goal();
    }
}