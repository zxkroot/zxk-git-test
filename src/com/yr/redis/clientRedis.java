package com.yr.redis;

import com.yr.util.RedisConnection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;



public class clientRedis {
	public boolean judge(String href){
		Jedis	 jedis =new RedisConnection().jedis;
		ShardedJedis shardedJedis =new RedisConnection().shardedJedis;
		return jedis.exists(href);
	}
	public void  getRedis(String href){
		Jedis	 jedis =new RedisConnection().jedis;
		ShardedJedis shardedJedis =new RedisConnection().shardedJedis;
		jedis.set(href, "value001");
	}

}
