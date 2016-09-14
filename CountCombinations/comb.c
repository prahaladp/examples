int BestOfSeries(int n) {
    char winner[1000];
    int  indx = 0;
    int  win_a = 0;
    int  win_b = 0;
    int  outcome = 0;
    
    bzero(winner, sizeof (winner));
    while (indx < n) {
        if (win_a >= series_win || win_b >= series_win) {
            outcome++;
            
            if (winner[indx] == 'a') {
                winner[indx] = 'b';
                win_a--;
                win_b++;
                indx++;
                continue;
            }
            
            if (winner[indx] == 'b') {
                while (indx >= 0 && winner[indx] == 'b') {
                    winner[indx--] = 0;
                    win_b--;
                }
                
                if (indx < 0) {
                    break;
                }
                
                if (winner[indx] == 'a') {
                    win_a--;
                    winner[indx] = 'b';
                    win_b++;
                }
            }
        }
        
        if (indx >= n) {
            indx = n - 1;
            while (indx >= 0 && winner[indx] == 'b') {
                winner[indx--] = 0;
                win_b--;
            }
            
            if (indx < 0) {
                break;
            }
            
            if (winner[indx] == 'a') {
                win_a--
            }
            
            winner[indx] = 'b';
            win_b++;
            continue;
        }
        switch (winner[indx]) {
            case 0:
                winner
            case 'a':
            
        }
    }
    return (0);
}
