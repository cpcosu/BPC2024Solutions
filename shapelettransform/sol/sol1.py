if __name__ == '__main__':
    n = int(input())
    top_3 = [] 
    mapping = {}
    score_list = []
    for i in range(n):
        id, score = input().split()
        id = int(id)
        score = float(score)
        mapping[score] = id
        score_list.append(score)
    sorted_list = sorted(score_list, reverse=True)
    for i in range(3):
        top_3.append(mapping[sorted_list[i]])
    top_3.sort()
    for e in top_3: 
        print(e)