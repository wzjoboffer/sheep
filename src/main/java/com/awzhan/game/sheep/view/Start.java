package com.awzhan.game.sheep.view;

import javax.swing.JFrame;

public class Start extends JFrame {

    public Start() {
        this.setTitle("Sheep Game");
        this.setSize(400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        autoRefresh();
    }

    private void autoRefresh() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Start();
    }
}
