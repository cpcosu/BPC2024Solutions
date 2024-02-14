#include <bits/stdc++.h>

using namespace std;

int main() {

    int width, height, num_pieces;

    cin >> width >> height >> num_pieces;

    map<int,char> row,col,diag_neg,diag_pos;

    for (int i = 0; i < num_pieces; ++i) {
        int x, y;
        char c;

        cin >> x >> y >> c;

        int neg = x-y;
        int pos = x+y;

        if (row.count(y)) {
            if (row[y] != c) {
                cout << "ATTACK" << endl;
                exit(0);
            }
        } else {
            row[y] = c;
        }

        if (col.count(x)) {
            if (col[x] != c) {
                cout << "ATTACK" << endl;
                exit(0);
            }
        } else {
            col[x] = c;
        }

        if (diag_neg.count(neg)) {
            if (diag_neg[neg] != c) {
                cout << "ATTACK" << endl;
                exit(0);
            }
        } else {
            diag_neg[neg] = c;
        }

        if (diag_pos.count(pos)) {
            if (diag_pos[pos] != c) {
                cout << "ATTACK" << endl;
                exit(0);
            }
        } else {
            diag_pos[pos] = c;
        }

    }

    cout << "SAFE" << endl;

    return 0;
}