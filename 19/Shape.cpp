#include <iostream>

using namespace std;

class Shape{
private:
    string type;
    float X;
    float Y;
public:
    Shape(string type, float X, float Y): type(type), X(X), Y(Y){

    }
    virtual float getArea() = 0;

    float getX(){
        return X;
    }

    float getY(){
        return Y;
    }

    void deets() const {
        cout << "detailszzzzz" << endl;
    }
};

class Circle: public Shape{
private:

public:
    Circle(float X, float Y): Shape("circle", X, Y){

    }

    void display(){
        cout << "ugh" << endl;
    }

    float getArea(){
        return 15;
    }

    friend void printDetails(const Shape *);

};

class Rectangle: public Shape{
public:
    Rectangle(float X, float Y): Shape("rectangle", X, Y){}
    
    float getArea(){
        return getX()*getY();
    }
};

void printDetails(const Shape *s){
    s->deets();
}

int main(){

    Circle *c = new Circle(50, 50);
    c->display();

    Rectangle *r = new Rectangle(5, 10);
    cout << r->getArea() << endl;

    printDetails(c);


}