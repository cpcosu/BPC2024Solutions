
n1,n2 = input().split()

n1 = n1.zfill(len(n2))
n2 = n2.zfill(len(n1))

carry = 0
for a, b in zip(n1[::-1], n2[::-1]):
    a = int(a)
    b = int(b)
    total = a + b + carry
    carry = total // 10
    
    print((total) % 10, carry)

if carry > 0:
    print(carry, 0)