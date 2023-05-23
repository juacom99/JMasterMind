/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.improvisados.jmastermind.gui.components;

import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.plaf.UIResource;
/**
 *
 * @author jomartinez
 */
public class LeftRightSpinnerUI extends BasicSpinnerUI {
 
  public static ComponentUI createUI(JComponent c) {
    return new LeftRightSpinnerUI();
  }
 
  @Override
  protected Component createNextButton() {
    Component c = createArrowButton(SwingConstants.EAST);
    c.setName("Spinner.nextButton");
    installNextButtonListeners(c);
    return c;
  }
 
  @Override
  protected Component createPreviousButton() {
    Component c = createArrowButton(SwingConstants.WEST);
    c.setName("Spinner.previousButton");
    installPreviousButtonListeners(c);
    return c;
  }

    @Override
    protected JComponent createEditor() {
         JSpinner.NumberEditor c = new JSpinner.NumberEditor(spinner,"0");
         c.getTextField().setBackground(Color.red);
         c.getTextField().setOpaque(true);
         
         c.setName("Spinner.editor");
         System.out.println(c);
         
        Theme theme=ThemeController.getInstance().getCurrentTheme();
        c.setBackground(theme.getForegroundColor());
        c.setForeground(theme.getBackgroundColor());  

       /* ((JTextField)c).setHorizontalAlignment(JTextField.CENTER);
        ((JTextField)c).setText(spinner.getValue()+"");*/
       c.setBorder(null);
        
        return c;
    }
  
  
 
  // copied from BasicSpinnerUI
  private Component createArrowButton(int direction) {
    JButton b = new BasicArrowButton(direction);
    Border buttonBorder = UIManager.getBorder("Spinner.arrowButtonBorder");
    if (buttonBorder instanceof UIResource) {
      b.setBorder(new CompoundBorder(buttonBorder, null));
    } else {
      b.setBorder(buttonBorder);
    }
    
    Theme theme=ThemeController.getInstance().getCurrentTheme();
    
    b.setInheritsPopupMenu(true);
    b.setBackground(theme.getForegroundColor());
    b.setForeground(theme.getBackgroundColor());
    b.setContentAreaFilled(false);
    b.setBorder(null);
    return b;
  }
 
  @Override
  public void installUI(JComponent c) {
    super.installUI(c);
    c.removeAll();
    c.setLayout(new BorderLayout());
    c.add(createNextButton(), BorderLayout.EAST);
    c.add(createPreviousButton(), BorderLayout.WEST);
    c.add(createEditor(), BorderLayout.CENTER);
  }
}
