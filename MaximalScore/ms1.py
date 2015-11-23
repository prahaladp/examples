import sys
 
 
def mmin(a,b):
    if (a==0):
        return b
    elif (b==0):
        return a
    elif (a<=b):
        return a
    else:
        return b
 
 
        
        
 
[V,E]=sys.stdin.readline().rstrip().split(' ')
V=int(V)
E=int(E)
#Initialising matrix
Matrix=[[0 for x in range(V)]for x in range(V)]
 
#Initialising Output Matrix
Omatrix=[[0 for x in range(V)]for x in range(V)]
 
#Inserting values into the matrix
for i in range(E):
    [u,v,w]=sys.stdin.readline().rstrip().split(' ')
    u=int(u)
    v=int(v)
    w=int(w)
    Matrix[u][v]=w
    Matrix[v][u]=w
 
 
#Applying mofified floyd-warshall's algorithm
 
for k in range(V):
    for i in range(V):
        for j in range(V):
            if(i!=j):
                Matrix[i][j]=max(Matrix[i][j],mmin(Matrix[i][k],Matrix[k][j]))
 
 
    
for i in range(V):
    for j in range(V):
        print Matrix[i][j],
    print
 
    
 
 

