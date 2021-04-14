#### The problem
This application is a guessing food game! 
The user will be presented with a characteristic and should confirm or deny it, 
and based on the answers we should give the correct answer.

#### Basic Flow
The following image describes the steps to learn how to guess foods based on questions about then.

![Alt text](GuessFood.svg)

We will solve this question using a binary tree with the following steps
First we add the root node, with a question and two possible answers.
Then Ask the current question, if next questions is null, try a guess, else ask next question based on the last answer, and move the answer until a guess is possible.
In case os failure, we change the current node on the tree for a questions leading to the a new scenario given by user.
#### Execution Example
Left branch = YES
Right branch = NO

####Round 1 : User thinking in Ice Cream answer [pasta: no,  cake: no]
```
      Pasta?
    /        \
 Lasagna     Cake


User Answer : Ice cream is eaten cold and cake is not.
```
####Round 2 : User thinking in Bananas answer [pasta: no, eaten cold: no, cake: no]
```


      Pasta?
    /        \
 Lasagna     is eaten cold?
            /              \
        Ice Cream         Cake   


User Answer : Banana is fruit and cake is not.
```
Round 3 Final state.
```
      Pasta?
    /        \
 Lasagna     is eaten cold?
            /              \
        Ice Cream      is Fruit?
                      /           \
                Banana           Cake    
```

Each question changes a leaf into a new question asumming the the old leaf is false for the new question and true for new food given by use.