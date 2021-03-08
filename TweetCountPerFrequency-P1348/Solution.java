class TweetCounts {
    Map<String, List<Integer>> tweetMap;
    
    public TweetCounts() {
        tweetMap = new HashMap<>();    
    }
    
    public void recordTweet(String tweetName, int time) {
        List<Integer> pq = 
            tweetMap.computeIfAbsent(tweetName, (k) -> new LinkedList<>());
        pq.add(time);
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> pq = tweetMap.get(tweetName);
        Collections.sort(pq);
        int s = startTime;
        int f = 0;
        if (freq.compareTo("minute") == 0) {
            f = 60;
        } else if (freq.compareTo("hour") == 0) {
            f = 3600;
        } else if (freq.compareTo("day") == 0) {
            f = 84000;
        }
        int nf= s + f;
        int c = 0;
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < pq.size(); i++) {
            //System.out.println(i + " " + pq.get(i) + " " + nf);
            if (pq.get(i) < startTime) {
                continue;
            }
            if (pq.get(i) > endTime) {
                break;
            }
            if (pq.get(i) < nf) {
                c = c+1;
            } else {
                res.add(c);
                c = 1;
                nf = nf + f;
            }
        }
        res.add(c);
        for (s=nf, nf += f; s < endTime; s=nf, nf += f) {
            res.add(0);
        }
        return res;
    }
}
