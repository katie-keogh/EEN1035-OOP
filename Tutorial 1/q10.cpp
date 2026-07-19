#include <iostream>
using namespace std;

int* reverse(int listOfNums[], int lenOfList){
    int *reverse =  new int [lenOfList];
    int x = lenOfList-1;
    for (int i = 0; i < lenOfList; i++){
        reverse[x] = listOfNums[i];
        --x;
    } 
    return reverse;
}

int main(){

    int x[] = {11, 22, 33, 44, 55};
    int *result = reverse(x, 5);

    cout << x << "\n";

    for (int i = 0; i < 5; i++){
        cout << *(result+i) << " ";
    }
    cout << "\n";
    
    return 0;
}