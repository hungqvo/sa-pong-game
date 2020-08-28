/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame.Objects;

import java.awt.Graphics2D;

/**
 *
 * @author David
 */
public interface GameObject {
    public void move();
    public void setInitData(GameWindow window, int data1, int data2, int data3, int data4, int data5);
    public void paint(Graphics2D g2d);
    public int getData(String name);
}
