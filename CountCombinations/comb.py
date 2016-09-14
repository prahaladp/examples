def BestOfSeries(n):
    winner = []
    for i in range(0, n):
        winner.append('')

    series_win = (n + 1)/2
    indx = 0
    win_a = 0
    win_b = 0
    possible = 0

    while indx >= 0:
        #print 'winner count ' , win_a, win_b
        if win_a >= series_win or win_b >= series_win:
            possible = possible + 1
            #print 'winner : ', indx, winner

            # if a is the last winner, then change the index to b
            if indx < n:
                if winner[indx] == 'a':
                    win_a = win_a - 1
                    winner[indx] = 'b'
                    win_b = win_b + 1
                    indx = indx + 1
                    continue

                if winner[indx] == 'b':
                    while indx >= 0 and winner[indx] == 'b':
                        winner[indx] = ''
                        indx = indx - 1
                        win_b = win_b - 1

                    if indx < 0:
                        break

                    if winner[indx] == 'a':
                        win_a = win_a - 1
                        winner[indx] = 'b'
                        win_b = win_b + 1
                    continue

        #print indx, winner

        if indx >=n:
            indx = n - 1
            while indx >= 0 and winner[indx] == 'b':
                winner[indx] = ''
                indx = indx - 1
                win_b = win_b - 1

            if indx < 0:
                break

            if winner[indx] == 'a':
                win_a = win_a - 1
            winner[indx] = 'b'
            win_b = win_b + 1
            continue

        if winner[indx] == '':
            winner[indx] = 'a'
            win_a = win_a + 1
        elif winner[indx] == 'a':
            if indx != n - 1:
                indx = indx + 1
                winner[indx] = 'a'
                win_a = win_a + 1
            else:
                winner[indx]= 'b'
                win_a = win_a - 1
                win_b = win_b + 1
        elif winner[indx] == 'b':
            indx = indx + 1
            if indx < n:
                winner[indx] = 'a'
                win_a = win_a + 1

    return possible

#for i in range(1,10):
#    print  i, BestOfSeries(i)
print BestOfSeries(15)
