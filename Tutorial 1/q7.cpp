#include <iostream> 
using namespace std;
void stringReverse(int len, char *string[]){

    cout << len << "\n";

    for(int i = len - 1; i > 0; --i){
        // cout << i << "\n";
        cout << string[i] << " ";
    }
    cout << "\n";

}

int main(int argc, char *argv[]){

    // cout << argv[1] << "\n";
    // cout << argc << "\n";

    stringReverse(argc, argv);

    return 0;
}