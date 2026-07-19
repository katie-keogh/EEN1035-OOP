#ifndef ALCOHOLIC_H
#define ALCOHOLIC_H

#include <iostream>
#include "Drink.h"
using namespace std;

class Alcoholic: public Drink
{
  protected:
   float alcoholPercentage;

  public:    
    Alcoholic(string, int, float);
    virtual float getAlcoholPercentage() = 0;
    virtual void setAlcoholPercentage(float alcoholPercentage);
};
#endif