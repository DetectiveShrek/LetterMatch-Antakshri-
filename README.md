# Antakshari Game (Java Console Edition)

A text-based Antakshari word game written in Java.  
The player competes against the computer in a word chain game where each word must start with the last letter of the previous word. Bonus letters give extra points, and the player with the higher score after 5 rounds wins.  

---

## Features
- Loads a dictionary of valid words from `words.txt`  
- Player vs Computer gameplay  
- 5 rounds of play  
- Word validation (must exist in dictionary, start with the correct letter, and not be repeated)  
- Bonus letters system: each round generates 3 random bonus letters, each worth +2 points when used  
- Score tracking with a final winner  

---

## Project Structure
- AntakshariGame.java # Main game source file
- words.txt # Dictionary file with valid words (one per line)
- README.md # Project documentation

## Scoring Rules
- Base score = word length
- Each bonus letter in the word = +2 points
- Example:
  - Bonus letters: [a, m, k]
  - Word: market
  - Base score = 6
  - Bonus score = 6 (for a, m, k)
  - Total = 12 points
