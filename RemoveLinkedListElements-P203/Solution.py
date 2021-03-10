class Solution(object):
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        r = None
        h = None
        while head != None:
            if head.val == val:
                head = head.next
                continue
            tmp = head.next
            if h is None:
                h = head
                h.next = None
                r = head
            else:
                r.next = head
                r = head
                r.next = None
            head = tmp
        return h
