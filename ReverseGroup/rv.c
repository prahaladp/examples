

typedef struct node_s {
    int                val;
    struct node_s      *next;
} node_t;

/*
 * param : len (number of elements)
           auto-fill the values
 */
 node_t *
 create_nodes(int len) {
 
     node_t    *head = NULL;
     node_t    *last = NULL;
     node_t    *tmp = NULL;
     int       val = 0;
     
     /* negative values */
     
     while (len) {
         /* add elements to the list */
         tmp = (node_t *)malloc(sizeof (node_t));
         if (tmp == NULL) {
             /* log (?) */
             return (head);
         }
         tmp->val = val++;
         tmp->next = NULL;
         
         if (last) {
             last->next = tmp;
         } else {
             head = tmp;
         }
         last = tmp;
         
         len--;
     }
     
     return (head);
 }
 
 
 /*
  * reverse the list
  */
node_t *
reverse_list(node_t *head, node_t **next_grp, int len)
{
    node_t    *curr = head;
    node_t    *prev = NULL;
    
    /* checks for len */
    
    while (len && curr) {
        node_t    *nxt = curr->next;
        
        len--;
        curr->next = prev;
        prev = curr;
        curr = nxt;
    }
    
    *next_grp = nxt;
    return (prev);
}

/*
    1->2->4 (variation in list size)
    1
    head = NULL
    1->1->1->2
    loop
    
 */



/*
  * reverse list by groups
  * assume list:  0 1 2 3 4 5 6 7 8 9
  * group_size 3: 2 1 0 5 4 3 8 7 6 9
  * group_size 4: 3 2 1 0 7 6 5 4 9 8
  
      tail = NULL
      nhead = (0)->...
      nxt_grp = (3)
      pgrp = (2)->1->0
      lhead = (3)
      
      tail = (0) ->....
      lhead = (3)...
      nhead = ((5)->4->(3)
      
          
 */

node_t *
reverse_by_group(node_t *head, int group_size) {
    node_t    *nxt_grp = NULL;
    node_t    *nhead = NULL;
    node_t    *lhead = NULL;
    node_t    *pgrp = NULL;
    node_t    *tail = NULL;
    
    /* call reverse_list repeatedly (till we reach end of the groups) */
    nhead = head;
    pgrp = reverse_list(nhead, &nxt_grp, group_size);
    tail = head;
    while (nxt_grp) {
        lhead = nxt_grp;
        nhead = reverse_list(nxt_grp, &nxt_grp, group_size);
        tail->next = nhead;
        tail = lhead;
    }
    
    return (pgrp);
}


