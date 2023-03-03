package com.joatan.pdfmapper.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * @author Joatan Sampaio
 * @date 3/2/2023
 *
 */
@SuppressWarnings("serial")
public class Button extends JButton{

	public Button(String label) {
		super(label);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(200, 50));
	}
	
}
