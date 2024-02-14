import math

x, y = map(int ,input().split())

n = int(input())

points = [list(map(int, input().split())) for _ in range(n)]

angles = []

count = 0

for a, b in zip(points, points[1:] + points[:1]):
    dx = b[0] - a[0]
    dy = b[1] - a[1]
    
    if dy == 0: continue
    if b[1] == y: continue
    
    c = (y -a[1]) / dy
    
    nx = c * dx + a[0]

    if nx > x and (a[0] <= nx < b[0] or b[0] < nx <= a[0]):
        count += 1

if count % 2 == 1:
    print("CONTAINED")
else:
    print("NOT CONTAINED")
    
  