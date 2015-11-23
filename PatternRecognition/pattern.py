import sys

total_symbol = 0

def copy_str(src, x, y):
    return (''.join(src[x:y]))

def check_tok(tok, tokens):
    for t in tokens:
        if tokens[t] == tok:
            return t
    return None

def next_sym(s):
    ns = chr(ord(s) + 1)
    return ns

def tokenize(inp, srange, tokens, sym, sym_str, pattern):
    global total_symbol
    indx = 0
    tok_len = 1
    max_len = srange[1] - srange[0]
    #print inp, max_len

    if max_len <= 0:
        if sym_str == pattern:
            print 'pattern :', pattern
            print 'final symbol :',  sym_str
            print 'tokens : ', tokens

    while tok_len <= max_len:

        local_tokens = tokens.copy()
        new_tok = copy_str(inp, indx, indx + tok_len)

        # see if the token already exists:
        n_sym = check_tok(new_tok, local_tokens)
        if n_sym is None:
            local_tokens[sym] = new_tok
            n_sym = next_sym(sym)
            next_sym_str = sym_str + sym
        else:
            next_sym_str = copy_str(sym_str, 0, len(sym_str) + 1)
            next_sym_str += n_sym
            n_sym = sym
            # print 'match : ', next_sym_str

        n_range = [srange[0] + tok_len, srange[1]]
        tokenize(inp[indx+tok_len:], n_range, local_tokens, n_sym, next_sym_str, pattern)

        tok_len += 1
        total_symbol += 1

            
inp = sys.argv[1]
pattern = sys.argv[2]
tokens = {}
sym = 'a'
sym_str = ''
srange = [0, len(inp)]
tokenize(inp, srange, tokens, sym, sym_str, pattern)

print 'total symbols generated : ', total_symbol
