**Memory Card Match Game (Java Swing)**

This is a Memory Card Matching Game built using Java Swing.
Players flip cards to find matching image pairs.

**The game supports:**
Single Player vs AI
Two Player (Friends) Mode
Multiple image categories
Score tracking and winner announcement

The goal is to match more pairs than the opponent before all cards are revealed.

**Game Features**

**1. Game Modes**
Play with AI → Compete against a computer player with memory logic.
Play with Friends → Two human players take turns.

**2. Categories**
Players can choose from:
Flower
Bird
Social Media
Animal
Fruit

Each category contains 8 image pairs (16 cards total).

**3. Gameplay Rules**
Players flip two cards per turn.
If the cards match, the player:
Earns 1 point
Gets another turn
If the cards do not match:
Cards flip back
Turn switches to the opponent
The game ends when all cards are matched.
The player with the highest score wins.

**AI Logic**

**The AI player:**
Remembers previously revealed cards
Chooses known matching pairs when possible
Otherwise selects random enabled cards

This simulates a basic memory-based intelligent opponent.

**Technologies Used**

Java
Java Swing (GUI)
Event Handling
Collections Framework
Timer-based animation
