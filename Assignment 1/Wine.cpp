#include <iostream>
#include "Wine.h"
using namespace std;

Wine::Wine(string name, int millilitre, double alcoholPercetangeOfADrink, string typeOfGrape)
:Alcoholic(name, millilitre, alcoholPercetangeOfADrink), grape(typeOfGrape){}

void Wine::info() const{
    cout << "Wine: " << getName() << " with "  << alcoholPercentage << "% and " << getVolume() << "ml." << endl;
}

float Wine::getAlcoholPercentage(){ return alcoholPercentage; };

void Wine::setAlcoholPercentage(float percentage){ 
    alcoholPercentage = percentage;
};

bool Wine::operator == (Wine w){
    cout << w.getName() << " + " << getName() << endl;
    if(w.getName() == getName() && w.grape == grape){
        return true;
    } else {
        return false;
    }
}