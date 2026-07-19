#include <iostream>
using namespace std;

void square(int &value){
    value = value * value;
}

int main(){
    int value = 10;

    cout << "Value: " << value << "\n";
    square(value);
    cout << "Square: " << value << "\n";


    return 0;
}