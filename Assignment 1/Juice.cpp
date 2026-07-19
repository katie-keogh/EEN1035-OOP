#include "Juice.h"
#include <iostream>

using namespace std;

Juice::Juice(string name, int mililitres, string flavour): Drink(name, mililitres), flavour(flavour){
    cout << "[    Juice: " << flavour << " " << getName() << ", " << getVolume() << "ml was created]" << endl;
};

string Juice::getFlavour() const {
    return flavour;
}