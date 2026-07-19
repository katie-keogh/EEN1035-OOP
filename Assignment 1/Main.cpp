#include "Drink.h"
#include "Wine.h"
#include "Juice.h"
#include <iostream>
#include <memory>
#include <vector>
#include <algorithm>
using namespace std;


void outputFunction(Wine wine){
    wine.info();
}

void displayArray(Wine arrayOfDrinks[], int len){
    int lenOfArray = len;
    for (int i = 0; i < lenOfArray; ++i) {
        arrayOfDrinks[i].info();
    }

}

void byValueAndReference(Wine value, Wine &reference){
    reference.setAlcoholPercentage(0.80);
    value.setAlcoholPercentage(0.50);
}

void displayConst(const Wine& drink){
    drink.info();
}

void setVolume(Drink &drink, int amount) { 
    drink.volume = amount;
};

//Point 13
Juice operator + (const Juice &left, const Juice &right){
    double combinedVolume = left.getVolume() + right.getVolume();
    string combinedName = left.getName() + " and " + right.getName();
    string combinedFlavour = left.getFlavour() + " and " + right.getFlavour();
    return  Juice(combinedName, combinedVolume, combinedFlavour);
}

int main(){
    
    //Point 4 - new
    Drink *water = new Drink("Water", 500);
    Drink *dilute = new Drink("Dilute", 50);
    Wine *whiteWine = new Wine("White wine", 750, 0.13, "white grapes");
    const Wine *champagne = new Wine("Champagne", 750, 0.13, "red grapes");
    Wine *redWine = new Wine("Red Wine", 750, 0.13, "red grapes");
    Wine *redWineMagnum = new Wine("Red Wine", 1000, 0.13, "red grapes");
    Juice *orangeJuice = new Juice("Orange Juice", 1000, "orange");
    Juice *appleJuice = new Juice("Apple Juice", 500, "apple");

    //Point  8 - an array 
    Wine arrayOfDrinks[3] = {*champagne, *redWine, *whiteWine};
    int lenOfArray = sizeof(arrayOfDrinks) / (sizeof(arrayOfDrinks[0]));

    // For overloading 
    Juice *overload1 = new Juice("Over loaded juice 1", 1000, "overload 1");
    Juice *overload2 = new Juice("Over loaded juice 2", 750, "overload 2");
    Juice *overload3 = new Juice("Over loaded juice 3", 500, "overload 3");
    
    //Point 18 - Vector
    vector<Wine> vectOfWine;
    vectOfWine.push_back(*whiteWine);
    vectOfWine.push_back(*champagne);
    vectOfWine.push_back(*redWine);
    vectOfWine.push_back(*redWineMagnum);
    
    cout << endl;

    //Point 5
    cout << "---- POINT 5 -----" << endl;
    cout << "Before pass by value: ";
    redWine->info(); // Point 10 - dynamic binding
    byValueAndReference(*redWine, *redWine);
    cout << "After pass by value: ";
    redWine->info();
    cout << endl;

    //Point 6
    cout << "---- POINT 6 -----" << endl;
    champagne->info();
    displayConst(*champagne);
    cout << endl;

    //Point 7 
    cout << "---- POINT 7 -----" << endl;
    setVolume(*water, 1000);
    water->info();
    cout << endl;

    //Point 8
    cout << "---- POINT 8 -----" << endl;
    displayArray(arrayOfDrinks, lenOfArray);
    cout << endl;

    //Point 10
    cout << "---- POINT 10 -----" << endl;
    cout << whiteWine->nonvirtualType() << endl;
    cout << redWine->nonvirtualType() << endl;
    cout << orangeJuice->nonvirtualType() << endl;
    cout << water->nonvirtualType() << endl;
    cout << endl;
    
    //Point 11 - overloading + and ==
    cout << "---- POINT 11 -----" << endl;
    Drink *mix = new Drink(*water + *dilute);
    mix->info();
    if (*redWine == *redWineMagnum){ 
        cout << redWine->getName() << " and " << redWineMagnum->getName() << " are equal!" << endl;
    }
    cout << endl;

    // Point 12 - overloading = 
    cout << "---- POINT 12 -----" << endl;
    overload1->info();
    overload2->info();
    overload3->info();
    cout << "Performing the assignment operator now " << endl;
    overload1 = overload2 = overload3;
    overload1->info();
    overload2->info();
    overload3->info();
    cout << endl;

    // Point 13 - Non-Member
    cout << "---- POINT 13 -----" << endl;
    orangeJuice->info();
    Juice orangeAndApple = *orangeJuice + *appleJuice;
    orangeAndApple.info();
    cout << endl;

    // Point 14 - passing itself
    cout << "---- POINT 14 -----" << endl;
    water->info();
    water->updateName(*water, "Sparkling water");
    water->info();
    cout << endl;

    // Point 15 - modified copy constructor
    cout << "---- POINT 15 -----" << endl;
    Wine magnumCopy(*redWineMagnum);
    byValueAndReference(magnumCopy, magnumCopy);
    magnumCopy.info();
    redWineMagnum->info();
    cout << endl;

    //Point 16 - cast
    cout << "---- POINT 16 -----" << endl; 
    cout << whiteWine->getName() << " alchol percentage as a float: " << whiteWine->getAlcoholPercentage() << endl;
    cout << whiteWine->getName() << " alchol percentage as an int: " << static_cast<int>(whiteWine->getAlcoholPercentage()) << endl;
    cout << endl;

    cout << "---- POINT 19 -----" << endl;
    for_each(vectOfWine.begin(), vectOfWine.end(), outputFunction);
    cout << endl;

    //Point 20 - Shared Pointer
    cout << "---- POINT 20 -----" << endl;
    shared_ptr<Wine> p(whiteWine);
    cout << "Initial use_count: " << p.use_count() << endl; // 1
    shared_ptr<Wine> q(p);
    cout << "Added another shared_ptr : " << p.use_count() << endl; // 2
    q.reset();
    cout << "Reset the second shared_ptr: " << p.use_count() << endl; // 1
    p.reset();
    cout << "Reset the initial shared_ptr: " << p.use_count() << endl; // 0

    cout << endl;


    //4 + 9 Point 
    cout << "---- POINT 4 + 9 -----" << endl;
    delete water;
    delete redWine;
    delete champagne;
    delete dilute;
    delete mix;
    whiteWine->~Wine();
    orangeJuice->~Juice();

    cout << endl;
    cout << endl;
    return 0;
}

