entity.Food Guess
#### The problem

#### Basic Flow
This Application basic flow uses the flow describe bellow to learn how to guess foods based on questions abou then.

![Alt text](GuessFood.svg "Guessflow")

First Step , user answer a question and then we filter wich foods are true for that question, and see if there are more than 1 possible matches.

If we only found one food, the job is done. Otherwise if there are more then one possible answer we need to filter the foods again. For that we filter wich questions still are relevant and repeat the first step. In case that no food match, we ask user to teach as a new question and how to answer it for all previous foods.

Example
Questions : 
["is pasta?", "is Italian", "is Japanese", "is Round", "has tomatoes", "has noodles"]
Foods :
    Pizza [true, true, false, true, true]
    Yakisoba [true, false, true, false, false, true]
    Nhoque [true, true, false, false, true, false]
    Lasagna [true, true, false, false, true, false]

##### Implementation
    For every question interate trought N food once filtering wich has true. The filtering funcion interates trought and array of questions.
    fiven q as number of questions and f number of foods
    we can represent this problema as matrix of (q, f)
    
              Pizza, Nhoque, Yakisoba, Lasagna
    pasta       T       T       T       T
    Italian     T       T       F       T
    Japanese    F       F       T       F
    Round       T       F       F       F
    tomatoes    T       F       F       T
    noodles     F       F       T       F

To improve the algorithim eficiency whe need to make questions where more foods will be discarted. Therefore we can create an heuristic base on the absolute difference of Trues or Falses  amount and optmize for 0 since is this we would reduce de possibilities by half.
In the given example, learning if the user wants pasta would give an heuristic value of 3, and in the next question we still have the same amount of foods. Meanwhile, the tomatoes questions would give an heustic value of 0, and in this case would split the possibilities to either [Pizza, Lasagna] or [Yakisoba, Nhoque].

To keep track of best heuristc values we will be using a [priority queue](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html), store questions and ordering by the heuristic value providing always the best question when popping an object.

#### PseudoCode Setps 
PriorityQueue : questions
List<String> :  foods
1 - Pop question -> Show entity.Question
2 - Save Answer -> Filter Foods Based on entity.Question
3 - How many foods?
    - 1    -> Show Answer
    - 0    -> Receive new question -> Add to question 
    - else -> Return step 1

Iterate trought foods is O(N).
 By saving one index reference for questions in the same order they are in foods, we can create a function for matching questions and foods with O(N).

1 - (extract from min heap in java is O(Log(N)))
2 - (O(N))
3 - (insert in min heap in java is O(Log n))
total = Log(N) + O(N)
In the worst case questions only remove one item from foods, assuming user created a relevant questions, making that there are no better questions and there for we need to ask all of then, repeting this cicle for the same amount of questions.
Best case all questions cut foods in half.
###Check this
Worst -> O (n log(n) + n²)
Best  -> O (log(n)² + n log(n))
