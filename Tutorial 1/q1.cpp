#include <iostream>

double getSum(double x, double y){
    return x + y;

}

double getDouble(double sum){
    return sum * sum;
}


bool checkIfSame(double x, double y){
    return x == y;
}


double sumDouble(double x, double y){
    double result;
    bool isSameNumber;

    isSameNumber = checkIfSame(x, y);

    if(isSameNumber){
        result = getDouble(getSum(x, y));
    } else {
        result = getSum(x, y);
    }

    return result;
}

int main() {
    double result = sumDouble(10, 10);
    std::cout << "Result: " << result << "\n";

    result = sumDouble(5, 10);
    std::cout << "Result: " << result << "\n";
    return 0;
}
