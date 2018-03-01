def find_distance(word_dict, word1, word2):
    if word1 not in word_dict:
        return -1

    if word2 not in word_dict:
        return -1

    indx1 = word_dict[word1]
    indx2 = word_dict[word2]
    
