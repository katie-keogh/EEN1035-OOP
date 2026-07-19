#include <iostream>
using namespace std;



bool monkeyTrouble(bool aSmile, bool bSmile){
    return aSmile == bSmile;
}

int main() {
    cout << monkeyTrouble(true, true) << "\n";
    cout << monkeyTrouble(false, false) << "\n";
    cout << monkeyTrouble(true, false) << "\n";
    return 0;
}