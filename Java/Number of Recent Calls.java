class RecentCounter {
    
    Queue<Integer> pingQueue;

    public RecentCounter() {
        this.pingQueue = new LinkedList();
        
    }
    
    public int ping(int t) {
        
        this.pingQueue.add(t);
        
        while(this.pingQueue.peek() < t-3000)
            this.pingQueue.poll();
        
        
        return size();
        
    }
    
    private int size() {
        return this.pingQueue.size();
    }
}
