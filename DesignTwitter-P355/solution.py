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
        self.followers = {}
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
        
        if userId not in self.followers:
            return
        
        for f in self.followers[userId]:
            if f not in self.tweets:
                self.tweets[f] = []
            self.insertAt(f, tweet)
    
    def insertAt(self, f, tweet):
        i = 0
        while i < len(self.tweets[f]):
            if self.tweets[f][i].stamp > tweet.stamp:
                i = i + 1
                continue
            self.tweets[f].insert(i, tweet)
            return
        self.tweets[f].append(tweet)
            
    def getNewsFeed(self, userId):
        """
        Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
        :type userId: int
        :rtype: List[int]
        """
        if userId not in self.tweets:
            return []
        tl = []
        tl = [x.tweetId for x in self.tweets[userId][0:10]]
        return tl

    def follow(self, followerId, followeeId):
        """
        Follower follows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: None
        """
        if followeeId == followerId:
            return
        if followeeId not in self.followers:
            self.followers[followeeId] = []
        if followerId in self.followers[followeeId]:
            return
        self.followers[followeeId].append(followerId)

        if followerId not in self.tweets:
            self.tweets[followerId] = []
        if followeeId in self.tweets:
            for t in self.tweets[followeeId]:
                if t.userId == followeeId:
                    self.insertAt(followerId, t)
                
    def unfollow(self, followerId, followeeId):
        """
        Follower unfollows a followee. If the operation is invalid, it should be a no-op.
        :type followerId: int
        :type followeeId: int
        :rtype: None
        """
        if followeeId not in self.followers:
            return
        if followerId not in self.followers[followeeId]:
            return
        self.followers[followeeId].remove(followerId)
        
        if followeeId not in self.tweets or followerId not in self.tweets:
            return
        self.tweets[followerId] = [t for t in self.tweets[followerId] if t.userId != followeeId]
        
# Your Twitter object will be instantiated and called as such:
# obj = Twitter()
# obj.postTweet(userId,tweetId)
# param_2 = obj.getNewsFeed(userId)
# obj.follow(followerId,followeeId)
# obj.unfollow(followerId,followeeId)
