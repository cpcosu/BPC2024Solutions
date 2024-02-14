
n = int(input())

data = []

for _ in range(n):
    a, b = input().split()
    
    data.append([int(a), float(b)])


data.sort(key=lambda x: x[1], reverse=True)

print(*list(sorted([i[0] for i in data[:3]])),sep="\n")