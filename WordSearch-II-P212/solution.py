class Node:
    def __init__(self, c):
        self.c=c
        self.trie={}
        self.w=None
        
    def add_word(self, nw):
        self.w=nw

class Solution(object):
    res=[]
    def check(self, x, y, board, tnode):
        #print("check :", tnode.c,tnode.trie.items(),x,y)
        if tnode.w != None:
            if tnode.w not in self.res:
                self.res.append(tnode.w)
        for (k,v) in tnode.trie.items():
            if x != 0 and board[x-1][y] != '-' and board[x-1][y] == k:
                board[x-1][y]='-'
                self.check(x-1,y,board,v)
                board[x-1][y]=k
            if x != len(board)-1 and board[x+1][y] != '-' and board[x+1][y] == k:
                board[x+1][y]='-'
                self.check(x+1,y,board,v)
                board[x+1][y]=k
            if y != 0 and board[x][y-1] != '-' and board[x][y-1] == k:
                board[x][y-1]='-'
                self.check(x,y-1,board,v)
                board[x][y-1]=k
            if y != len(board[x])-1 and board[x][y+1] != '-' and board[x][y+1] == k:
                board[x][y+1]='-'
                self.check(x,y+1,board,v)
                board[x][y+1]=k    
        
    def exist(self, board):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        tnode=self.trie
        for i in range(0,len(board)):
            for j in range(0, len(board[i])):
                if board[i][j] in tnode:
                    c=board[i][j]
                    board[i][j]='-'
                    v = tnode[c]
                    if v.w != None:
                        if v.w not in self.res:
                            self.res.append(v.w)
                    self.check(i, j, board, v)
                    board[i][j]=c

    def build_trie(self, words):
        self.trie={}
        for x in words:
            r=self.trie
            #print("trie="+str(r))
            for i in range(0, len(x)):
                if x[i] in r:
                    nn = r[x[i]]
                    r = r[x[i]].trie
                else:
                    # add a node
                    nn=Node(x[i])
                    r[x[i]] = nn
                    r=nn.trie
            nn.add_word(x)
            
    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        self.res = []
        self.trie={}
        self.build_trie(words)
        #print("trie = ", str(self.trie))
        self.exist(board)
        return self.res
