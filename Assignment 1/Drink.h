#ifndef DRINK_H
#define DRINK_H

#include <string>
using namespace std;

class Drink {
private:
    string name;
    int volume;

    //Point 17
    int productCode;
    void construct();

protected:
    static int nextProductCode;


public:
    Drink(string, int);
    ~Drink();

    Drink operator + (Drink);

    void info() const;
    string nonvirtualType() { return "Drink";};
    string getName() const;
    int getVolume() const;
    friend void setVolume(Drink &, int);
    friend void setName(Drink &, string);
    void updateName(Drink &, string);
};

#endif