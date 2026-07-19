#include <stdio.h>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Sensor{
private:
    string name;
    string type;
    string units;
    float reading;
public:
    Sensor(string name, string type, string units, float reading): name(name), type(type), units(units), reading(reading){
        // cout << "A sensor [" + name + "] has just been created." << endl;
    }

    void display(){
        cout << "The sensor [" << name << "] has type [" << type << "] and value [" << reading << " " << units << "]." << endl;
    }

    ~Sensor(){
        // cout << "A sensor name [" + name << "] value ["  << reading << " was destoryed." << endl;
    }

    bool operator < (const Sensor& s) const {
        return (reading < s.reading ? true : false);
    }

    bool operator == (const Sensor& s) const {
        return (reading == s.reading ? true : false);
    }

    string getName(){
        return name;
    }

        
    float getReading(){
        return reading;
    }

    virtual string getDetails(){
        return ("Sensor [" + name + "] has type [" + type + "] and value [" + to_string(reading) + " " + units + "].");
    }
};

class Processor{

friend ostream &operator<<( ostream &stream, Processor p);
private:
    vector<Sensor> sensor_vec;
public:
    Processor(){}
    void addValue(Sensor s){
        this->sensor_vec.push_back(s);
    }
    void display(){
        sort(this->sensor_vec.begin(), this->sensor_vec.end());
        for(std::vector<Sensor>::iterator it = sensor_vec.begin(); it != sensor_vec.end(); ++it) {
            it->display();
        }
    }
    Sensor getMinimum(){
        sort(this->sensor_vec.begin(), this->sensor_vec.end());
        return this->sensor_vec.front();
    }
    Sensor getMaximum(){
        sort(this->sensor_vec.begin(), this->sensor_vec.end());
        return this->sensor_vec.back();
    }

    float getSum(string name){
        float sum = 0;
        for(vector<Sensor>::iterator it = sensor_vec.begin(); it != sensor_vec.end(); ++it){
            if(it->getName() == name){
                sum = sum + it->getReading();
            }
        }
        return sum;
    }

    
};
ostream &operator<<( ostream &stream, Processor p){
    for(std::vector<Sensor>::iterator it = p.sensor_vec.begin(); it != p.sensor_vec.end(); ++it) {
        stream << it->getDetails() << "\n";
    }
    return stream;
}

int main(){

    Sensor *s1 = new Sensor("Sensor 1", "temperature", "degrees", 25.4);
    // Sensor *s2 = new Sensor("Sensor 2", "temperature", "degrees", 30.4);
    // Sensor *s3 = new Sensor("Sensor 3", "temperature", "degrees", 12.4);
    // s1->display();

    cout << endl << endl;

    Processor *p = new Processor();
    p->addValue(Sensor("Sensor 1", "temperature", "degrees", 25.4));
    p->addValue(Sensor("Sensor 2", "temperature", "degrees", 30.4));
    p->addValue(Sensor("Sensor 3", "temperature", "degrees", 12.4));
    p->addValue(Sensor("Sensor 1", "temperature", "degrees", 50.4));

    string test = s1->getDetails();

    cout << "details " << test << endl;

    p->display();
    cout << "Max: ";
    p->getMaximum().display();
    cout << endl << "Min: ";
    p->getMinimum().display();
    cout << endl;
    cout << "sum : " << p->getSum("Sensor 1") << endl;

    cout << p << endl;


   // delete s1;
    return 0;
}