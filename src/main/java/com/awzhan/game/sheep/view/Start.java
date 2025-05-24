package com.awzhan.game.sheep.view;

import javax.swing.JFrame;

public class Start extends JFrame {

    public Start() {
        this.setTitle("Sheep Game");
        this.setSize(400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Start();
    }
}
