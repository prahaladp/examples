def findSongs(songs):
    song_dict = {}
    word_dict = {}
    
    try:
        song_tok = songs.split(',')
        for tok in song_tok:
            # split song:word combination
            sw_tok = tok.split(':')
            song = sw_tok[0]
            word = sw_tok[1]
        
            if song not in song_dict:
                song_dict[song] = []
                song_dict[song].append(word)
            else:
                if word not in song_dict[song]:
                    song_dict[song].append(word)
                
            if word not in word_dict:
                word_dict[word] = []
                word_dict[word].append(song)
            else:
                if song not in word_dict[word]:
                    word_dict[word].append(song)
                
    except:
        return []

    # build the duplicate list
    duplicate = {}
    for song in song_dict:
        duplicate[song] = 0
        for w in song_dict[song]:
            word_entry = word_dict[w]
            for s in word_entry:
                if s != song:
                    duplicate[song] = duplicate[song] + 1
                    break
    max_n = 1
    d_list = []
    for k in duplicate:
        if duplicate[k] >= max_n:
            d_list.append(k)
            max_n = duplicate[k]
                
    return d_list
