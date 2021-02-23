package hash;

import java.util.Arrays;
import java.util.HashMap;

public class LruCache {

    public static void main(String[] args) {
//        int[][] operators = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
//        int[][] operators = {{1, 2, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        int[][] operators = {
                {1, 1, 1}, {1, 6, 2},
                {2, 1}, {1, 11, 3},
                {1, 16, 1}, {1, 2, 1},
                {1, 3, 1}, {2, 6}
        };
//        LruCache solution = new LruCache();
//        int[] ints = solution.LRU(operators, 5);
//        System.out.println(Arrays.toString(ints));

        for (int i = 1; i <= 3; i++) {
            for (int j = i - 1; j > 0; j--) {
                System.out.println("i="+i+" j="+j);
            }
        }

    }

    /**
     * 描述：最近最久未访问
     * 1.队尾总是最新数据(新插入数据或老数据获取)
     * 2.队头总是最老数据
     * 每次访问或插入的数据应该放到队尾，最近访问的元素在队尾，队头永远是最久未访问数据
     */

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param capacity  int整型 the k Lru 容量
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int capacity) {
        int getSize = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 2) {
                getSize++;
            }
        }

        int[] result = new int[getSize];
        int index = 0;

        System.out.println(Arrays.deepToString(operators));
        ILru lruCache = new LruCacheImplByHashMap(capacity); // 法1：双向链表+hashmap
