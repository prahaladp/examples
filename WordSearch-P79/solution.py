class Solution(object):
    def check(self, x, y, board, word, wi):
        if wi >= len(word):
            return True
        if x != 0 and board[x-1][y] != '-' and board[x-1][y] == word[wi]:
            board[x-1][y]='-'
            if self.check(x-1,y,board,word,wi+1) == True:
                return True;
            board[x-1][y]=word[wi]
        if x != len(board)-1 and board[x+1][y] != '-' and board[x+1][y] == word[wi]:
            board[x+1][y]='-'
            if self.check(x+1,y,board,word,wi+1) == True:
                return True;
            board[x+1][y]=word[wi]
        if y != 0 and board[x][y-1] != '-' and board[x][y-1] == word[wi]:
            board[x][y-1]='-'
            if self.check(x,y-1,board,word,wi+1) == True:
                return True;
            board[x][y-1]=word[wi]
        if y != len(board[x])-1 and board[x][y+1] != '-' and board[x][y+1] == word[wi]:
            board[x][y+1]='-'
            if self.check(x,y+1,board,word,wi+1) == True:
                return True;
            board[x][y+1]=word[wi]
        return False
        
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        if word == None:
            return false
        for i in range(0,len(board)):
            for j in range(0, len(board[i])):
                if board[i][j] == word[0]:
                    board[i][j] = '-'
                    if self.check(i,j,board,word,1) == True:
                        return True
                    board[i][j] = word[0]
        return False
