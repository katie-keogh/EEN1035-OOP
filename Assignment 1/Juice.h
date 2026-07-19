#ifndef JUICE_H
#define JUICE_H

#include "Drink.h"


class Juice : public Drink {
    private:
    string flavour;

    public:
    Juice(string, int, string);
    string nonvirtualType() { return "Juice";};
    virtual string getFlavour() const;
    ~Juice() {};

};

#endif