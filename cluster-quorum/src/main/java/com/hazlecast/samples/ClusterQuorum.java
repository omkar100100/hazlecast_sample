package com.hazlecast.samples;

import java.util.concurrent.BlockingQueue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceNotActiveException;
import com.hazelcast.core.ILock;
import com.hazelcast.core.IQueue;
import com.hazelcast.quorum.QuorumException;

public class ClusterQuorum {

	public static void main(String[] args) throws Exception {
		final HazelcastInstance instance1 = Hazelcast.newHazelcastInstance();
		final HazelcastInstance instance2 = Hazelcast.newHazelcastInstance();
		// final IQueue<String> queue = instance1.getQueue("queueWithQuorum");
		// final ILock lock = instance1.getLock("lockWithQuorum");

		final BlockingQueue<String> queue = instance1.getQueue("queueWithQuorum");

		try {
			for (;;) {
				System.out.println(queue.take());
			}
		} catch (HazelcastInstanceNotActiveException e) {
			System.err.println("Unable to take from the queue. Hazelcast Member is probably going down!");
		}
	}
}