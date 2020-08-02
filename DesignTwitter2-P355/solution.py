class Tweet(object):
    
    def __init__(self, u, t, s):
        self.userId = u
        self.tweetId = t
        self.stamp = s 
        
class Twitter(object):
    
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.tweets = {}
        self.follows = {}
        self.stamp = 0

    def postTweet(self, userId, tweetId):
        """
        Compose a new tweet.
        :type userId: int
        :type tweetId: int
        :rtype: None
        """
        if userId not in self.tweets:
            self.tweets[userId] = []
        tweet = Tweet(userId, tweetId, self.stamp)
        self.tweets[userId].insert(0, tweet)
        self.stamp = self.stamp + 1            
    
    def insertAt(self, ti, tweet):
        i = 0
        while i < len(ti):
            if ti[i].stamp > tweet.stamp:
                i = i + 1
                continue
            ti.insert(i, tweet)
            return ti
        ti.append(tweet)
        return ti

    def getNewsFeed(self, userId):
        """
        Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
        :type userId: int
        :rtype: List[int]
        """
        ci = {}
        ti = []
        if userId in self.tweets and len(self.tweets[userId]) > 0:
            ti.append(self.tweets[userId][0])
            ci[userId] = 0
        
        if userId not in self.follows:
            self.follows[userId] = []
        
        for f in self.follows[userId]:
            if f in self.tweets and len(self.tweets[f]) > 0:
                ti = self.insertAt(ti, self.tweets[f][0])
                ci[f] = 0
        if len(ti) == 0:
            return []
        
        tl = []
        while len(tl) < 10 and len(ti) > 0:
            tw = ti[0]
            ti = ti[1:]
            tl.append(tw.tweetId)
            ci[tw.userId] += 1
            if tw.userId in self.tweets and len(self.tweets[tw.userId]) > ci[tw.userId]:
                ti = self.insertAt(ti, self.tweets[tw.userId][ci[tw.userId]])
        return tl
            
    def follow(self, followerId, followeeId):
        """
        Follower follows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: None
        """
        if followerId == followeeId:
            return
        if followerId not in self.follows:
            self.follows[followerId] = []
        if followeeId not in self.follows[followerId]:
            self.follows[followerId].append(followeeId)
                
    def unfollow(self, followerId, followeeId):
        """
        Follower unfollows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: None
        """
        if followerId in self.follows and followeeId in self.follows[followerId]:
            self.follows[followerId].remove(followeeId)
        
# Your Twitter object will be instantiated and called as such:
# obj = Twitter()
# obj.postTweet(userId,tweetId)
# param_2 = obj.getNewsFeed(userId)
# obj.follow(followerId,followeeId)
# obj.unfollow(followerId,followeeId)
