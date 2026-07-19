#include "Drink.h"
#include <iostream>
using namespace std;

int Drink::nextProductCode = 1;

//Point 17
void Drink::construct(){
    productCode = nextProductCode++;
}

Drink::Drink(string drinkName, int millilitre): name(drinkName), volume(millilitre) {
    construct();
    cout << "[ Drink " << name << " with " << volume << "ml and product code " << productCode << " created ]" << endl;
}

Drink::~Drink() {
    volume = 0; //pouring out the liquid 
    cout << "[ Drink " << name << " with " << volume << "ml destoryed]" << endl;
}

void Drink::info() const {
    cout << "Drink name: " << name << " with " << volume << "ml." << endl;
}

string Drink::getName() const { return name; };

int Drink::getVolume() const { return volume; };

Drink Drink::operator + (Drink d){
    return Drink(name, volume + d.volume);
}

void setName(Drink &drink, string newName) { drink.name = newName; };

void Drink::updateName(Drink &drink, string newName){setName(drink, newName);};
