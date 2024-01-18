import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.abs;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
    
    public class Board extends javax.swing.JFrame {
        public Board() {
            initialize();
            checkMoves();
        }
        
        
        private JPanel gameBoard;
        private JPanel[][] squares = new JPanel[3][3];
        private JPanel square;
        private Turn turn= Turn.X;
        Game game= new Game();

        private void initialize()
        {
            JPanel buttonPanel = new JPanel();
            JButton undo = new JButton("Undo");
            undo.setPreferredSize(new Dimension(100, 90));
            buttonPanel.add(undo);
    
            
            int row,col;
            gameBoard = new JPanel(new GridLayout(3, 3));
            for (row = 0; row < 3; row++) {
                for (col = 0; col < 3; col++) {
                    square = new JPanel();
                    square.setPreferredSize(new Dimension(100, 100));
                    square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    square.setBackground(Color.CYAN);
                    squares[row][col] = square;
                    gameBoard.add(square);
                }
            }
            
            undo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("undo and notify");
                    //memento here + observer( observer is board and subject is validatemoves)
                }
            });
            
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(gameBoard, BorderLayout.CENTER);
            getContentPane().add(buttonPanel, BorderLayout.EAST);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null);
        }
        
        
        //instead of getbackground, when we have an image or drawing, we'll add it to frame
        //and use getcomponentcount
        private void checkMoves() 
        {
            gameBoard.addMouseListener(new MouseAdapter() {
                int row,col;
                Boolean flag;
                @Override
                public void mouseClicked(MouseEvent e) {
                    row=e.getY()/100;
                    col=e.getX()/100;
                    flag=false;
                    //System.out.println("row " + row+ " col " + col);
                    if(squares[row][col].getComponentCount()==0 && squares[row][col].getBackground()==Color.CYAN && turn==Turn.X && flag==false)
                    {
                        //turn=game.makeMove(row, col, turn);
                        squares[row][col].setBackground(Color.RED);
                        if(winningMove(row,col)){
                            JOptionPane.showMessageDialog(null, "Red WINS! ");
                            setVisible(false);
                        }
                        turn=Turn.O;
                        flag=true;
                    }
                    else if(flag) // because now after red is played, flag is true, so it wont enter the else if for the green
                    {
                        System.out.println("Square is used by red");
                    }
                    if(squares[row][col].getComponentCount()==0 && squares[row][col].getBackground()==Color.CYAN && turn==Turn.O && flag==false)
                    {
                        //turn=game.makeMove(row, col, turn);
                        squares[row][col].setBackground(Color.GREEN);
                        if(winningMove(row,col)){
                            JOptionPane.showMessageDialog(null, "Green WINS! ");
                            setVisible(false);
                        }
                        turn=Turn.X;
                        flag=true;
                    }
                    else if(!flag)
                    {
                        System.out.println("Square is used by green");
                    }
                    
                }
    
            });
        }
        
        private Boolean winningMove(int row, int col)
        {
            if(abs(row-col)==2 || row==col)// one of the corners + middle
                if(checkDiagonal(row,col))
                    return true;
            if(checkHorizontalVertical(row,col))
                return true;
            return false;
        }
        
        private Boolean checkHorizontalVertical(int row, int col)
        {
            Boolean flag=true;
            for(int i=0;i<3;i++)
            {
                if(squares[row][col].getBackground()!=squares[row][i].getBackground()) //check horizontal
                    flag=false;
                if(!flag)
                    break;
            }
            if(flag)
                return true;
            flag=true;//if horizontal check failed
            for(int i=0;i<3;i++)
            {
                if(squares[row][col].getBackground()!=squares[i][col].getBackground()) //check vertical
                    flag=false;
                if(!flag)
                    break;
            }
            if(flag)
                return true;
            return false;//if neither happens
        }
        
        private Boolean checkDiagonal(int row, int col)
        {
            if(row==col)
            {
                if(squares[row][col].getBackground()==squares[0][0].getBackground() && squares[row][col].getBackground()==squares[1][1].getBackground() && squares[row][col].getBackground()==squares[2][2].getBackground())
                    return true;
                return false;
            }
            else
            {
                if(squares[row][col].getBackground()==squares[0][2].getBackground() && squares[row][col].getBackground()==squares[1][1].getBackground() && squares[row][col].getBackground()==squares[2][0].getBackground())
                    return true;
                return false;
            }
        }

        public static void main(String args[]) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
    
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Board().setVisible(true);
                }
            });
}}
