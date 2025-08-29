package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache 算法实现
 * 使用 HashMap + 双向链表
 *
 * 时间复杂度: get() 和 put() 操作均为 O(1)
 * 空间复杂度: O(capacity)
 */
public class LRUCache {

    /**
     * 双向链表节点内部类
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> cache; // 用于 O(1) 查找
    private int size;
    private int capacity;
    private DLinkedNode head, tail; // 虚拟头尾节点，简化边界操作

    /**
     * 构造函数
     * @param capacity 缓存容量
     */
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // 初始化虚拟头尾节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取 key 对应的 value
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
     * @param key 键
     * @return 值或 -1
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // 未找到
        }
        // 如果找到，将其移动到链表头部（标记为最近使用）
        moveToHead(node);
        return node.value;
    }

    /**
     * 插入或更新 key-value
     * 如果关键字 key 已经存在，则变更其数据值；
     * 如果不存在，则向缓存中插入该组 key-value 。
     * 如果插入操作导致关键字数量超过 capacity ，则应该逐出最久未使用的关键字。
     * @param key 键
     * @param value 值
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            // 关键字不存在，创建新节点
            DLinkedNode newNode = new DLinkedNode(key, value);

            // 添加到哈希表
            cache.put(key, newNode);

            // 添加到链表头部
            addToHead(newNode);

            size++;

            // 检查是否超出容量
            if (size > capacity) {
                // 从链表尾部移除节点
                DLinkedNode tailNode = removeTail();
                // 从哈希表中移除
                cache.remove(tailNode.key);
                size--;
            }
        } else {
            // 关键字已存在，更新值并移动到头部
            node.value = value;
            moveToHead(node);
        }
    }

    // --- 双向链表操作的辅助方法 ---

    /**
     * 将节点添加到链表头部
     * @param node 要添加的节点
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 移除一个节点
     * @param node 要移除的节点
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将节点移动到链表头部
     * @param node 要移动的节点
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 移除链表尾部节点
     * @return 被移除的节点
     */
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    // --- 用于测试和演示的方法 ---

    /**
     * 获取当前缓存状态的字符串表示 (用于调试)
     * @return 缓存内容字符串
     */
    public String getState() {
        StringBuilder sb = new StringBuilder();
        DLinkedNode current = head.next;
        sb.append("Cache (head -> tail): ");
        while (current != tail) {
            sb.append("[").append(current.key).append(":").append(current.value).append("] ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    /**
     * 主函数，用于测试 LRU Cache
     */
    public static void main(String[] args) {
        System.out.println("LRU Cache 算法演示");
        System.out.println("==================");

        LRUCache lruCache = new LRUCache(2);

        System.out.println("初始化容量为 2 的 LRU Cache");
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        lruCache.put(1, 1); // 缓存是 {1=1}
        System.out.println("Put (1, 1)");
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println("Put (2, 2)");
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        System.out.println("Get 1 -> " + lruCache.get(1)); // 返回 1
        System.out.println("当前状态 (1被访问，移动到头部): " + lruCache.getState());
        System.out.println();

        lruCache.put(3, 3); // 该操作会使得关键字 2 作废 (容量已满，且2是最近最少使用的)
        System.out.println("Put (3, 3) -> 关键字 2 被逐出");
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        System.out.println("Get 2 -> " + lruCache.get(2)); // 返回 -1 (未找到)
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        lruCache.put(4, 4); // 该操作会使得关键字 1 作废
        System.out.println("Put (4, 4) -> 关键字 1 被逐出");
        System.out.println("当前状态: " + lruCache.getState());
        System.out.println();

        System.out.println("Get 1 -> " + lruCache.get(1)); // 返回 -1 (未找到)
        System.out.println("Get 3 -> " + lruCache.get(3)); // 返回 3
        System.out.println("Get 4 -> " + lruCache.get(4)); // 返回 4
        System.out.println("最终状态: " + lruCache.getState());
    }
}



