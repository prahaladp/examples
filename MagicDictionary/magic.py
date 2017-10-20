class MagicDictionary(object):
    dictionary = []

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.dictionary = []

    def buildDict(self, words):
        """
        Build a dictionary through a list of words
        :type dict: List[str]
        :rtype: void
        """
        for w in words:
            self.dictionary.append(w)

    def search(self, word):
        """
        Returns if there is any word in the trie that equals to the given word after modifying exactly one character
        :type word: str
        :rtype: bool
        """
        for k in self.dictionary:
            diff = len(k) - len(word)
            if diff == 0:
                diff = self.compare_words(k, word)
                if diff == 1:
                    return True

        return False
    
    def compare_words(self, k, word):
        l1 = len(k)
        l2 = len(word)
        diff = 0
        for i in range(l1):
            if l2 <= i:
                diff = diff + 1
            elif k[i] != word[i]:
                diff = diff + 1
            if diff >= 2:
                break

        return diff

obj = MagicDictionary()
dict=[]
obj.buildDict(dict)
print obj.search("leetcodd")

