#include <iostream> 
using namespace std;
void stringReverse(int len, char *inputString[]){

    cout << "There are " << len-1 << " strings\nThe number of strings with:\n";

    int bin[10];
    for(int i = 0; i < 10; i++){
        bin[i] = 0;
    }

    for(int i = 1; i < len; i++){
        string s = inputString[i];
        bin[s.length()]++;
    }

    for(int i = 1; i < 10; i++){
        cout << "Length  " << i << " characters " << bin[i] << "\n";
    }



    cout << "\n";


}

int main(int argc, char *argv[]){

    // cout << argv[1] << "\n";
    // cout << argc << "\n";

    stringReverse(argc, argv);

    return 0;
}