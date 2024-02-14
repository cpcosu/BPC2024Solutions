if __name__ == '__main__':
    num1, num2 = input().split()
    
    res = []
    pos1 = len(num1)-1
    pos2 = len(num2)-1
    
    carryOver = 0

    while pos1 >= 0 and pos2 >= 0:
        digit1 = (int)(num1[pos1]) 
        digit2 = (int)(num2[pos2])
        new_digit = (digit1+digit2+carryOver) % 10
        carryOver = (digit1+digit2+carryOver) // 10
        print(new_digit, carryOver)
        pos1 -= 1
        pos2 -= 1
        
    while pos1 >= 0:
        digit1 = (int)(num1[pos1])
        digit2 = 0
        new_digit = (digit1+digit2+carryOver) % 10
        carryOver = (digit1+digit2+carryOver) // 10
        print(new_digit, carryOver)
        pos1 -= 1


    while pos2 >= 0:
        digit1 = 0
        digit2 = (int)(num2[pos2])
        new_digit = (digit1+digit2+carryOver) % 10
        carryOver = (digit1+digit2+carryOver) // 10
        print(new_digit, carryOver)
        pos2 -= 1

    #extra carryover
    if carryOver != 0:
        print(1, 0)