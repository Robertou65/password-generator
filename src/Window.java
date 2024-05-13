import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {;
    private PasswordGenerator pss = new PasswordGenerator();
    private JPanel mainPanel, titlePanel, centerPanel, contentPanel, lengthPanel, securityPanel,
                    generatedButtonPanel, passwordPanel;
    public Window (){
        setSize(1100, 600);
        setMinimumSize(new Dimension(1100, 600));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Password Generator");

        init();

        setVisible(true);
    }
    private void init (){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0,0));
        this.getContentPane().add(mainPanel);

        initTitlePanel(mainPanel);

        initComponentsPanel(mainPanel);

    }

    private void initTitlePanel (JPanel mainPanel){
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JLabel title = new JLabel();
        title.setText("Password Generator");
        title.setFont(new Font("Cascadia Code", Font.BOLD, 40));
        title.setBorder(new EmptyBorder(40,0,40,0));
        titlePanel.add(title);
    }

    private JTextArea passwordTextArea;
    private JTextField textField;
    private JRadioButton firstLevel,secondLevel, thirdLevel, fourthLevel;

    private void initComponentsPanel (JPanel mainPanel){
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(0,0));
        centerPanel.setBorder(new EmptyBorder(0,20,60,20));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        centerPanel.add(contentPanel, BorderLayout.CENTER);

        lengthPanel = new JPanel();
        lengthPanel.setLayout(new BoxLayout(lengthPanel,BoxLayout.Y_AXIS));
        contentPanel.add(lengthPanel);

        JLabel lengthText = new JLabel("Password length");
        lengthText.setFont(new Font("Cascadia Code", Font.BOLD, 20));
        lengthText.setBorder(new EmptyBorder(50,30,10,30));
        lengthPanel.add(lengthText);

        textField = new JTextField();
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
        textFieldPanel.setBorder(new EmptyBorder(0, 40, 20, 30));  // Margen solo al panel contenedor
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
        textFieldPanel.add(textField);
        lengthPanel.add(textFieldPanel);

        securityPanel = new JPanel();
        securityPanel.setLayout(new BoxLayout(securityPanel, BoxLayout.Y_AXIS));
        contentPanel.add(securityPanel, BorderLayout.CENTER);

        JLabel securityText = new JLabel("Security level");
        securityText.setFont(new Font("Cascadia Code", Font.BOLD, 21));
        securityText.setBorder(new EmptyBorder(30,70,30,30));
        securityPanel.add(securityText);

        firstLevel = new JRadioButton("Only lower case letter");
        firstLevel.setBounds(10,10,100,50);
        firstLevel.setBorder(new EmptyBorder(5,70,10,0));
        securityPanel.add(firstLevel);

        secondLevel = new JRadioButton("Lower case letter and numbers");
        secondLevel.setBounds(10,10,100,50);
        secondLevel.setBorder(new EmptyBorder(10,70,10,0));
        securityPanel.add(secondLevel);

        thirdLevel = new JRadioButton("Lower case letter, capital letter and numbers");
        thirdLevel.setBounds(10,10,100,50);
        thirdLevel.setBorder(new EmptyBorder(10,70,10,0));
        securityPanel.add(thirdLevel);

        fourthLevel = new JRadioButton("Lower case letter, capital letter, numbers and symbols");
        fourthLevel.setBounds(10,10,100,50);
        fourthLevel.setBorder(new EmptyBorder(10,70,10,0));
        securityPanel.add(fourthLevel);

        ButtonGroup grupoRadioButtons = new ButtonGroup();
        grupoRadioButtons.add(firstLevel);
        grupoRadioButtons.add(secondLevel);
        grupoRadioButtons.add(thirdLevel);
        grupoRadioButtons.add(fourthLevel);

        generatedButtonPanel = new JPanel();
        generatedButtonPanel.setBorder(new EmptyBorder(30, 70,0,30));
        contentPanel.add(generatedButtonPanel);

        JButton generatedButton = new JButton("Generate");
        generatedButton.setPreferredSize(new Dimension(200,50));
        generatedButton.setFont(new Font("Cascadia Code", Font.BOLD, 20));
        generatedButton.addActionListener(this);
        generatedButtonPanel.add(generatedButton);

        passwordTextArea = new JTextArea();
        passwordTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 20));
        passwordTextArea.setLineWrap(true);
        passwordTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(passwordTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Generated Password"));
        centerPanel.add(scrollPane, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        int size, securityLevel;

        try{
            size = Integer.parseInt(text);

            securityLevel = 0;
            if (firstLevel.isSelected()){
                securityLevel = 1;
            } else if (secondLevel.isSelected()) {
                securityLevel = 2;
            }else if(thirdLevel.isSelected()){
                securityLevel = 3;
            } else if (fourthLevel.isSelected()) {
                securityLevel = 4;
            }

            if (securityLevel == 0){
                JOptionPane.showMessageDialog(null, "Enter a valid security level",
                        "Invaild Security Level", JOptionPane.ERROR_MESSAGE);
            }else{
                passwordTextArea.append(pss.generatePassword(size, securityLevel) + "\n");
            }

        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid number for the password length.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            textField.setText("");
        }
    }
}
