def solve(weights, classifiers):
    output = []
    for classifier in classifiers:
        total = 0
        for i in range(len(weights)):
            total += weights[i] * classifier[i]
        if total > 0:
            output.append("good")
        else:
            output.append("bad")
    return output

if __name__ == '__main__':
    num_var, num_classifer = map(int, input().split()) 
    
    #take the weights
    weights = list(map(int, input().split()))

    #take classifiers
    classifiers = []
    for i in range(num_classifer):
        classifiers.append(list(map(int, input().split())))
    
    output = solve(weights, classifiers)

    for o in output: print(o)
