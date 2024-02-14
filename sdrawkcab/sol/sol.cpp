
#include <iostream>
#include <string>
#include <algorithm>

int main() {

    std::string line;
    
    std::getline(std::cin, line);
    
    std::reverse(line.begin(), line.end());

    std::cout << line;

    return 0;
}