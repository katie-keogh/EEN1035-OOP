import javax.swing.*;
import javax.swing.plaf.SplitPaneUI;

import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class DisCalc extends Frame implements WindowListener, ActionListener {

    JTextArea text = new JTextArea(10, 80);
    int alph[] = new int[27];
    JTextField alphText[] = new JTextField[27];
    JPanel left = new JPanel(), right = new JPanel();
    JButton calc = new JButton("Calculate Dist");

    public DisCalc(){
        super("Dis calc");
        // setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 

        // this.add(lhs());
        // this.add(rhs());

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lhs(), rhs());
        sp.setOneTouchExpandable(true);
        sp.setDividerLocation(110);
        // this.getContentPane().add(sp);

        this.add(sp);
        this.addWindowListener(this);
        this.pack();
        this.setVisible(true);
    }

    private JPanel lhs(){
        JPanel p = new JPanel();
        this.text.setLineWrap(true);
        this.text.setWrapStyleWord(true);
        this.text.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(this.text);
        this.calc.addActionListener(this);
        p.add(this.calc);

        

        return p;
    }

    private JPanel rhs(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 4));
        for(int i = 0; i < 27; i++){
            this.alphText[i] = new JTextField();
            this.alphText[i].setEditable(false);
            p.add(this.alphText[i]);
            if(i < 26){
                char l = (char) ('a' + i);
                p.add(new JLabel("" + l));
            } else {
                p.add(new JLabel("other"));
            }
        }
        

        return p;
    }

    public void actionPerformed(ActionEvent e){
        System.out.println("HII");
        String t = this.text.getText();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            if((int) c >= 'a' && (int) c <= 'z'){
                this.alph[c - 'a']++; 
            } else {
                this.alph[26]++;
            }
        }

        for(int i = 0; i < 27; i++){
            this.alphText[i].setText("" + this.alph[i]);
        }
    }

    
    public static void main(String[] args) {
        DisCalc s = new DisCalc();
        
    }


    public void windowActivated(WindowEvent arg0){}
    public void windowClosed(WindowEvent arg0){}
    public void windowClosing(WindowEvent arg0) {
        int option = JOptionPane.showOptionDialog(
        DisCalc.this,
        "Are you sure you want to quit?",
        "Exit Dialog", JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE, null, null,
        null );

        if( option == JOptionPane.YES_OPTION ) {
        System.exit(0);
        }
        else {}
    }
    public void windowDeactivated(WindowEvent arg0){}
    public void windowIconified(WindowEvent arg0){}
    public void windowOpened(WindowEvent arg0){}
    public void windowDeiconified(WindowEvent arg0){}

}
