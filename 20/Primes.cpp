#include <stdio.h>
#include <iostream>
#include <vector>


std::vector<int> find_primes(int start, int end){
    std::vector<int> primes;
    while(start < end){
        bool found = false;
        for(int i = 2; i < start/2; i++){
            if(start % i == 0){
                found = true;
                break;
            }

            
        }
        if(!found){
            primes.push_back(start);
        }
            
        start++;
    }

    return primes;
};


int main() {
    std::vector<int> primes = find_primes(40,150);
    for(int& value: primes){
        std::cout << " " << value;
    }
    return 0;
}