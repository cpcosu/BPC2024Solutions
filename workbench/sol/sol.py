"""
Solution to BPC 2024 Problem: workbench

Author:
Ram
"""

n,t=map(int,input().split())
out=[]
tot=0
inventory={}
for _ in range(n):
    a,b=input()[1:-1:1].split(',')
    inventory[a]=int(b)
for _ in range(t):
    line=input().split(':')
    id,it=line[0][1:-1:1].split(',')
    id=int(id)
    poss=True
    for x in line[1].split():
        a,b=x[1:-1:1].split(',')
        b=int(b)
        if a not in inventory or inventory[a]<b:
            poss=False
            break
    if poss:
        tot+=id
        out.append(it[0])

print(''.join(out))
print(tot)