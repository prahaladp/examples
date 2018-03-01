# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
            :type l1: ListNode
            :type l2: ListNode
            :rtype: ListNode
            """
        if l1 is None and l2 is None:
            return None
        
        if l1 is None:
            if l2 is not None:
                return l2
            return None
        
        if l2 is None:
            if l1 is not None:
                return l1
            return None
        
        l3 = None
        head = None
        while l1 is not None and l2 is not None:
            if l1.val < l2.val:
                if l3 is None:
                    l3 = l1
                    head = l3
                    l1 = l1.next
                else:
                    l3.next = l1
                    l3 = l1
                    l3.next = None
            else:
                if l3 is None:
                    l3 = l2
                    head = l3
                    l2 = l2.next
                else:
                    l3.next = l2
                    l3 = l2
                    l3.next = None
    
        if l1 is not None:
            l3.next = l1
if l2 is not None:
    l3.next = l2
        
        return head
