package com.xuyu.extmap;

import java.util.LinkedList;


@SuppressWarnings("all")

public class ExtLinkedListHashMap {
	
	public static void main(String[] args) {
		ExtLinkedListHashMap linkedListHashMap = new ExtLinkedListHashMap();
		linkedListHashMap.put("a", "aaaa");
		linkedListHashMap.put("a", "ccccc");// ����
		System.out.println(linkedListHashMap.get("a"));
	}
	// map���entry����
	LinkedList<Entry>[] tables = new LinkedList[988];

	// ��ѯֱ��ʹ��hashֱֵ�Ӷ�λ�������Ǹ�λ��
	public Object get(Object key) {
		int hashCode = key.hashCode();// 97
		// hashȡģ����ȡ����
		int hash = hashCode % tables.length;// 998 998���� 00997
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
		// hashȡģ����ȡ����
		int hash = hashCode % tables.length;// 998 998���� 00997
		// 1.��ȡ���±�Ԫ��,�Ƿ���LinkedList
		LinkedList<Entry> entryLinkedList = tables[hash];
		if (entryLinkedList == null) {
			// û��hash��ͻ
			entryLinkedList = new LinkedList<Entry>();
			//�����ӵ�ָ���±�λ��
			entryLinkedList.add(newEntry);
			// ��ӵ�tables�����Ӧλ��
			tables[hash] = entryLinkedList;
		} else {
			for (Entry entry : entryLinkedList) {
				if (entry.key.equals(key)) {
					// equals���,hashCode һ����ͬ ˵��:��ͬһ������
					entry.value = value;// ֱ�Ӹ���
				} else {
					// hashCode ��ͬ�������ֵ��һ����ͬ
					// ����hash��ͻ���⣬ֱ���ں�������������ڵ�
					entryLinkedList.add(newEntry);
				}
			}

		}
	}
}

// hash�洢����
class Entry<Key, Value> {
	// hashMap�洢��key
	Key key;
	// HashMap�洢��value
	Value value;

	// ����������
	public Entry(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}

}