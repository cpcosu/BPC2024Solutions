n, *lines = open(0)

n = int(n)

# n = int(input())

lines = [i.strip() for i in lines]

# lines = [input().strip() for _ in range(n)]

ins = list(filter(lambda x: x!="", " ".join(lines).split(" ")))

jumpLocation = [0 for _ in range(len(ins))]

def findIgnoreContent(ins, pointer, match):
    depth = 0
    
    while ins[pointer] != match or depth > 0:
        if ins[pointer] == "swap":
            pointer += 2
        elif ins[pointer] == "if":
            depth += 1
            pointer += 3
        elif ins[pointer] == "else":
            pointer += 1
        elif ins[pointer] == "end":
            depth -= 1
            pointer += 1
        elif ins[pointer] == "while":
            depth += 1
            pointer += 3
    return pointer
            

for i in range(len(ins)):
    if ins[i] == "if":
        jumpLocation[i] = findIgnoreContent(ins, i + 3, "else") + 1
    elif ins[i] == "while":
        jumpLocation[i] = findIgnoreContent(ins, i + 3, "end") + 1
    elif ins[i] == "else":
        jumpLocation[i] = findIgnoreContent(ins, i + 1, "end") + 1

seenStates = [set() for _ in range(len(ins))]

currState = [0 for _ in range(10)]

pointer = 0

stack = []

halts = True

while pointer < len(ins):
    key = tuple(currState)
    
    if key in seenStates[pointer]:
        halts = False
        break
        
    seenStates[pointer].add(key)
    
    if ins[pointer] == "swap":
        index = ord(ins[pointer + 1]) - ord("a")
        
        currState[index] = 1 - currState[index]
        
        pointer += 2
    elif ins[pointer] == "while":
        index = ord(ins[pointer + 1]) - ord("a")
        
        if currState[index]:
            stack.append(["while", pointer])
            pointer += 3
        else:
            pointer = jumpLocation[pointer]
    elif ins[pointer] == "if":
        index = ord(ins[pointer + 1]) - ord("a")
        
        if currState[index]:
            stack.append(["if"])
            pointer += 3
        else:
            stack.append(["if"])
            pointer = jumpLocation[pointer]
    elif ins[pointer] == "else":
        stack.pop()
        pointer = jumpLocation[pointer]
    elif ins[pointer] == "end":
        if stack[-1][0] == "while":
            pointer = stack[-1][1]
            stack.pop()
        else:
            pointer += 1
            stack.pop()
        
      
  
if halts:
    print("halts")
else:
    print("doesn't halt")