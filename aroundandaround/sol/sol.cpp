#include <bits/stdc++.h>

using namespace std;

double distance(pair<double, double> p1, pair<double, double> p2) {
    return sqrt((p2.first - p1.first)*(p2.first - p1.first) + (p2.second - p1.second)*(p2.second - p1.second));
}

constexpr double pi = 3.14159265358979323846;

int main() {
    int n;
    double r;
    
    std::cin >> n >> r;
    
    vector<pair<double, double>> points;


    for (int i = 0; i < n; ++i) {
        double x, y;
        cin >> x >> y;
        
        points.push_back(make_pair(x,y));
    }
    
    double total_dist = 0;
    
    for (int i = 1; i < n; ++i) {
        total_dist += distance(points.at(i), points.at(i-1));
    }
    total_dist += distance(points.at(n-1), points.at(0));
    
    double rotation = total_dist / (2 * pi * r) + 1;
    
    cout << fixed << setprecision(1) << rotation << endl;

    return 0;
}
