if __name__ == '__main__':
    
    res = True
    snacks = []
    n = int(input())
    for i in range(n): snacks.append(int(input()))


    total = 0
    for e in snacks: 
        total += e
    
    if total % 3 != 0:
        res = False

    else:
        posLeft = 0
        leftSum = 0
        while posLeft < len(snacks):
            
            leftSum += snacks[posLeft]

            if leftSum == total/3:  break

            posLeft += 1
        
        if posLeft >= len(snacks):
            res = False

        else:
            posRight = len(snacks) - 1
            rightSum = 0
            
            while posRight >= 0: 
                rightSum += snacks[posRight]
                if rightSum == total/3: break
                posRight -= 1

            if posRight < 0: 
                res = False
            else:
                res = posRight - posLeft > 1
    
    if res:
        print('YES')
    else:
        print('NO')


