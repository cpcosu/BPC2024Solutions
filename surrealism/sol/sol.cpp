#include "bits/stdc++.h"

// {{|}|}

long gcd(long a, long b) 
{ 
    // Everything divides 0 
    if (a == 0) 
        return b; 
    if (b == 0) 
        return a; 
  
    // base case
    if (a == b) 
        return a; 
  
    // a is greater 
    if (a > b) 
        return gcd(a - b, b); 
    return gcd(a, b - a); 
} 
  

using namespace std;

class Fraction {
  public:
    long num;
    long den;
    Fraction(long newNum, long newDen) {
      long common = gcd(abs(newNum), newDen);
      num = newNum / common;
      den = newDen / common;
    }
    Fraction(long newNum) {
      num = newNum;
      den = 1;
    }
    Fraction() {
      num = 0;
      den = 1;
    }
    Fraction operator+(Fraction const& obj) const {
      long newDen = obj.den * den;
      long newNum = obj.num * den + num * obj.den;
      return Fraction(newNum, newDen);
    }
    Fraction operator-() const {
      return Fraction(-num, den);
    }
    Fraction operator-(Fraction const& obj) const {
      return *this + (-obj);
    }
    bool operator==(Fraction const& obj) const {
      return num * obj.den == obj.num * den;
    }
    bool operator<(Fraction const& obj) const {
      return num * obj.den < obj.num * den;
    }
    bool operator>(Fraction const& obj) const {
      return num * obj.den > obj.num * den;
    }
    bool operator<=(Fraction const& obj) const {
      return num * obj.den <= obj.num * den;
    }
    bool operator>=(Fraction const& obj) const {
      return num * obj.den >= obj.num * den;
    }
    Fraction roundedDown() {
      return Fraction(num / den);
    }
    Fraction roundedUp() {
      return Fraction(- ( (-num) / den));
    }
    Fraction half() {
      return Fraction(num, den * 2);
    }

};

std::ostream &operator<<(std::ostream &os, Fraction const &frac) { 
  if(frac.den == 1) {
    return os << frac.num;
  }else{
    return os << frac.num << "/" << frac.den;
  }
}

pair<Fraction, int> process(string line, int index) {

  index += 1;

  Fraction lower(-10000000), higher(10000000);
  if(line.at(index) == '{') {
    pair<Fraction, int> result = process(line, index);
    lower = result.first;
    index = result.second;
  }

  index += 1;

  if(line.at(index) == '{') {
    pair<Fraction, int> result = process(line, index);
    higher = result.first;
    index = result.second;
  }

  index += 1;

  Fraction result;

  if(lower >= result) {
    result = lower.roundedDown() + Fraction(1);
    Fraction interval(1, 2);

    while(result <= lower || result >= higher) {
      if(result <= lower) {
        result = result + interval;
      }else{
        result = result - interval;
      }
      interval = interval.half();
    }
  }else if(higher <= result) {
    result = higher.roundedUp() - Fraction(1);
    Fraction interval(1, 2);

    while(result <= lower || result >= higher) {
      if(result <= lower) {
        result = result + interval;
      }else{
        result = result - interval;
      }
      interval = interval.half();
    }
  }

  return pair<Fraction, int>(result, index);
}

int main() {
  string line;

  cin >> line;

  cout << process(line, 0).first << endl;

  return 0;
}