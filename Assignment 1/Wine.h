#ifndef WINE_H
#define WINE_H

#include "Alcoholic.h"

class Wine: public Alcoholic {
private:
    string grape;

public:
    Wine(string, int, double, string);

    bool operator == (Wine);

    virtual float getAlcoholPercentage();
    virtual void setAlcoholPercentage(float);
    virtual void info() const;
    ~Wine() {};
};

#endif
