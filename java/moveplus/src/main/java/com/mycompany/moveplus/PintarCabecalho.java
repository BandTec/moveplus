/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moveplus;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author beatriz
 */
public class PintarCabecalho extends JLabel implements TableCellRenderer{
public PintarCabecalho(){
        setFont(new java.awt.Font("Jamrul", 0, 18));
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(new Color(51,204,255));
        setBorder(BorderFactory.createEmptyBorder());
        setHorizontalAlignment(JLabel.CENTER);
    
}
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
    
}
