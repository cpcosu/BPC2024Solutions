import math
import fractions



def process(s, index):
  
  index += 1
  
  lower = -1000000
  if s[index] != "|":
    lower, index = process(s, index)
  
  index += 1
    
  higher = 1000000
  if s[index] != "}":
    higher, index = process(s, index)
    
  index += 1

  num = 0

  if lower >= num:
    numLower = math.floor(lower)
    numHigher = numLower + 2
    
    num = (numLower + numHigher) / 2
  
    while not(lower < num and num < higher):
      if num <= lower:
        numLower = num
      else:
        numHigher = num
      num = (numLower + numHigher) / 2
  elif higher <= num:
    numHigher = math.ceil(higher)
    numLower = numHigher - 2
      
    num = (numLower + numHigher) / 2
    
    while not(lower < num and num < higher):
      if num <= lower:
        numLower = num
      else:
        numHigher = num
      num = (numLower + numHigher) / 2
  
  return num, index

  

def solve(s):
  value, _ = process(s, 0)
  
  frac = fractions.Fraction(value)
  
  return str(frac)

if __name__ == "__main__":
  print(solve(input()))