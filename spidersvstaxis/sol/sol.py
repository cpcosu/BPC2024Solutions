from queue import PriorityQueue

def spider(n, grid):
    
    q = PriorityQueue()
    q.put((0, 0, 0))
    
    minDis = [[float('inf') for _ in range(n)] for _ in range(n)]
    minDis[0][0] = 0
    
    while not q.empty():
        dis, x, y = q.get()
        
        for dx, dy in [(1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (1, -1), (-1, -1), (-1, 1)]:
            nx = x + dx
            ny = y + dy
            
            newDis = dis + (1 if dx == 0 or dy == 0 else 1.5)
            
            if 0 <= nx < n and 0 <= ny < n and grid[ny][nx] == 0 and newDis < minDis[ny][nx]:
                minDis[ny][nx] = newDis
                q.put((newDis, nx, ny))
    
    return minDis[-1][-1]

def taxi(n, grid):
    
    q = PriorityQueue()
    q.put((0, 0, 0))
    
    minDis = [[float('inf') for _ in range(n)] for _ in range(n)]
    minDis[0][0] = 0
    
    while not q.empty():
        dis, x, y = q.get()
        
        for dx, dy in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
            nx = x + dx
            ny = y + dy
            
            newDis = dis + (1 if dx == 0 or dy == 0 else 1.5)
            
            if 0 <= nx < n and 0 <= ny < n and grid[ny][nx] == 0:
                if newDis < minDis[ny][nx]:
                    minDis[ny][nx] = newDis
                    q.put((newDis, nx, ny))
                
                nx = nx + dx
                ny = ny + dy
                
                if 0 <= nx < n and 0 <= ny < n and grid[ny][nx] == 0 and newDis < minDis[ny][nx]:
                    minDis[ny][nx] = newDis
                    q.put((newDis, nx, ny))
    
    return minDis[-1][-1]




n = int(input())

grid = [[".X".find(i) for i in input()] for _ in range(n)]

s = spider(n, grid)
t = taxi(n, grid)
if s == t:
    print("tie")
elif s < t:
    print("spider")
else:
    print("taxicab")