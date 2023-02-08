package com.Mygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {

    JLabel heading, clockLabel;
    Font font=new Font("",Font.CENTER_BASELINE,40);
    JPanel mainpanel;


    JButton []btns=new JButton[9];


    //Game instance variable

    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={
            {0 ,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6},
    };

    int winner = 2;
        boolean gameOver;


    MyGame()
    {
        System.out.println("chalo Game suru Kre..");
        setTitle("tic toi tae :)");
        setSize(750,750);
        ImageIcon icon =new ImageIcon("src/img/new.png");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);

    }

    private void createGUI()
    {
        //by deful background is setted but if You want to constmize then write this Line
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());

        //North Heading..

        heading=new JLabel("Tic Tac Toe");
        //heading.setIcon(new ImageIcon("src/img/new.png"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(heading,BorderLayout.NORTH);

        //For Clock
        clockLabel=new JLabel("Clock");
        clockLabel.setForeground(Color.white);

        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);

        //clock By thread Method

        Thread t=new Thread(){
            public void run()
            {
              try
              {
                  while (true)
                  {
                      String datetime=new Date().toLocaleString();

                      clockLabel.setText(datetime);

                      Thread.sleep(1000);
                  }
              }catch (Exception v)
              {
                  v.printStackTrace();
              }
            }
        };

        t.start();

        ///////Midel all  panel sections
        mainpanel=new JPanel();

        mainpanel.setLayout(new GridLayout(3,3));

        for (int i=1; i<=9; i++){
            JButton btn=new JButton();

            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainpanel.add(btn);
            btns[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }

        this.add(mainpanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButtob =(JButton) e.getSource();

        String nameSTR = currentButtob.getName();
        System.out.println(nameSTR);

        int name = Integer.parseInt(nameSTR.trim());

        if(gameOver == true)
        {
            JOptionPane.showMessageDialog(this, "gameOver Is Over");
            return;
        }

        if(gameChances[name]==2)
        {
            if(activePlayer==1)
            {
                currentButtob.setIcon(new ImageIcon("src/img/Onew.png"));

                gameChances[name] = activePlayer;
                activePlayer=0;
            }else {
                currentButtob.setIcon(new ImageIcon("src/img/X.png"));

                gameChances[name] = activePlayer;
                activePlayer=1;
            }

            //find winner....

            for (int []temp:wps)
            {
                if((gameChances[temp[0]] == gameChances[temp[1]]) &&(gameChances[temp[1]]==gameChances[temp[2]])&&gameChances[temp[2]]!=2)
                {
                   winner=gameChances[temp[0]];
                   JOptionPane.showMessageDialog(null," Player " + winner + " Tum jeet gye ho.. ");
                   int i = JOptionPane.showConfirmDialog(this," Aapko Firse Khelna he ??   ⁖  :-D");
                   if(i==0)
                   {
                       this.setVisible(false);
                       new MyGame();
                   }else if(i==1)
                   {
                       System.exit(777);
                   }else {

                   }
                    System.out.println(i);
                    break;
                }
            }

            //drow Logic

            int c = 0;

            for(int x:gameChances)
            {
                if (x==2)
                {
                    c++;
                    break;
                }
            }

            if(c==0 && gameOver==false)
            {
                JOptionPane.showMessageDialog(null,"Mukabala Barabarika..");

                int i = JOptionPane.showConfirmDialog(this, "Ek Or💨❗");
                if (i==0)
                {
                    this.setVisible(false);

                    new MyGame();
                }else if (i==1)
                {
                    System.exit(778);
                } else {

                    JOptionPane.showConfirmDialog(this,"Tum Saste nasedi Ho Tune Cancle Kyu Dabaya (∪.∪ )...zzz");

                    new MyGame();

                }

                gameOver=true;
            }
        }else
        {
            JOptionPane.showMessageDialog(this,"na na na.. chiting Nahi   ( ´･･)ﾉ(._.`)");
        }

    }
}
