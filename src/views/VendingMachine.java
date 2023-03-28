package views;

import machines.Result;
import machines.TuringMachine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class VendingMachine extends JFrame implements ActionListener {
    private JPanel itemsPanel;
    private JPanel keyPadPanel,dispensePanel;
    private JScrollPane scrollPaneForDispenser;
    private BufferedImage napkinImg,knifeImg,forkImg,spoonImg = null;
    Image napkinImgScl,knifeImgScl,forkImgScl,spoonImgScl = null;
    private JButton fiveDollarBtn,tenDollarBtn,twentyDollarBtn;
    private JButton napkinBtn,knifeBtn,forkBtn,spoonBtn;
    private JLabel keypadTitle,changeDisplayTitle,dispenserTitle,napkinItemCountLabel,forkItemCountLabel,spoonItemCountLabel,knifeItemCountLabel;
    private JTextField keyPadDisplay;
    private JTextArea dispenseDisplay;
    private JTextArea changeDisplay;
    private JButton submitBtn,clearBtn;
    private static long till=0;
    public static int forkCount=20;
    public static int knifeCount=20;
    public static int spoonCount=20;
    public volatile static int napkinCount=20;

    private Result result;

    public VendingMachine() throws HeadlessException {
        this.initializeComponents();
        this.setLayouts();
        this.setColors();
        this.setFonts();
        this.addComponents();
        this.addListeners();
        this.setSizes();
    }


    private void initializeComponents() {
        this.setTitle("Vending Machine");
        itemsPanel = new JPanel();
        keyPadPanel = new JPanel();
        dispensePanel = new JPanel();
        keypadTitle = new JLabel("KeyPad");
        keypadTitle.setFont(new Font("Arial",Font.BOLD,20));
        changeDisplayTitle = new JLabel("Change");
        changeDisplayTitle.setFont(new Font("Arial",Font.BOLD,20));
        dispenserTitle = new JLabel("Please collect items here : ");
        dispenserTitle.setFont(new Font("Arial",Font.BOLD,20));
        napkinItemCountLabel = new JLabel("20");
        knifeItemCountLabel = new JLabel("20");
        forkItemCountLabel = new JLabel("20");
        spoonItemCountLabel = new JLabel("20");

        this.initializeImages();
        dispenseDisplay = new JTextArea("",1,1);
        dispenseDisplay.setFont(new Font("Arial",Font.BOLD,15));
        dispenseDisplay.setEditable(false);
        dispenseDisplay.setAutoscrolls(true);
        scrollPaneForDispenser = new JScrollPane(dispenseDisplay);
        scrollPaneForDispenser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        changeDisplay = new JTextArea("",1,1);
        changeDisplay.setFont(new Font("Arial",Font.BOLD,15));
        changeDisplay.setEditable(false);

        keyPadDisplay = new JTextField("",10);
        keyPadDisplay.setFont(new Font("Arial",Font.BOLD,30));
        keyPadDisplay.setEditable(false);
        keyPadPanel = new JPanel();
        fiveDollarBtn = new JButton("α");
        tenDollarBtn = new JButton("β");
        twentyDollarBtn = new JButton("γ");
        napkinBtn = new JButton("N");
        knifeBtn = new JButton("K");
        forkBtn = new JButton("F");
        spoonBtn = new JButton("S");
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
    }

    private void initializeImages() {
       try{
           napkinImg = ImageIO.read(new File("src/images/NapkinTOC.jpeg"));
           napkinImgScl = napkinImg.getScaledInstance(100,100,Image.SCALE_DEFAULT);
           knifeImg = ImageIO.read(new File("src/images/KnifeTOC.jpeg"));
           knifeImgScl = knifeImg.getScaledInstance(100,100,Image.SCALE_DEFAULT);
           forkImg = ImageIO.read(new File("src/images/ForkToc.jpeg"));
           forkImgScl = forkImg.getScaledInstance(100,100,Image.SCALE_DEFAULT);
           spoonImg = ImageIO.read(new File("src/images/spoonTOc.jpeg"));
           spoonImgScl = spoonImg.getScaledInstance(100,100,Image.SCALE_DEFAULT);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }


    private void addComponents() {
        itemsPanel.add(new JLabel(new ImageIcon(napkinImgScl)));
        itemsPanel.add(new JLabel("N  : $10- β"+" "+"# :"));
        itemsPanel.add(napkinItemCountLabel);
        itemsPanel.add(new JLabel(new ImageIcon(knifeImgScl)));
        itemsPanel.add(new JLabel("K  : $25- βγ"+" "+"# :"));
        itemsPanel.add(knifeItemCountLabel);
        itemsPanel.add(new JLabel(new ImageIcon(forkImgScl)));
        itemsPanel.add(new JLabel("F : $15- βα"+" "+"# :"));
        itemsPanel.add(forkItemCountLabel);
        itemsPanel.add(new JLabel(new ImageIcon(spoonImgScl)));
        itemsPanel.add(new JLabel("S : $20- γ"+" "+"# :"));
        itemsPanel.add(spoonItemCountLabel);
        keyPadPanel.setPreferredSize(new Dimension(10,0));

        keyPadPanel.add(keypadTitle);
        keyPadPanel.add(napkinBtn);
        keyPadPanel.add(knifeBtn);
        keyPadPanel.add(forkBtn);
        keyPadPanel.add(spoonBtn);
        keyPadPanel.add(fiveDollarBtn);
        keyPadPanel.add(tenDollarBtn);
        keyPadPanel.add(twentyDollarBtn);
        keyPadPanel.add(submitBtn);
        keyPadPanel.add(clearBtn);
        keyPadPanel.add(keyPadDisplay);


        dispensePanel.setPreferredSize(new Dimension(400,60));
        dispensePanel.setLayout(new GridLayout(4,1,0,0));
        dispensePanel.add(dispenserTitle);
        dispensePanel.add(scrollPaneForDispenser);
        dispensePanel.add(changeDisplayTitle);
        dispensePanel.add(changeDisplay);
        this.add(itemsPanel, BorderLayout.NORTH);
        this.add(keyPadPanel, BorderLayout.CENTER);
        this.add(dispensePanel, BorderLayout.EAST);
    }

    private void addListeners() {
        fiveDollarBtn.addActionListener(this);
        tenDollarBtn.addActionListener(this);
        twentyDollarBtn.addActionListener(this);
        napkinBtn.addActionListener(this);
        knifeBtn.addActionListener(this);
        forkBtn.addActionListener(this);
        spoonBtn.addActionListener(this);
        submitBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    private void setLayouts() {
        this.setLayout(new BorderLayout(90,20));
        itemsPanel.setLayout(new GridLayout(2,2));
        keyPadPanel.setLayout(new GridLayout(0,3));
        dispensePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
    }



    private void setFonts() {
        Font font = new Font("Arial",Font.BOLD,15);
        fiveDollarBtn.setFont(font);
        tenDollarBtn.setFont(font);
        twentyDollarBtn.setFont(font);
        napkinBtn.setFont(font);
        knifeBtn.setFont(font);
        forkBtn.setFont(font);
        spoonBtn.setFont(font);
        submitBtn.setFont(font);
        clearBtn.setFont(font);
    }

    private void setColors() {
        itemsPanel.setBackground(Color.WHITE);
        keyPadPanel.setBackground(Color.LIGHT_GRAY);
        fiveDollarBtn.setBackground(Color.WHITE);
        tenDollarBtn.setBackground(Color.WHITE);
        twentyDollarBtn.setBackground(Color.WHITE);
        napkinBtn.setBackground(Color.WHITE);
        knifeBtn.setBackground(Color.WHITE);
        forkBtn.setBackground(Color.WHITE);
        spoonBtn.setBackground(Color.WHITE);
        submitBtn.setBackground(Color.WHITE);
        clearBtn.setBackground(Color.WHITE);
    }

    private void setSizes() {
        this.setSize(1024,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fiveDollarBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "α");
        }
        if (e.getSource() == tenDollarBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "β");
        }
        if (e.getSource() == twentyDollarBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "γ");
        }
        if (e.getSource() == napkinBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "N");
        }
        if (e.getSource() == knifeBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "K");
        }
        if (e.getSource() == forkBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "F");
        }
        if (e.getSource() == spoonBtn) {
            keyPadDisplay.setText(keyPadDisplay.getText() + "S");
        }
        if (e.getSource() == clearBtn) {
            keyPadDisplay.setText("");
            dispenseDisplay.setText("");
        }

        if (e.getSource() == submitBtn) {
            String input = keyPadDisplay.getText();

            if (input.isEmpty())
            {
                System.out.println("You must enter coins and an item. Please restart.\n");
                input=keyPadDisplay.getText();
            }
            else {
                LinkedList<String> inputString = new LinkedList<String>();
                for (int i = 0; i < input.length(); i++) {
                    inputString.add(input.substring(i, i + 1));
                }

                TuringMachine turingMachine = new TuringMachine(inputString);
                this.result = turingMachine.runResult();
                long tillResult = result.getTill();
                boolean isOwner = result.isOwner();
                till = till + tillResult;

                if (isOwner==true){
                    JOptionPane.showMessageDialog(this, "Welcome Owner, Till is "+ till,"Owner Information",JOptionPane.INFORMATION_MESSAGE); //dialog box to show items along with till
                    till = 0;//resetting the till after telling owner to implement "resetting till"
                }else {
                    dispenseDisplay.setText(dispenseDisplay.getText()+"\n Items: ");
                    for(String element: result.getActions()){
                        dispenseDisplay.setText(dispenseDisplay.getText()+"\n" +element);
                    }
                    changeDisplay.setText("$"+ String.valueOf(result.getChange()));
                    napkinItemCountLabel.setText(String.valueOf(napkinCount));
                    forkItemCountLabel.setText(String.valueOf(forkCount));
                    knifeItemCountLabel.setText(String.valueOf(knifeCount));
                    spoonItemCountLabel.setText(String.valueOf(spoonCount));
                }
            }
        }
    }

    public static long getTill() {
        return till;
    }

    public static void setTill(long till) {
        VendingMachine.till = till;
    }

    public static synchronized void updateForkCount(int count) {
        forkCount = count;
    }public static synchronized void updateKnifeCount(int count) {
        knifeCount = count;
    }public static synchronized void updateSpoonCount(int count) {
        spoonCount = count;
    }public static synchronized void updateNapkinCount(int count) {
        napkinCount = count;
    }



    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        new VendingMachine();
    }
}
