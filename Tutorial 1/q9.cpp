#include <iostream>
using namespace std;

int factorial(int x){
    if(x <= 1){
        return 1;
    }
    
    int sum = x;

    for(int i = 1; i < x; i++){
        sum = sum * i;
    }

    return (x * factorial(x - 1));
}

int main(int argc, char *argv[]){

    cout << factorial(1) << "\n"; 
    cout << factorial(2) << "\n"; 
    cout << factorial(5) << "\n"; 
    
    return 0;
}