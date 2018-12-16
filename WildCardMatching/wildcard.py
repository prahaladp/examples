
class Solution(object):
    def __init__(self):
        pass

    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        pat = self.get_all_patterns(p)
        if len(pat) == 0:
            if len(s) > 0:
                return False
            return True
        # 'a*b?b'
        # s = 'axvbxbxb'
        cmp_list = [(0, 0)]
        res = False

        while len(cmp_list) != 0:
            print 'current compare list is ', cmp_list, ' for ', s

            pat_ind = cmp_list[len(cmp_list) - 1][0]
            str_ind = cmp_list[len(cmp_list) - 1][1]
            del cmp_list[-1]

            if pat[pat_ind] == '?':
                # consume one character
                if str_ind >= len(s):
                    res = False
                    break

                # handle the last '?'
                if pat_ind + 1 == len(pat):
                    if str_ind + len(pat[pat_ind]) >= len(s):
                        # done
                        res = True
                        break
                    if pat_ind != 0 and pat[pat_ind-1] == '*':
                        cmp_list.append((pat_ind - 1, str_ind+1))
                else:
                    if pat_ind != 0 and pat[pat_ind-1] == '*':
                        cmp_list.append((pat_ind - 1, str_ind+1))
                    cmp_list.append((pat_ind + 1, str_ind + len(pat[pat_ind])))

            elif pat[pat_ind] == '*':
                # get first match
                if pat_ind + 1 == len(pat):
                    res = True
                    break
                cmp_list.append((pat_ind + 1, str_ind))
            else:
                m_ind = self.get_match(s, str_ind, pat[pat_ind])
                print 'match is ', m_ind
                if m_ind == -1:
                    # error
                    res = False
                    #print 'no match found for ' + pat[pat_ind]
                    continue

                if m_ind != str_ind:
                    if pat_ind != 0 and pat[pat_ind-1] == '*':
                        cmp_list.append((pat_ind-1, str_ind + 1))
                    if len(cmp_list) == 0:
                        res = False
                        #print 'no match found for ' + pat[pat_ind]
                        break
                else:
                    # if the previous match was a '*', we might have to revisit
                    # this match for more matches
                    if pat_ind != 0 and pat[pat_ind-1] == '*':
                        cmp_list.append((pat_ind-1, m_ind + 1))

                    # continue with the next match
                    if pat_ind + 1 != len(pat):
                        cmp_list.append((pat_ind + 1, m_ind + len(pat[pat_ind])))
                    else:
                        # make sure it matches the last block rules
                        if m_ind + len(pat[pat_ind]) >= len(s):
                            res = True
                            break
        return res

    def get_match(self, s, beg, pat):
        print 'finding match in ', s, ' at ', beg, ' for ', pat, ' len(pat) ', len(pat)
        return s.find(pat, beg)

    def get_all_patterns(self, p):
        p_block = []
        i = 0
        while i < len(p):
            if p[i] == '?' or p[i] == '*':
                p_block.append(p[i])
                i = i + 1
            else:
                # slice it up
                ps = ''
                while i < len(p) and p[i] != '?' and p[i] != '*':
                    ps = ps + p[i]
                    i = i + 1
                p_block.append(ps)
        print 'patterns blocks are ', p_block
        return p_block

s = Solution()
print s.isMatch('aa', 'a')
print s.isMatch('aa', '*')
print s.isMatch('cb', '?a')
print s.isMatch('adceb', '*a*b')
print s.isMatch('acdcb', 'a*c?b')
print s.isMatch('axvbxbxb', 'a*b?b')
print s.isMatch('bcd', '??')
print s.isMatch("acdcb", "a*c?b")
print s.isMatch('adceb', '*a*b')
print s.isMatch('a', '')
print s.isMatch('hi', '*?')
print s.isMatch('aa', '*')
print s.isMatch("acdcb", "a*c?b")
print s.isMatch("abefcdgiescdfimde", "ab*cd?i*de")
