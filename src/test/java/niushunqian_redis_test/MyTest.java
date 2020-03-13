package niushunqian_redis_test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.niushunqian.bean.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class MyTest {
	@Autowired
	RedisTemplate redisTemplate;
	@Test
	public void jdkTest(){
		User user=null;
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 1; i <= 100000; i++) {
			user=new User(i, "1", "男", "1", i+"", i+"");
			list.add(user);
		}
		
		
		redisTemplate.opsForList().leftPushAll("jdklist", list.toArray());
		
		
		System.out.println("序列化方式：jdk");
		System.out.println("保存数量：100000");
		
	}
	@Test
	public void jsonTest(){
		User user=null;
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 1; i <= 100000; i++) {
			user=new User(i, "1", "男", "1", i+"", i+"");
			list.add(user);
		}
		
		
		redisTemplate.opsForList().leftPushAll("jsonlist", list.toArray());
		
		
		System.out.println("序列化方式：json");
		System.out.println("保存数量：100000");
		
	}
	
	@Test
	public void hashTest(){
		User user=null;
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 1; i <= 100000; i++) {
			user=new User(i, "1", "男", "1", i+"", i+"");
			list.add(user);
		}
		
		for (User user2 : list) {
			redisTemplate.opsForHash().increment("myhash",user2.toString(), user2.getId());
		}
		
		
		System.out.println("序列化方式：hash");
		System.out.println("保存数量：100000");
		
	}

}
