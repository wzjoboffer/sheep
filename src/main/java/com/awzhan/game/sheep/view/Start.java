package com.awzhan.game.sheep.view;

import com.awzhan.game.sheep.model.Brand;

import javax.swing.JFrame;

public class Start extends JFrame {

    public Start() {
        this.setTitle("Sheep Game");
        this.setSize(400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Brand testBrand = new Brand("wei");
        this.getContentPane().add(testBrand);

        this.setVisible(true);
        autoRefresh();
    }

    private void autoRefresh() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Start();
    }
}
