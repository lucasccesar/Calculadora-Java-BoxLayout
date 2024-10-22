import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BoxLayoutExemplo implements ActionListener {

    double[] values = new double[4];
    boolean operation;
    boolean virgula;
    int decimalPosition = 10;

    JFrame frame = new JFrame("BoxLayout Exemplo");

    JPanel panelConteiner = new JPanel();

    JTextArea text = new JTextArea();

    JButton[][] buttons = new JButton[4][4];
    JPanel[] panels = new JPanel[4];

    public BoxLayoutExemplo() {
        panelConteiner.setLayout(new BoxLayout(panelConteiner, BoxLayout.Y_AXIS));

        panelConteiner.add(text);
        text.setEditable(false);
        text.setText("" + 0);
        text.setFont(new Font("Arial", Font.PLAIN, 60));

        panels[0] = new JPanel();
        panels[1] = new JPanel();
        panels[2] = new JPanel();
        panels[3] = new JPanel();

        for (JPanel panel : panels) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
            panelConteiner.add(panel);
        }

        buttons[0][0] = new JButton("7");
        buttons[0][1] = new JButton("8");
        buttons[0][2] = new JButton("9");
        buttons[0][3] = new JButton("/");

        buttons[1][0] = new JButton("4");
        buttons[1][1] = new JButton("5");
        buttons[1][2] = new JButton("6");
        buttons[1][3] = new JButton("*");

        buttons[2][0] = new JButton("1");
        buttons[2][1] = new JButton("2");
        buttons[2][2] = new JButton("3");
        buttons[2][3] = new JButton("+");

        buttons[3][0] = new JButton("-");
        buttons[3][1] = new JButton("0");
        buttons[3][2] = new JButton(",");
        buttons[3][3] = new JButton("=");

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setFont(new Font("Courier New", Font.BOLD, 60));
                buttons[i][j].addActionListener(this);
                panels[i].add(buttons[i][j]);
            }
        }

        frame.add(panelConteiner);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    public static void main(String[] args) {
        new BoxLayoutExemplo();
    }

    public void actionPerformed(ActionEvent e) {

        String buttonText = e.getActionCommand();

        try {

            if (!virgula) {
                values[operation ? 1 : 0] = 10 * values[operation ? 1 : 0] + Integer.parseInt(buttonText);
                text.setText("" + (int) values[operation ? 1 : 0]);
            } else {
                values[operation ? 1 : 0] += Double.parseDouble(buttonText) / decimalPosition;
                text.setText("" + values[operation ? 1 : 0]);
                decimalPosition *= 10;
            }

        } catch (NumberFormatException ex) {

            if (Objects.equals(buttonText, "=")) {
                if (operation) {
                    if (values[2] == 1) {
                        text.setText("" + (values[0] + values[1]));
                    } else if (values[2] == 2) {
                        text.setText("" + (values[0] - values[1]));
                    } else if (values[2] == 3) {
                        text.setText("" + (values[0] * values[1]));
                    } else if (values[2] == 4) {
                        text.setText("" + (values[0] / values[1]));
                    }
                    values[0] = 0;
                    values[1] = 0;
                    values[2] = 0;
                    operation = false;
                }
            } else if (Objects.equals(buttonText, ",")) {
                virgula = true;
                text.setText("" + (values[operation ? 1 : 0]));
            } else {
                operation = true;
                if (Objects.equals(buttonText, "+")) {
                    values[2] = 1;
                    text.setText("+");
                } else if (Objects.equals(buttonText, "-")) {
                    values[2] = 2;
                    text.setText("-");
                } else if (Objects.equals(buttonText, "*")) {
                    values[2] = 3;
                    text.setText("*");
                } else if (Objects.equals(buttonText, "/")) {
                    values[2] = 4;
                    text.setText("/");
                }
                decimalPosition = 10;
                virgula = false;
            }

        }

    }

}
