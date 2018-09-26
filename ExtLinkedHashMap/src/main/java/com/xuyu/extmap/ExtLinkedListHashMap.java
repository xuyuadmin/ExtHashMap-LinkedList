package com.xuyu.extmap;

import java.util.LinkedList;


@SuppressWarnings("all")

public class ExtLinkedListHashMap {
	
	public static void main(String[] args) {
		ExtLinkedListHashMap linkedListHashMap = new ExtLinkedListHashMap();
		linkedListHashMap.put("a", "aaaa");
		linkedListHashMap.put("a", "ccccc");// 覆盖
		System.out.println(linkedListHashMap.get("a"));
	}
	// map存放entry对象
	LinkedList<Entry>[] tables = new LinkedList[988];

	// 查询直接使用hash值直接定位在数组那个位置
	public Object get(Object key) {
		int hashCode = key.hashCode();// 97
		// hash取模，获取余数
		int hash = hashCode % tables.length;// 998 998以内 00997
		LinkedList<Entry> linkedList = tables[hash];
		for (Entry entry : linkedList) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
		}
		return tables[hash];
	}

	public void put(Object key, Object value) {
		Entry newEntry = new Entry(key, value);
		int hashCode = key.hashCode();// 97
		// hash取模，获取余数
		int hash = hashCode % tables.length;// 998 998以内 00997
		// 1.获取该下标元素,是否有LinkedList
		LinkedList<Entry> entryLinkedList = tables[hash];
		if (entryLinkedList == null) {
			// 没有hash冲突
			entryLinkedList = new LinkedList<Entry>();
			//把他加到指定下标位置
			entryLinkedList.add(newEntry);
			// 添加到tables数组对应位置
			tables[hash] = entryLinkedList;
		} else {
			for (Entry entry : entryLinkedList) {
				if (entry.key.equals(key)) {
					// equals相等,hashCode 一定相同 说明:是同一个对象
					entry.value = value;// 直接覆盖
				} else {
					// hashCode 相同，对象的值不一定相同
					// 发生hash冲突问题，直接在后面继续添加链表节点
					entryLinkedList.add(newEntry);
				}
			}

		}
	}
}

// hash存储对象
class Entry<Key, Value> {
	// hashMap存储的key
	Key key;
	// HashMap存储的value
	Value value;

	// 参数构造器
	public Entry(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}

}