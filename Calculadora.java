
/**
 * Write a description of class Calculadora here.
 * 
 * @author Roberto Salazar Marquez 
 * @version 5.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculadora extends JFrame
{
    // instance variables - replace the example below with your own
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton b10, bMas, bMenos, bMult, bDiv, bIgual, bPunto, bBorrar;
    private JTextField display;
    private JPanel teclado, displ;
    
    private boolean punto = true, op = true;
    private char c = ' ';
    private double num1 = 0.0, num2 = 0.0, res = 0.0; 
    

    /**
     * Constructor for objects of class Calculadora
     */
    public Calculadora()
    {        
        super("Calculadora");
        
        b1 = new JButton("1");  b2 = new JButton("2");
        b3 = new JButton("3");  b4 = new JButton("4");
        b5 = new JButton("5");  b6 = new JButton("6");
        b7 = new JButton("7");  b8 = new JButton("8");
        b9 = new JButton("9");  b10 = new JButton("0");
        bMas = new JButton("+");  bMenos = new JButton("-");
        bMult = new JButton("*");  bDiv = new JButton("/");
        bPunto = new JButton(".");  bBorrar = new JButton("C");
        bIgual = new JButton("=");
        
        display  = new JTextField("0");
        teclado = new JPanel();
        displ = new JPanel();
        
        //  Configuramos el primer JPanel
        teclado.setLayout( new GridLayout(4,4,2,2) );
        teclado.add(b7);
        teclado.add(b8);
        teclado.add(b9);
        teclado.add(bDiv);
        teclado.add(b4);
        teclado.add(b5);
        teclado.add(b6);
        teclado.add(bMult);
        teclado.add(b1);
        teclado.add(b2);
        teclado.add(b3);
        teclado.add(bMenos);
        teclado.add(bIgual);
        teclado.add(b10);
        teclado.add(bPunto);
        teclado.add(bMas);
        
        //  Configuramos el segundo JPanel
        displ.setLayout( new BorderLayout() );
        displ.add(bBorrar,"East");
        displ.add( display, "Center" );
        
        // Integramos la interfaz final
        setLayout( new BorderLayout() );
        add(displ, "North");
        add(teclado, "Center");
        
        b1.addActionListener( new BotonNumerico() );
        b2.addActionListener( new BotonNumerico() );
        b3.addActionListener( new BotonNumerico() );
        b4.addActionListener( new BotonNumerico() );
        b5.addActionListener( new BotonNumerico() );
        b6.addActionListener( new BotonNumerico() );
        b7.addActionListener( new BotonNumerico() );
        b8.addActionListener( new BotonNumerico() );
        b9.addActionListener( new BotonNumerico() );
        b10.addActionListener( new BotonNumerico() );
        bBorrar.addActionListener( new BotonC() );
        bIgual.addActionListener( new BotonIgual() );
        bMas.addActionListener( new BotonOpera() );
        bMenos.addActionListener( new BotonOpera() );
        bMult.addActionListener( new BotonOpera() );
        bDiv.addActionListener( new BotonOpera() );
        bPunto.addActionListener( new BotonPunto() );
        
        display.setEnabled(true);
        setSize(400, 400);
        setVisible(true);      
    }
    
    private class WinC extends WindowAdapter 
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false);
            dispose();
        }
    }

    private class BotonC implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            display.setText("0");
            punto = true;
            op = true;
            num1 = num2 = 0.0;
        }
    }
    
    private class BotonNumerico implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String displaynum = display.getText();
            if(displaynum.equals("0"))
                  displaynum = "";
            displaynum = displaynum + e.getActionCommand();
            display.setText(displaynum);
        }
    }
    
    private class BotonIgual implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            try {
                opera();
            } catch( DivideByZeroException exp) {
                JOptionPane.showMessageDialog(null,exp.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void opera() throws DivideByZeroException
        {
            num2 = Double.parseDouble(display.getText());
            switch (c) {
                case '+': res = num1 + num2; break;
                case '-': res = num1 - num2; break;
                case '*': res = num1 * num2; break;
                case '/': if( num2 == 0 )
                                throw new DivideByZeroException();
                          res = num1 / num2; break;
            }
            display.setText( String.valueOf(res) );
            op = punto = true;
        }
        
    }
    
    private class BotonOpera implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String signo;
            if(op) {
                signo = new String( e.getActionCommand() );
                c = signo.charAt(0);
                num1 = Double.parseDouble(display.getText());
                display.setText("0");
                op = false;
                punto = true;
            }
        }
    }
    
    private class BotonPunto implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            if(punto) {
                String displaynum = display.getText();
                displaynum = displaynum + ".";
                display.setText(displaynum);
                punto = false;
            }
        }
    }
    

    public static void main(String args[])
    {

    }
}








