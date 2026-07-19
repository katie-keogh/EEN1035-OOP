#include <iostream>

using namespace std;

class Person{
private: 
    string name;
    int age;
    friend void clearName(Person &);
public:
    Person(string, int);
    Person(const Person &);
    virtual bool operator == (Person);
    virtual void display();
};

Person::Person(string name, int age): name(name), age(age){

}

Person::Person(const Person &p): name(p.name), age(p.age){

}

bool Person::operator == (Person p){

    cout << name.compare(p.name) << endl;
    return name == p.name;
}

void Person::display(){
    cout << " Person [" << name << "] who is [" << age << " years old]." << endl;
}

void clearName(Person &p){
    p.name = "";
}

int main(int argc, char const *argv[])
{
    Person *k = new Person("katie", 21);
    Person *k2 = new Person("katie", 21);

    cout << (&k == &k2) << endl;
    cout << ("katie" == "katie") << endl;

    Person p1("Alice", 30);
    Person p2("Alice", 25);
    Person p3("Bob", 30);

    // Comparing objects
    if (p1 == p2) { // Calls operator==
        cout << "p1 and p2 are equal." << endl;
    } else {
        cout << "p1 and p2 are not equal." << endl;
    }

    if (p1 == p3) { // Calls operator==
        cout << "p1 and p3 are equal." << endl;
    } else {
        cout << "p1 and p3 are not equal." << endl;
    }
    return 0;
}
