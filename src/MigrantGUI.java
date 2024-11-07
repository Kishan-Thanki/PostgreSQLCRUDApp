import javax.swing.*;
import java.awt.*;

class MigrantGUI extends JFrame {
    Container c;
    JLabel label1, label2, label3, label4;
    Color c1 = new Color(60, 64, 72);

    MigrantGUI() {
        // Set JFrame properties directly
        setTitle("Migrant Panel");
        setBounds(63, 45, 862, 475);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Container
        c = getContentPane();
        c.setLayout(null);


        /* ========== JLabel ========== */
        // JLabel Heading
        label1 = new JLabel();
        label1.setText("Migrant Panel");
        label1.setBounds(300,5,1200,50);
        label1.setFont(new Font("Arial",Font.BOLD,40));
        c.add(label1);

        // Title JLabels
        // JLabel First Name
        label2 = new JLabel();
        label2.setText("First Name");
        label2.setBounds(20,130,100,20);
        label2.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(label2);

        // JLabel Middle Name
        label3 = new JLabel();
        label3.setText("Middle Name");
        label3.setBounds(20,160,100,20);
        label3.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(label3);

        // JLabel Last Name
        label4 = new JLabel();
        label4.setText("Last Name");
        label4.setBounds(20,190,100,20);
        label4.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(label4);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MigrantGUI();
    }
}
