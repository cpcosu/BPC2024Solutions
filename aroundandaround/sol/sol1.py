import math


def distance(a, b):
  return math.sqrt((b[0] - a[0])**2 + (b[1] - a[1])**2)


def solve(r, arr):
  dis = 0
  for a, b in zip(arr, arr[1:] + [arr[0]]):
    dis += distance(a, b)
  
  rotation = dis / (2 * math.pi * r) + 1
  
  return f"{rotation:.1f}"
  
  
  
if __name__ == "__main__":
  line = input().split()
  
  n = int(line[0])
  r = float(line[1])
  
  arr = [list(map(float, input().split())) for _ in range(n)]
  
  print(solve(r, arr))