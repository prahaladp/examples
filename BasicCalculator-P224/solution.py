class Solution(object):
    def eval(self, nums, op):
        v2 = nums.pop()
        v1 = nums.pop()
        #print("eval ", v1, op, v2)
        if op == '+':
            nums.append(v1 + v2)
        elif op == '-':
            nums.append(v1 - v2)
  
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
                if c == ' ':
                    continue
                if c != '(' and len(ops) != 0 and len(nums) >= 2:
                    op = ops.pop()
                    if op == '+' or op == '-':
                        self.eval(nums, op)
                    else:
                        ops.append(op)
                if c == ')':
                    # removes the open parenthesis
                    o = ops.pop()
                    while len(ops) != 0 and len(nums) >= 2:
                        op = ops.pop()
                        #print("--", str(nums), str(ops))
                        if op == '+' or op == '-':
                            self.eval(nums, op)
                        else:
                            ops.append(op)
                            break
                else:
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
