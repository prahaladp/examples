    private List<Character> getNextRankSet(Map<Character, List<Integer>> rankMap, List<Character> pSet, int position, int maxRank) {
        if (pSet.size() == 1) {
            List<Character> nl = new LinkedList<>();
            nl.addAll(pSet);
            return nl;
        }
        if (position > maxRank) {
            List<Character> nl = new LinkedList<>();
            nl.addAll(pSet);
            nl.sort((a,b) -> (a - b));
            return nl;
        }

        List<Character> result = new LinkedList<>();
        int maxRankForPosition = Integer.MIN_VALUE;
        Iterator<Character> iter = pSet.iterator();
        while (iter.hasNext() == true) {
            Character c = iter.next();
            List<Integer> ranks = rankMap.get(c);
            if (ranks.get(position - 1) == maxRankForPosition) {
                result.add(c);
            } else if (ranks.get(position-1) > maxRankForPosition) {
                maxRankForPosition = ranks.get(position - 1);
                result.clear();
                result.add(c);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        if (result.size() == 1) {
            return result;
        }

        // there is a tie
        List<Character> tempRes = new LinkedList<>();
        int expectedSize = result.size();
        while (tempRes.size() != expectedSize) {
            List<Character> nextRes = getNextRankSet(rankMap, result, position + 1, maxRank);
            nextRes.forEach(c -> tempRes.add(c));
            nextRes.forEach(c -> result.remove(c));
        }
        return tempRes;
    }

    public String rankTeams(String[] votes) {
        Map<Character, List<Integer>> rankMap = new HashMap<>();
        buildMap(rankMap, votes);

        List<Character> pSet = new LinkedList<>();
        pSet.addAll(rankMap.keySet());
        List<Character> result = new LinkedList<>();
        int position = 1;
        while (position <= votes[0].length() && pSet.isEmpty() == false) {
            List<Character> rankSet = getNextRankSet(rankMap, pSet, position, votes[0].length());
            if (rankSet == null) {
                position++;
                continue;
            }
            result.addAll(rankSet);
            pSet.removeAll(rankSet);
        }
        StringBuilder str = new StringBuilder();
        result.forEach(c -> {
            str.append(c);
        });
        return str.toString();
    }

