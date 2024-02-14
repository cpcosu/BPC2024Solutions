#include <bits/stdc++.h>

using namespace std;

int main() {

    int a[4], b[4];

    /**
     * 0 - neither
     * 1 - B
     * 2 - A
     * 3 - A and B
    */

    for(int i = 0; i < 4; ++i) {
        cin >> a[i] >> b[i];
    }

    int choices[] = {abs(a[0] - a[2]), abs(b[0] - b[1]), abs(a[1] - a[3]), abs(b[2] - b[3])};

    cout << *min_element(choices, choices + 4) << endl;

    return 0;
}