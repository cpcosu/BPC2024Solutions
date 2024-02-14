"""
Solution to BPC 2024 Problem: Queens

Author:
Brandon Young
"""


ATTACK = "ATTACK"
SAFE = "SAFE"

def solve(width, height, queens):
    # Contains first White then Black
    row = [set(), set()]
    column = [set(), set()]
    diagDown = [set(), set()]
    diagUp = [set(), set()]

    for x, y, color in queens:
        colorIndex = {"W": 0, "B": 1}[color]
        enemyIndex = {"W": 1, "B": 0}[color]
        
        diagDownKey = x - y
        diagUpKey = x + y
        
        if y in row[enemyIndex]: return ATTACK
        if x in column[enemyIndex]: return ATTACK
        if diagDownKey in diagDown[enemyIndex]: return ATTACK
        if diagUpKey in diagUp[enemyIndex]: return ATTACK
        
        row[colorIndex].add(y)
        column[colorIndex].add(x)
        diagDown[colorIndex].add(diagDownKey)
        diagUp[colorIndex].add(diagUpKey)

    return SAFE


if __name__ == "__main__":
    w, h = map(int, input().split())
  
    n = int(input())
    
    queens = [input().split() for _ in range(n)]
    
    for i in range(n):
        queens[i][0] = int(queens[i][0])
        queens[i][1] = int(queens[i][1])
    
    print(solve(w, h, queens))
