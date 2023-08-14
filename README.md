# Bowling Kata with TDD using Java and JUnit 5
This project implements the Bowling Kata using Test-Driven Development (TDD) principles. It demonstrates how to build a 
bowling score calculator in Java, focusing on proper software design, testing practices, and using a custom C-style 
linked list for efficient score tracking.

## Table of Contents
- [Introduction](#introduction)
- [Custom Linked List](#custom-linked-list)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The Bowling Kata is a classic coding exercise that challenges your ability to write clean, maintainable code and create 
an efficient algorithm to calculate bowling scores. This project follows the principles of Test-Driven Development, 
which means that tests are written before the code and serve as both documentation and validation of the implementation.

## Custom Linked List
One of the distinctive features of this Bowling Kata project is the implementation of a custom linked list, designed 
specifically to manage and calculate bowling scores efficiently. This custom linked list serves as the underlying data 
structure for tracking and updating the scores of individual frames in a bowling game.

### Why a Custom Linked List?
While Java provides built-in collections like **ArrayList** and **LinkedList**, creating a custom linked list offers several 
advantages. By tailoring the linked list to the specific requirements of the bowling score calculation, you gain greater
control over how data is organized and accessed. This allows for optimized scoring calculations, better encapsulation of
game logic, and improved readability of the codebase.

### Key Features
The custom linked list (**LinkedListScores**) exhibits the following key features:

- **Frame Structure**: Each node of the linked list represents a frame in the bowling game. It encapsulates information such
as the number of pins knocked down in each roll (pinsA, pinsB, and pinsC), the frame's total score (frameTotal), and 
whether the frame has been calculated (calculated).

- **Bonus Handling**: The linked list enables efficient tracking of bonus calculations for strikes and spares. It allows 
for the identification of frames that require additional calculations based on the game's rules.

- **Modularity**: The linked list is designed to seamlessly integrate with the Game class, providing a clear separation 
of concerns and facilitating the calculation and updating of frame scores.

### How to Interact with the Custom Linked List
The custom linked list is intricately woven into the implementation of the Game class. Each instance of the Game class 
holds a reference to the first frame in the linked list (**firstFrame**). As the game progresses and scores are 
calculated, the linked list's nodes are updated to reflect the evolving state of the game.

For example, the **calculateFrameTotal** method in the LinkedListScores class is crucial for determining the total score
of each frame while considering bonuses. Similarly, the **updateFrameTotals** method iterates through the linked list, 
calculating the scores for frames with spares or strikes.
## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven (for building and running tests)
### Installation
1. Clone the repository to your local machine:
```
git clone https://github.com/luisalvarez246/bowling-kata.git bowling-kata
cd bowling-kata
```
2. Build the project using Maven:
```
mvn clean install
```
## Usage
To use the Bowling Kata project, you can explore the org.game package, which contains the implementation of the bowling
game and the custom linked list data structure.

## Running Tests
To run the tests and ensure the correctness of the implementation, use the following command:
```
mvn test
```
This command will execute the JUnit 5 tests written for the Bowling Kata, validating the behavior of the game logic and 
linked list.
## Contributing
Contributions to this project are welcome! If you find any issues or have suggestions for improvements, please feel free
to open an issue or submit a pull request.
## License
CC0: Public Domain