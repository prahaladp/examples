#
Circular Buffer : 

 B:
  - - - - -

To implement:
  Init 
  Read  
  Write 

Eg:
Circular buffer : B 
Target Buffer : T 

Write (B, "abc", 3) ==> B : a b c - - (3)
    
Read  (B, T, 2)     ==> 
            T : a b   (2)
            B : - - c - - 
Write (B, "123", 3) ==> B : B : 3 - c 1 2
"12345" , 5 (4) 

R/W no-blocking
Concurreny model
Read returns a copy of the requested buffers
sindex = start of the buffer (useful for reads)
eindex = end of the buffer (useful for writes)
citems = useful for fast conditions
#

class Cbuffer(object):
    
    def __init__(self, nitems):
        self._nitems = nitems
        self._citems = 0
        self._sindex = 0
        self._eindex = 0
        self._buffer = Array(self._nitems)
        
    def get_next_index(self, cindx):
        indx = cindx + 1
        if indx >= self._nitems:
            indx = 0
        return indx
        
    # citems = 3, eindx = 3
    # witems = 2, litems = 2, buffer[3] = '1'
    # litems = 1, buffer[4] = '2', eindx = 0
    # litems = 0, witems = 2, citems = 5
    def write(self, buff, len):
        if self._citems == self._nitems:
            return 0
        
        witems = self._nitems - self._citems
        litems = witems
        
        # will not work if buffer is bigger than len (?)
        for b in buff:
            if litems < 1:
                # we are done with writing to buffer
                break;
            # eindex always points to the right index in the array
            self._buffer.set(b, self._eindex)
            self._eindx = self.get_next_index(self._eindx)
            litems = litems - 1
        
        
        # update the number of items we actually wrote
        witems = witem - litems
        
        # update the number of items in the buffer
        self._citems = self._citems + witems

        return witems

    # citems = 3, eindx = 3, sindex = 0
    # len = 2, val = '1', litems = 1
    # len = 1, citems = 2, sindex = 1, val = '2', litems = 2
    # len = 0, citems = 1, sindex = 2 <exit while>
    # return litems (2)
    def read(self, target, len):
        
        # terminating conditions are
        # (1) len == 0
        # (2) self._citems == 0 
        # collect the number of items we have gathered
        litems = 0
        
        while len and self._citems != 0:
            val = self._buffer.get(self._sindex)
            target.append(val)
            
            self._sindex = self.get_next_index(self._sindex)
            self._citems = self._citems - 1
            len = len - 1
            litems = litems + 1
        
        return litems

