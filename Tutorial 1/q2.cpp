#include <iostream>
using namespace std;

string helloName(string name){
    return "Hello " + name + "!";

}

int main() {
    string name = "Meep";

    cout << helloName(name) << "\n";
    return 0;
}