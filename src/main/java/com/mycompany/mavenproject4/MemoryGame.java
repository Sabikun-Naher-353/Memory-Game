package com.mycompany.mavenproject4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MemoryGame {
    private JFrame mainFrame,modeFrame,categoryFrame,gameFrame;
    private JButton[] cardButtons;
    private ArrayList<String> images;
    private JButton firstCard,secondCard;
    private int firstIndex,secondIndex;
    private boolean playerOneTurn=true;
    private boolean isAI=false;
    private JButton turnIndicator;
    private int playerOneScore=0,playerTwoScore=0;
    private JLabel scoreLabel;
    private Color playerOneColor=new Color(42,52,57);
    private Color playerTwoColor=new Color(255,255,255);
    private Random random=new Random();
    private String selectedCategory;
    private Map<Integer,String>aiMemory=new HashMap<>();
    public MemoryGame(){
        showMainFrame();
    }
    
    private void showMainFrame(){
        mainFrame=new JFrame("Memory Card Match");
        mainFrame.setSize(600,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        JLabel title=new JLabel("Memory Card Match",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,24));
        mainFrame.add(title,BorderLayout.CENTER);
        Timer timer=new Timer(2000,e->{
            mainFrame.dispose();
            showModeSelection();
        });
        timer.setRepeats(false);
        timer.start();

        mainFrame.setVisible(true);
    } 
    
    private void showModeSelection(){
    modeFrame=new JFrame("Select Mode");
    modeFrame.setSize(600,600);
    modeFrame.setLayout(null); 
    JButton playAI=new JButton("Play with AI");
    playAI.setBounds(200,200,200,50); 
    playAI.setBackground(Color.decode("#73A5C6"));
    playAI.setForeground(Color.WHITE);
    playAI.addActionListener(e->{
        isAI=true;
        modeFrame.dispose();
        showCategorySelection();
    });
    JButton playFriend=new JButton("Play with Friends");
    playFriend.setBounds(200,300,200,50); 
    playFriend.setBackground(Color.decode("#73A5C6"));
    playFriend.setForeground(Color.WHITE);
    playFriend.addActionListener(e->{
        isAI=false;
        modeFrame.dispose();
        showCategorySelection();
    });
    JButton backButton=new JButton("Back");
    backButton.setBounds(20,20,80,30);
    backButton.addActionListener(e->{
        modeFrame.dispose();
        showMainFrame();
    });
    modeFrame.add(playAI);
    modeFrame.add(playFriend);
    modeFrame.add(backButton);
    modeFrame.setVisible(true);
    }
    
    private void showCategorySelection(){
        categoryFrame=new JFrame("Select Category");
        categoryFrame.setSize(600,600);
        categoryFrame.setLayout(null);
        String[] categories={"Flower","Bird","Social Media","Animal","Fruit"};
        int yPosition=100;
        for(String category:categories){
            JButton btn=new JButton(category);
            btn.setBounds(200,yPosition,200,50);
            btn.addActionListener(e->{
                playerOneScore=0;
                playerTwoScore=0;
                selectedCategory=category;
                categoryFrame.dispose();
                startGame();
            });
            categoryFrame.add(btn);
            yPosition+=80;
        }
        JButton backButton=new JButton("Back");
        backButton.setFont(new Font("Arial",Font.BOLD,12));
        backButton.setBounds(10,10,80,30);
        backButton.addActionListener(e->{
            categoryFrame.dispose();
            showModeSelection();
        });
        categoryFrame.add(backButton);
        categoryFrame.setVisible(true);
    }
private void startGame(){
    gameFrame=new JFrame("Memory Game");
    gameFrame.setSize(600,600);
    gameFrame.setLayout(null);
    JPanel gamePanel=new JPanel();
    gamePanel.setLayout(new GridLayout(4,4,5,5));
    gamePanel.setBounds(70,60,450,450);
    cardButtons=new JButton[16];
    images=generateImages(selectedCategory);
    for(int i=0;i<16;i++){
        JButton card=new JButton("?");
        cardButtons[i]=card;
        int index=i;
        card.addActionListener(e->flipCard(card,index));
        gamePanel.add(card);
    }
    turnIndicator=new JButton("Player 1's Turn");
    turnIndicator.setBounds(200,10,200,40);
    turnIndicator.setBackground(playerOneColor);
    turnIndicator.setForeground(Color.WHITE);
    turnIndicator.setEnabled(false);
    JButton backButton=new JButton("Back");
    backButton.setBounds(10,10,80,30);
    backButton.addActionListener(e->{
        gameFrame.dispose();
        showCategorySelection();
    });
    scoreLabel=new JLabel("Player 1 (BLACK): 0 | Player 2 (WHITE): 0",SwingConstants.CENTER);
    scoreLabel.setBounds(150,520,300,30);
    gameFrame.add(turnIndicator);
    gameFrame.add(backButton);
    gameFrame.add(scoreLabel);
    gameFrame.add(gamePanel);
    gameFrame.setVisible(true);
    if(isAI){
        playerOneTurn=new Random().nextBoolean();
        if(playerOneTurn){
            turnIndicator.setText("Player 1's Turn");
            turnIndicator.setBackground(playerOneColor);
        }
        else{
            turnIndicator.setText("AI's Turn");
            turnIndicator.setBackground(playerTwoColor);
        }
        if(!playerOneTurn){
            performAITurn();
        }
    }
    }
    private ArrayList<String>generateImages(String category){
        ArrayList<String> list=new ArrayList<>();
        String[] imageNames=new String[16];
        switch (category){
            case "Flower":
                imageNames=new String[]{
                        "flower1.png","flower2.png","flower3.png","flower4.png",
                        "flower5.png","flower6.png","flower7.png","flower8.png",
                        "flower1.png","flower2.png","flower3.png","flower4.png",
                        "flower5.png","flower6.png","flower7.png","flower8.png"
                };
                break;
            case "Bird":
                imageNames=new String[]{
                        "bird1.png","bird2.png","bird3.png","bird4.png",
                        "bird5.png","bird6.png","bird7.png","bird8.png",
                        "bird1.png","bird2.png","bird3.png","bird4.png",
                        "bird5.png","bird6.png","bird7.png","bird8.png"
                };
                break;
            case "Social Media":
                imageNames=new String[]{
                        "social1.png","social2.png","social3.png","social4.png",
                        "social5.png","social6.png","social7.png","social8.png",
                        "social1.png","social2.png","social3.png","social4.png",
                        "social5.png","social6.png","social7.png","social8.png"
                };
                break;
            case "Animal":
                imageNames=new String[]{
                        "animal1.png","animal2.png","animal3.png","animal4.png",
                        "animal5.png","animal6.png","animal7.png","animal8.png",
                        "animal1.png","animal2.png","animal3.png","animal4.png",
                        "animal5.png","animal6.png","animal7.png","animal8.png"
                };
                break;
            case "Fruit":
                imageNames=new String[]{
                        "fruit1.png","fruit2.png","fruit3.png","fruit4.png",
                        "fruit5.png","fruit6.png","fruit7.png","fruit8.png",
                        "fruit1.png","fruit2.png","fruit3.png","fruit4.png",
                        "fruit5.png","fruit6.png","fruit7.png","fruit8.png"
                };
                break;
    }
        Collections.shuffle(Arrays.asList(imageNames));
        return new ArrayList<>(Arrays.asList(imageNames));
}
    
    private void flipCard(JButton card,int index){
        if(firstCard==null){
            firstCard=card;
            firstIndex=index;
            setCardImage(firstCard,images.get(index));
        }
        else if(secondCard==null&&card!=firstCard){
            secondCard=card;
            secondIndex=index;
            setCardImage(secondCard,images.get(index));
            checkMatch();
        }
    }
    
    private void setCardImage(JButton button,String imagePath){
        ImageIcon icon=loadImage(imagePath);
        if(icon!=null){
            Image img=icon.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
            button.setText("");
        }
        else{
            button.setText("?");
        }
    }
    
    private ImageIcon loadImage(String imagePath){
        String path="images/"+imagePath;
        java.net.URL imgURL=getClass().getClassLoader().getResource(path);
        if(imgURL!=null){
            return new ImageIcon(imgURL);
        }
        return null;
    }
    
    private void updateScoreLabel(){
        String currentPlayer=playerOneTurn?"Player 1's Turn":(isAI?"AI's Turn":"Player 2's Turn");
        turnIndicator.setText(currentPlayer);
        turnIndicator.setBackground(playerOneTurn?playerOneColor:playerTwoColor);
        String player2Name=isAI?"AI":"Player 2";
        scoreLabel.setText("Player 1 (BLACK): "+playerOneScore+" | "+player2Name+" (WHITE) "+playerTwoScore);
    }
    
    private void checkMatch(){
        Timer timer=new Timer(1000,e->{
            if(images.get(firstIndex).equals(images.get(secondIndex))){
                if(playerOneTurn){
                    playerOneScore++;
                    firstCard.setBackground(playerOneColor);
                    secondCard.setBackground(playerOneColor);
                }
                else{
                    playerTwoScore++;
                    firstCard.setBackground(playerTwoColor);
                    secondCard.setBackground(playerTwoColor);
                }
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
            }
            else{
                firstCard.setIcon(null);
                firstCard.setText("?");
                secondCard.setIcon(null);
                secondCard.setText("?");
                playerOneTurn=!playerOneTurn;
            }
            updateScoreLabel();
            firstCard=secondCard=null;
            if(isGameOver()){
                showWinner();
            }
            else if(isAI&&!playerOneTurn){
                performAITurn();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private boolean isGameOver(){
        for(JButton card:cardButtons){
            if(card.isEnabled()){
                return false;
            }
        }
        return true;
    }
    private void showWinner(){
        String winner;
        if(playerOneScore>playerTwoScore){
            winner="Player 1 Wins! (BLACK)";
        }
        else if(playerOneScore<playerTwoScore){
            winner="Player 2 Wins! (WHITE)";
        }
        else{
            winner="It's a Tie!";
        }
        JOptionPane.showMessageDialog(gameFrame,winner,"Game Over",JOptionPane.INFORMATION_MESSAGE);
        gameFrame.dispose();
        showMainFrame();
    }
    
    private void performAITurn(){
       Timer timer=new Timer(1000,e->{
            Integer firstPick=null, secondPick=null;
            for(Map.Entry<Integer,String>entry1:aiMemory.entrySet()){
                for(Map.Entry<Integer,String>entry2:aiMemory.entrySet()){
                    if(!entry1.getKey().equals(entry2.getKey())&&entry1.getValue().equals(entry2.getValue())&&cardButtons[entry1.getKey()].isEnabled()&&cardButtons[entry2.getKey()].isEnabled()){
                        firstPick=entry1.getKey();
                        secondPick=entry2.getKey();
                        break;
                    }
                }
                if(firstPick!=null){
                   break;
                } 
            }
            if(firstPick==null){
                do{
                    firstPick=random.nextInt(16);
                } 
                while(!cardButtons[firstPick].isEnabled());
            }
            flipCard(cardButtons[firstPick],firstPick);
            aiMemory.put(firstPick,images.get(firstPick));
            if(secondPick==null){
                do{
                    secondPick=random.nextInt(16);
                } 
                while(!cardButtons[secondPick].isEnabled()||secondPick.equals(firstPick));
            }
            flipCard(cardButtons[secondPick],secondPick);
            aiMemory.put(secondPick,images.get(secondPick));
        });
        timer.setRepeats(false);
        timer.start();
    }
    public static void main(String[] args) {
        new MemoryGame();
    }

}


