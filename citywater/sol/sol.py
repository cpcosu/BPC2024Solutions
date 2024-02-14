from queue import Queue
from collections import defaultdict



def levelBFS(n, graph, source):
  levels = [-1 for _ in range(n)]
  levels[source] = 0
  
  q = Queue()
  q.put(source)
  
  while not q.empty():
    currNode = q.get()
    
    for nextNode, edgeFlow, _ in graph[currNode]:
      if edgeFlow > 0 and levels[nextNode] == -1:
        levels[nextNode] = levels[currNode] + 1
        q.put(nextNode)
  
  return levels


  
def flowDFS(n, graph, levels, start, used, currNode, currFlow, sink):
  
  if currNode == sink:
    return currFlow
  
  while start[currNode] < len(graph[currNode]):
    nextNode, edgeFlow, reverseIndex = graph[currNode][start[currNode]]
    
    if edgeFlow > 0 and levels[currNode] + 1 == levels[nextNode]:
      
      tempFlow = min(currFlow, edgeFlow)
      tempFlow = flowDFS(n, graph, levels, start, used, nextNode, tempFlow, sink)
      
      if tempFlow > 0:
        
        graph[currNode][start[currNode]][1] -= tempFlow
        graph[nextNode][reverseIndex][1] += tempFlow
        
        if nextNode in used[currNode]:
          used[currNode][nextNode] += tempFlow
        else:
          used[nextNode][currNode] -= tempFlow
    
        return tempFlow
      
    start[currNode] += 1
        
  return 0


def doFlow(n, graph, used, source, sink):
  
  totalFlow = 0
  
  while True:
    levels = levelBFS(n, graph, source)
    
    if levels[sink] == -1:
      break
    
    start = [0 for _ in range(n)]
    
    while True: 
      tempFlow = flowDFS(n, graph, levels, start, used, source, float('inf'), sink)
      
      if tempFlow == 0:
        break
      
      totalFlow += tempFlow
  
  return totalFlow
      




def solve(n, edges, source, sink1, sink2):
  
  graph = [[] for _ in range(n)]
  used = defaultdict(lambda: defaultdict(lambda: 0))
  
  for a, b, flow in edges:
    if b in used[a] or a in used[b]:
      for i in range(len(graph[a])):
        if graph[a][i][0] == b:
          graph[a][i][1] += flow
          break
    else:
      addA = [b, flow, len(graph[b])]
      addB = [a, 0, len(graph[a])]
      
      graph[a].append(addA)
      graph[b].append(addB)
      
      used[a][b] = 0
  

  totalFlow = doFlow(n, graph, used, source, sink1)
  secondFlow = doFlow(n, graph, used, source, sink2)
  
  return f"{totalFlow:d} {secondFlow:d}"


if __name__ == "__main__":
  n, m, source, sink1, sink2 = map(int, input().split())
  
  edges = [list(map(int, input().split())) for _ in range(m)]
  
  print(solve(n, edges, source, sink1, sink2))