#include <iostream>
#include "Alcoholic.h"
using namespace std;

Alcoholic::Alcoholic( string name, int millilitre, float alcoholPercentageOfADrink): 
Drink(name, millilitre), alcoholPercentage(alcoholPercentageOfADrink)
{}

void Alcoholic::setAlcoholPercentage(float newPercentage) {
    alcoholPercentage = newPercentage;
}
