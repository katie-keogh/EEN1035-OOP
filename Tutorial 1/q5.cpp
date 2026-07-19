#include <iostream>
using namespace std;

int countEvens(int list[], int len){
    int numberOfEvens = 0;
    for(int i = 0; i < len; i++){
        if(list[i] % 2 == 0){
            numberOfEvens++;
        }
    }

    return numberOfEvens;
}

int main(){
    int x[] = {2, 1, 2, 3, 4};
    int y[] = {2, 2, 0};
    int z[] = {1, 3, 5};

    cout << countEvens(x, 5) << "\n";
    cout << countEvens(y, 3) << "\n";
    cout << countEvens(z, 3) << "\n";

    return 0;    
}