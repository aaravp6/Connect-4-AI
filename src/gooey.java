import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class gooey {
    public JLabel score1Label;
    public JLabel score2Label;
    public JLabel[][]grid;
    public JButton[] buttons;
    public JLabel text;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton button6;
    public JButton button7;
    public JButton b1;
    public JButton b2;
    public JSlider difficulty;
    public JButton start;
    public boolean ready;
    public int b=0;
    public boolean p1Human=true;
    public boolean p2Human=false;
    public int dif;
    public gooey() {
        JFrame frame=new JFrame();
        JLayeredPane panel=new JLayeredPane();
        frame.setSize(800,1150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        frame.setResizable(false);
        panel.setOpaque(true);
        panel.setBackground(new Color(255,255,153));

        ImageIcon backgroundImg=new ImageIcon("C:\\AaravWorld\\code\\FinalGUICSAConnect4AI\\Background.png");
        JLabel background=new JLabel(backgroundImg);
        background.setBounds(-5,150,800,580);
        panel.add(background,JLayeredPane.POPUP_LAYER);

        JLabel title=new JLabel("Connect Four",JLabel.CENTER);
        title.setFont(new Font("Bahnschrift",Font.BOLD,75));
        title.setBounds(0,25,800,100);
        panel.add(title);

        JLabel x=new JLabel("-",JLabel.CENTER);
        x.setFont(new Font("Bahnschrift",Font.BOLD,40));
        x.setBounds(0,80,800,100);
        panel.add(x);

        JLabel l1=new JLabel("Player 1:",JLabel.CENTER);
        l1.setFont(new Font("Bahnschrift",Font.BOLD,36));
        l1.setForeground(Color.RED);
        l1.setBounds(150,943,160,70);
        panel.add(l1);

        JLabel l2=new JLabel("Player 2:",JLabel.CENTER);
        l2.setFont(new Font("Bahnschrift",Font.BOLD,36));
        l2.setForeground(Color.BLUE);
        l2.setBounds(450,943,160,70);
        panel.add(l2);

        start=new JButton("Start");
        start.setFont(new Font("Bahnschrift",Font.ITALIC,32));
        start.setBounds(95,825,600,100);
        start.setFocusable(false);
        panel.add(start);
        ActionListener strtl=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ready=true;
                start.setVisible(false);
            }
        };
        start.addActionListener(strtl);


        b1=new JButton("Human");
        b1.setFont(new Font("Bahnschrift",Font.ITALIC,32));
        b1.setBackground(new Color(255,136,77));
        b1.setBounds(150,1000,160,70);
        b1.setFocusable(false);
        panel.add(b1);
        ActionListener bal1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(p1Human) {
                    p1Human=false;
                    b1.setText("Bot");
                    b1.setBackground(new Color(128,102,255));
                } else {
                    p1Human=true;
                    b1.setText("Human");
                    b1.setBackground(new Color(255,136,77));
                }
            }
        };
        b1.addActionListener(bal1);

        b2 =new JButton("Bot");
        b2.setFont(new Font("Bahnschrift",Font.ITALIC,32));
        b2.setBackground(new Color(128,102,255));
        b2.setBounds(450,1000,160,70);
        b2.setFocusable(false);
        panel.add(b2);
        ActionListener bal2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(p2Human) {
                    p2Human=false;
                    b2.setText("Bot");
                    b2.setBackground(new Color(128,102,255));
                } else {
                    p2Human=true;
                    b2.setText("Human");
                    b2.setBackground(new Color(255,136,77));
                }
            }
        };
        b2.addActionListener(bal2);

        score1Label=new JLabel("0",JLabel.RIGHT);
        score1Label.setFont(new Font("Bahnschrift",Font.BOLD,40));
        score1Label.setForeground(Color.RED);
        score1Label.setBounds(0,80,390,100);
        panel.add(score1Label);

        score2Label=new JLabel("0",JLabel.LEFT);
        score2Label.setFont(new Font("Bahnschrift",Font.BOLD,40));
        score2Label.setForeground(Color.BLUE);
        score2Label.setBounds(410,80,390,100);
        panel.add(score2Label);

        grid=new JLabel[6][7];
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[i].length;j++) {
                grid[i][j]=new JLabel();
                grid[i][j].setBounds(j*90+88,i*90+180,75,75);
                grid[i][j].setOpaque(true);
                grid[i][j].setBackground(Color.WHITE);
                panel.add(grid[i][j],JLayeredPane.DEFAULT_LAYER);
            }
        }


        button1=new JButton("1");
        button1.setBounds(90,740,75,50);
        button1.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button1.setBackground(Color.LIGHT_GRAY);
        button1.setFocusable(false);
        panel.add(button1);
        button2=new JButton("2");
        button2.setBounds(180,740,75,50);
        button2.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button2.setBackground(Color.LIGHT_GRAY);
        button2.setFocusable(false);
        panel.add(button2);
        button3=new JButton("3");
        button3.setBounds(270,740,75,50);
        button3.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button3.setBackground(Color.LIGHT_GRAY);
        button3.setFocusable(false);
        panel.add(button3);
        button4=new JButton("4");
        button4.setBounds(360,740,75,50);
        button4.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button4.setBackground(Color.LIGHT_GRAY);
        button4.setFocusable(false);
        panel.add(button4);
        button5=new JButton("5");
        button5.setBounds(450,740,75,50);
        button5.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button5.setBackground(Color.LIGHT_GRAY);
        button5.setFocusable(false);
        panel.add(button5);
        button6=new JButton("6");
        button6.setBounds(540,740,75,50);
        button6.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button6.setBackground(Color.LIGHT_GRAY);
        button6.setFocusable(false);
        panel.add(button6);
        button7=new JButton("7");
        button7.setBounds(630,740,75,50);
        button7.setFont(new Font("Bahnschrift",Font.BOLD,40));
        button7.setBackground(Color.LIGHT_GRAY);
        button7.setFocusable(false);
        panel.add(button7);

        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        button5.setVisible(false);
        button6.setVisible(false);
        button7.setVisible(false);
        ActionListener value1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=1;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=2;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value3=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=3;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value4=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=4;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value5=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=5;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value6=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=6;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        ActionListener value7=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b=7;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                button5.setVisible(false);
                button6.setVisible(false);
                button7.setVisible(false);
            }
        };
        button1.addActionListener(value1);
        button2.addActionListener(value2);
        button3.addActionListener(value3);
        button4.addActionListener(value4);
        button5.addActionListener(value5);
        button6.addActionListener(value6);
        button7.addActionListener(value7);


        text=new JLabel("",JLabel.CENTER);
        text.setFont(new Font("Bahnschrift",Font.BOLD,60));
        text.setOpaque(true);
        text.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        text.setBorder(border);
        text.setBounds(95,825,600,100);
        panel.add(text);





        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new gooey();
    }
}