class Solution(object):
    def eval(self, nums, op):
        v2 = nums.pop()
        v1 = nums.pop()
        if op == '+':
            nums.append(v1 + v2)
        elif op == '-':
            nums.append(v1 - v2)
        elif op == '/':
            nums.append(v1/v2)
        else:
            nums.append(v1 * v2)
  
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        """
        nums=[]
        ops=[]
        curr=None
        for c in s:
            #print(str(nums), str(ops))
            if c.isdigit() is True:
                if curr == None:
                    curr = 0
                curr = curr * 10 + (ord(c)-ord('0'))
            else:
                # push the previous value
                if curr is not None:
                    nums.append(curr)
                    curr = None
                    if len(ops) != 0:
                        op = ops.pop()
                        if op == '/' or op == '*':
                            self.eval(nums, op)
                        else:
                            ops.append(op)
                if c == ' ':
                    continue
                if len(ops) != 0:
                    if c == '*' or c == '/':
                        # has precedence, so push
                        ops.append(c)
                        continue
                    op = ops.pop()
                    self.eval(nums, op)
                ops.append(c)
        #print(str(nums), str(ops))
        if curr is not None:
            nums.append(curr)
            curr = None
        #print(str(nums), str(ops))

        while len(ops) != 0:
            op = ops.pop()
            self.eval(nums, op)

        return nums[0]