//        ILru lruCache = new LruCacheImplByArray(capacity); // 法2：双向链表+单链表(存放相同hash的Node)+数组
        for (int i = 0; i < operators.length; i++) {
            int type = operators[i][0];
            int key = operators[i][1];
            if (type == 1) {
                lruCache.set(key, operators[i][2]);
            } else if (type == 2) {
                result[index] = lruCache.get(key);
                index++;
            }
            System.out.println(lruCache.toString());
        }
        return result;
    }

    private class Node {
        int key;
        int value;
        // pre\next用于存放lru的双向链表
        Node pre;
        Node next;
        Node hashNext; // 用于存放相同hash的单链表（此链表以此存放，不做lru处理），数组实现lru时才使用
    }

    private interface ILru {
        int get(int key);

        void set(int key, int value);

        int size();

    }

    /**
     * 描述：法1：
     * 双向链表+hashmap
     */
    private class LruCacheImplByHashMap implements ILru {
        int mCapacity; // 容量
        // head&tail 哨兵节点，不可修改
        Node mHead;
        Node mTail;
        HashMap<Integer, Node> mMap; // 用于存放key和Node

        public LruCacheImplByHashMap(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity < 0");
            }
            mCapacity = capacity;
            mMap = new HashMap<>(capacity);
            mHead = new Node();
            mTail = new Node();
            // 注意：不要忘记设置next和pre
            mHead.next = mTail;
            mTail.pre = mHead;
        }


        @Override
        public void set(int key, int value) {
            // 每次插入的数据总是在队尾
            if (get(key) != -1) {
                // 1.存在则将该数据放置队尾，同时更新value
                mMap.get(key).value = value;
            } else {
                // 2.不存在则将数据插入到队尾
                if (isFull()) {// 先判断有没有存满，再添加,相等时即存满，此时应该先释放，再添加
                    removeExpired(mHead.next);
                }
                Node node = new Node();
                node.key = key;
                node.value = value;
                addToTail(node); // 新数据在队尾
            }
        }

        @Override
        public int get(int key) {
            // 每次访问的数据应该放到队尾
            if (containKey(key)) {
                Node node = mMap.get(key);
                // 移除此节点
                removeNode(node);
                // 更新到队尾
                addToTail(node);
                return node.value;
            }
            return -1;
        }

        @Override
        public int size() {
            return mMap.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("size=").append(mMap.size()).append(":");
            Node node = mHead;
            while (node.next != mTail) {
                Node tmpNode = node.next;
                sb.append(" -> [key=").append(tmpNode.key).append(" value=").append(tmpNode.value).append("]");
                node = tmpNode;
            }

            return sb.toString();
        }

        private boolean containKey(int key) {
            return mMap.containsKey(key);
        }

        private boolean isFull() {
            return mMap.size() >= mCapacity; // 相等时即存满，此时应该先释放，再添加
        }

        /**
         * 描述：添加数据到队尾
         */
        private void addToTail(Node node) {
            node.pre = mTail.pre; // 该节点的上一项，指向队尾的上一项
            node.next = mTail; // 该节点的下一项，指向队尾
            mTail.pre.next = node; // 队尾的上一项的next,指向该节点
            mTail.pre = node;// 队尾的上一项，指向该节点
            mMap.put(node.key, node);
        }

        /**
         * 描述：移除当前节点
         */
        private void removeNode(Node node) {
            node.pre.next = node.next; // 该节点的上一项的next，指向该节点的下一项
            node.next.pre = node.pre; // 该节点的下一项的pre，指向该节点的上一项
            mMap.remove(node.key);
        }

        /**
         * 描述：移除过期数据
         */
        private void removeExpired(Node node) {
            if (containKey(node.key)) {
                removeNode(node);
            }
        }

    }

    /**
     * 描述：法2
     * 双向链表+单链表(存放相同hash的Node)+数组
     */
    private class LruCacheImplByArray implements ILru {
        int mCapacity; // 容量
        // head&tail 哨兵节点，不可修改
        Node mHead;
        Node mTail;
        Node[] mArr; // hash-key数组，根据hash作为索引，存放某个key的根Node，同一hash的Node通过Node.hashnext连接
        int mSize;

        public LruCacheImplByArray(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity < 0");
            }
            mSize = 0;
            mCapacity = capacity;
            mArr = new Node[capacity];
            mHead = new Node();
            mTail = new Node();
            // 注意：不要忘记设置next和pre
            mHead.next = mTail;
            mTail.pre = mHead;
        }

        @Override
        public void set(int key, int value) {
            // 每次插入的数据总是在队尾
            Node node = tryGet(key);
            if (node != null) {
                // 1.存在则将该数据放置队尾，同时更新value
                node.value = value;
            } else {
                // 2.不存在则将数据插入到队尾
                if (isFull()) {// 先判断有没有存满，再添加,相等时即存满，此时应该先释放，再添加
                    removeNode(mHead.next, getNode(mHead.next.key)[1]);
                }
                node = new Node();
                node.key = key;
                node.value = value;
                addToTail(node);// 新数据在队尾
            }
        }

        @Override
        public int get(int key) {
            // 每次访问的数据应该放到队尾
            Node node = tryGet(key);
            if (node != null) {
                return node.value;
            }
            return -1;
        }


        /**
         * 描述：尝试获取数据
         * 获取成功：将Node更新到队尾并返回
         * 获取失败，返回null
         */
        private Node tryGet(int key) {
            // 每次访问的数据应该放到队尾
            Node[] nodes = getNode(key);
            Node node = nodes[0];
            Node hashPreNode = nodes[1];
            if (node != null) {
                removeNode(node, hashPreNode); // 移除此节点
                addToTail(node); // 更新到队尾
            }
            return node;
        }

        /**
         * 描述：获取结点
         *
         * @return Node[]{此节点,此key的在hash链表中的前一项}
         */
        private Node[] getNode(int key) {
            // 每次访问的数据应该放到队尾
            Node hashRootNode = getHashRoot(getHashIndex(key)); // 从hash数组中读取根节点
            Node hashPreNode = null;
            Node node = hashRootNode;
            while (node != null && node.key != key) {
                hashPreNode = node;// 保存目标结点上一个hash结点，用于从hash链表中移除node。因为hash链表是电联表
                node = node.next;
            }
            return new Node[]{node, hashPreNode};
        }

        @Override
        public int size() {
            return mSize;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("size=").append(size()).append(":");
            Node node = mHead;
            while (node.next != mTail) {
                Node tmpNode = node.next;
                sb.append(" -> [key=").append(tmpNode.key).append(" value=").append(tmpNode.value).append("]");
                node = tmpNode;
            }

            return sb.toString();
        }


        private boolean isFull() {
            return mSize >= mCapacity; // 相等时即存满，此时应该先释放，再添加
        }

        /**
         * 描述：添加数据到队尾
         */
        private void addToTail(Node node) {
            node.pre = mTail.pre; // 该节点的上一项，指向队尾的上一项
            node.next = mTail; // 该节点的下一项，指向队尾
            mTail.pre.next = node; // 队尾的上一项的next,指向该节点
            mTail.pre = node;// 队尾的上一项，指向该节点
            addToHash(node); // 向hash数组添加元素
            mSize++;
        }

        /**
         * 描述：移除当前节点
         */
        private void removeNode(Node node, Node hashPreNode) {
            // lru 链表删除
            node.pre.next = node.next; // 该节点的上一项的next，指向该节点的下一项
            node.next.pre = node.pre; // 该节点的下一项的pre，指向该节点的上一项
            removeHashNode(node, hashPreNode); // 移除hash数组的一个node
            mSize--;
        }

        /**
         * 描述：计算hash值
         */
        private int getHashIndex(int key) {
            return Math.abs(key % mArr.length);
        }

        /**
         * 描述：获取某个key的hash链表根结点
         */
        private Node getHashRoot(int hashIndex) {
            return mArr[hashIndex];
        }


        /**
         * 描述：向hash数组添加元素
         */
        private void addToHash(Node node) {
            int hashIndex = getHashIndex(node.key);
            Node hashRootNode = getHashRoot(hashIndex);
            if (hashRootNode == null) {
                // 1. 根节点不存在，直接存放
                mArr[hashIndex] = node;
            } else {
                // 2. 根节点存在
                Node hashNext = hashRootNode.hashNext;
                if (hashNext == null) {// 3. 判断是不是只有根节点,只有根节点则直接插入
                    hashRootNode.hashNext = node;
                } else {
                    // 4. 除根节点之外还有数据，则将node插入到根节点后面
                    hashRootNode.hashNext = node;
                    node.hashNext = hashNext;
                }
            }
        }

        /**
         * 描述：移除hash数组的一个node
         * 此node不一定是根节点
         */
        private void removeHashNode(Node node, Node hashPreNode) {
            int hashIndex = getHashIndex(node.key);
            if (hashPreNode == null) {
                // 1.如果hash链表中此节点node属于根节点
                // 则置为null
                mArr[hashIndex] = null;
            } else {
                // 2.除根节点之外还有数据，则将node替换为node.hashNext
                hashPreNode.hashNext = node.hashNext;
            }
        }
    }

}
