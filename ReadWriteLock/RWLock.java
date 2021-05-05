package com.leetcode;

import com.sun.deploy.util.Waiter;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {

    final Lock lock = new ReentrantLock();

    enum LockType {
        NONE,
        READ,
        WRITE;
    };
    LockType currentLock;
    int readers;

    class Waiters {
        Condition condition;
        LockType lockType;
        public Waiters(Lock lock, LockType lockType) {
            this.lockType = lockType;
            this.condition = lock.newCondition();
        }

        public void await() {
            try {
                this.condition.await();
            } catch (InterruptedException ie) {
                System.out.println("exception " + ie);
            }
        }

        public void signal() {
            this.condition.signal();
        }

        public LockType getLockType() {
            return lockType;
        }
    };
    Queue<Waiters> waitQ;

    public RWLock() {
        currentLock = LockType.NONE;
        waitQ = new LinkedList<>();
        readers = 0;
    }

    public void ReadLock(Long threadId) {
        lock.lock();

        System.out.println("Read Lock - Thread " + threadId);
        if (currentLock == LockType.NONE) {
            this.currentLock = LockType.READ;
            this.readers++;
            System.out.println("Read Lock - Thread " + threadId + " readers " + readers);
            lock.unlock();
            return;
        }

        if (currentLock == LockType.WRITE) {
            Waiters w = new Waiters(lock, LockType.READ);
            System.out.println("Read Lock - Waiting - Thread " + threadId);
            waitQ.add(w);
            w.await();
        }

        if (currentLock == LockType.READ) {
            Waiters w = waitQ.peek();
            if (w == null) {
                this.readers++;
                System.out.println("Read Lock - Thread " + threadId + " readers " + readers);
                lock.unlock();
                return;
            }
            if (w.getLockType() == LockType.WRITE) {
                Waiters nw = new Waiters(lock, LockType.READ);
                waitQ.add(nw);
                System.out.println("Read Lock - Waiting - Thread " + threadId);
                nw.await();
            }
        }

        readers++;
        this.currentLock = LockType.READ;
        System.out.println("Read Lock - Thread " + threadId + " readers " + readers);
        lock.unlock();
    }

    public void WriteLock(Long threadId) {
        lock.lock();

        if (currentLock == LockType.NONE) {
            this.currentLock = LockType.WRITE;
            System.out.println("Write Lock - Thread " + threadId);
            lock.unlock();
            return;
        }

        Waiters w = new Waiters(lock, LockType.WRITE);
        System.out.println("Write Lock - Waiting thread " + threadId + " readers " + readers);
        waitQ.add(w);
        w.await();

        this.currentLock = LockType.WRITE;
        System.out.println("Write Lock - Thread " + threadId);
        lock.unlock();
    }

    public void unlock(Long threadId) {
        lock.lock();
        System.out.println("unlock " + threadId);
        if (readers != 0) {
            readers--;
            System.out.println("Reader unlock " + readers);
            if (readers != 0) {
                lock.unlock();
                return;
            }

            if (waitQ.size() == 0) {
                this.currentLock = LockType.NONE;
                System.out.println("Nothing waiting");
                return;
            }

            // start waking up the writer
            Waiters w = waitQ.remove();
            this.currentLock = w.getLockType();
            w.signal();
            lock.unlock();
            return;
        }

        if (waitQ.size() == 0) {
            System.out.println("Nothing waiting");
            this.currentLock = LockType.NONE;
            lock.unlock();
            return;
        }

        // this was a writer
        Waiters w = waitQ.peek();
        if (w.getLockType() == LockType.WRITE) {
            w = waitQ.remove();
            this.currentLock = w.getLockType();
            System.out.println("Waiting LockType " + w.getLockType());
            w.signal();
            lock.unlock();
            return;
        }

        Iterator<Waiters> iter = waitQ.iterator();
        while(iter.hasNext()){
            Waiters nextW = iter.next();
            System.out.println("Waiting LockType " + nextW.getLockType().toString());
            if(nextW.getLockType() == LockType. READ) {
                nextW.signal();
                iter.remove();
            }
        }

        lock.unlock();
    }
}

