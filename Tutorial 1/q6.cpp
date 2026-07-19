#include <iostream>
using namespace std;


bool sum28(int list[], int len){
    int sumOf2s = 0;
    
    for(int i = 0; i < len; i++){
        if(list[i] == 2){
            sumOf2s+=2;
        }
    }

    if(sumOf2s == 8){
        return true;
    }
    return false;
}

int main() {
    int x[] = {2, 3, 2, 2, 4, 2};
    int y[] = {2, 3, 2, 2, 4, 2, 2};
    int z[] = {1, 2, 3, 4};

    cout << (bool) sum28(x, 6) << "\n";
    cout << sum28(y, 7) << "\n";
    cout << sum28(z, 4) << "\n";


    return 0;
}