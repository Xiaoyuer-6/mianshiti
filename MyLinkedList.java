class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
public  class MyLinkedList {

    public Node head;//普通引用，目的是让head一直指向当前列表的头

    public void createLinked() {
        this.head = new Node(12);
        Node node2 = new Node(22);
        Node node3 = new Node(32);
        Node node4 = new Node(42);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
    }

    public void display() {//打印一个链表
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public Node findLastNode() {//找到最后一个结点
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public Node findLastTwoNode() {//找到倒数第二个结点
        if (this.head == null)
            System.out.println("链表为空");
        if (this.head.next == null)
            System.out.println("链表中有一个结点");
        Node cur = this.head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    public Node findN(int n) {
        if (this.head == null) {
            System.out.println("链表为空");
            return null;
        }
        if (n <= 0) {
            System.out.println("n的值不合理");
            return null;
        }
        if (n > size()) {
            System.out.println("n太大了");
            return null;
        }
        Node cur = this.head;
        int count = 1;
        while (count != n) {
            cur = cur.next;
            count++;
        }
        return cur;
    }

    //得到单链表的长度
    public int size() {
        Node cur = this.head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }

    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //头插法
    public void addFirst(int data) {
        Node node = new Node(data);//Node一个新的节点；
        if (this.head == null) {
            this.head = node;//链表为空，链表的头就等于要插入的节点；
        } else {
            node.next = this.head;//①（先绑定后面的）新节点的next=原来链表的head；
            this.head = node;//②链表的头等于要插入的节点；
        }
    }

    //尾插法
    public void addLast(int data) {
        Node node = new Node(data);//Node一个新的节点；
        if (this.head == null) {
            this.head = node;//节点为空，链表的头就是要插入的节点；
        } else {
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;//遍历找到最后一个节点；
            }
            cur.next = node;//最后一个节点的next等于要插入的节点；
        }
    }

    public Node moveYiIndex(int index) {//找到要插入节点的前一个
        Node cur = this.head;
        int count = 0;
        while (count != index - 1) {
            cur = cur.next;
            count++;
        }
        return cur;
    }

    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index, int data) {
        if (index < 0 || index > size()) {//不合法现象；
            System.out.println("index不合理");
            return;
        }
        if (index == 0) {//头插法
            addFirst(data);
            return;
        }
        if (index == size()) { //尾插法
            addLast(data);
            return;
        }
        Node cur = moveYiIndex(index);//调用moveYiIndex函数，找到index的前一个节点cur；
        Node node = new Node(data);//定义一个新的节点；
        node.next = cur.next;//插入节点的next等于原始cur的next；
        cur.next = node;//cur的next变成了node；
    }

    public Node searchCur(int key) {//找到要删除节点的前驱
        Node cur = this.head;
        while (cur.next != null) {
            if (cur.next.val == key) {//找出key的节点的前一个
                return cur;
            }
            cur = cur.next;

        }
        return null;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        if (this.head == null) {
            return;
        }
        if (this.head.val == key) {
            this.head = this.head.next;
            return;
        }
        Node cur = searchCur(key);//调用searchCur，找出删除节点的前驱cur
        if (cur == null) {
            System.out.println("未找到要删除的节点");
        } else {
            Node del = cur.next;//定义要删除的节点为key;
            cur.next = del.next;//让cur的next等于删除节点的next;
        }
    }

    //删除所有值为key的节点
    public void removeAllKey(int key) {
        if (this.head == null) {
            System.out.println("链表为空");
            return;
        }
        Node prev = this.head;//定义一个prev指向链表的头；
        Node cur = prev.next;//定义一个cur指向prev的next；
        while (cur != null) {//遍历完条件；
            if (cur.val == key) {//找到要删除的节点；
                prev.next = cur.next;//把该节点删掉；
            } else {
                prev = cur;//未找到
            }
            cur = cur.next;//cur继续往后走；
        }
        if (this.head.val == key) {//头节点就是要删除的节点
            this.head = this.head.next;//删除头，链表的头直接是head的next；
        }
    }

    public Node reverseList() {
        Node cur = this.head;//定义cur指向需要需要反转链表
        Node prev = null;//定义一个prev指向需要反转的前一个链表
        Node newhead = null;//定义一个newHead
        while (cur != null) {
            Node curNext = cur.next;//定义一个newHead指向需要反转的链表的下一个链表
            if (curNext == null) {
                newhead = cur;//cur走到最后一个链表时，重新定义链表的头
            }
            cur.next = prev;//反转链表
            prev = cur;//在cur往后移动之前确定cur
            cur = curNext;//cur往后移动
        }
        this.head = newhead;
        return newhead;//循环结束，返回链表

    }

    public void clear() {//清除单链表；

    }

    public Node middleNode() {//方法：快慢指针
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {//两种情况同时满足
            fast = fast.next.next;//fast每次走两步
            slow = slow.next;//slow每次走一步
        }
        return slow;//返回slow
    }

    public Node FindKthToTail(int k) {
        if (head == null || k <= 0) {//链表为空或k小于0；
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (k - 1 != 0) {//让fast先走k-1步；
            if (fast.next != null) {//判断的是k值大于链表长度时
                fast = fast.next;
                k--;
            } else {
                return null;
            }
        }
        while (fast.next != null) {//未走到尾节点时，fsat和slow每次各走一步
            fast = fast.next;
            slow = slow.next;
        }
        return slow;//返回slow，为找到的倒数第n个节点；
    }

    public Node mergeTwoLists(Node headA, Node headB) {//两个链表头分别headA，headB;

        Node newHead = new Node(-1); //定义一个傀儡节点
        Node tmp = newHead;//定义一个移动的节点tmp保存往傀儡节点后面拼接的节点此时位置在傀儡节点
        while (headA != null && headB != null) {//两个都不为空
            if (headA.val > headB.val) {//判断链表头val的大小
                tmp.next = headB;//保存headB
                headB = headB.next;//headB后移一位
                tmp = tmp.next;//tmp也后移一位
            } else {//headB.val大
                tmp.next = headA;//保存headA
                headA = headA.next;//headA后移一位
                tmp = tmp.next;//tmp后移一位
            }
        }
        if (headB != null) {//headB为空
            tmp.next = headB;//直接是headB链表
        }
        if (headA != null) {//headA为空
            tmp.next = headA;//直接是headA链表
        }
        return newHead.next;//返回链表的头，傀儡节点的下一个；
    }

    public Node partition(int x) {
        Node be = null;
        Node ae = null;
        Node as = null;
        Node bs = null;
        Node cur = this.head;
        while (cur != null) {
            if (cur.val < x) {
                if (bs == null) {//小于x的部分一个节点还没有的情况下
                    bs = cur;
                    be = cur;
                } else {//有了节点
                    be.next = cur;
                    be = be.next;
                }
            } else {
                if (as == null) {//大于x的部分一个节点还没有的情况下
                    as = cur;
                    ae = cur;
                } else {//小于x的部分一个节点还没有的情况下
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        if (bs == null) {//如果bs为空，返回的是as
            return as;
        }
        be.next = as;
        if (as != null) {//as不为空时，我们要把as的最后一个节点置为空
            ae.next = null;
        }
        return bs;
        // write code here
    }
    /*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
        public Node deleteDuplication() {
            Node newHead = new Node(-1);
            Node tmp = newHead;
            Node cur = this.head;
            while (cur != null) {
                if (cur.next != null && cur.val == cur.next.val) {
                    while (cur.next != null && cur.val == cur.next.val) {
                        cur = cur.next;
                    }
                    cur = cur.next;
                } else {
                    tmp.next = cur;
                    tmp = tmp.next;
                    cur = cur.next;
                }
            }
            tmp.next = null;
            return newHead.next;
        }
    public boolean chkPalindrome() {
            if (this.head == null){
                return false;
            }
        // write code here
        //1.找到链表的中间节点
        Node fast = this.head;
        Node slow = this.head;
        while (fast !=null && fast.next != null){
            fast =fast.next.next;
            slow = slow.next;
        }
        //2.翻转中间链表之后的节点
        Node cur =slow.next;//此时slow的位置就是中间节点
        while (cur != null){
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //3.对比是否是首尾相同的回文
        while (slow != head){//当他两没遇到时
        if (slow.val != head.val){
            return false;
        }
        if (head.next == slow){//偶数情况下
            return  true;
        }
            slow = slow.next;
            head = head.next;
        }
        return true;

    }
    public static Node getIntersectionNode(Node headA, Node headB) {
        if(headA ==null || headB == null){//是否为空，空的话无公共节点
            return null;
        }
        int lenA =0;
        int lenB =0;
        Node p1 = headA;
        Node p2 = headB;
        while( p1!= null){//链表A的长度
            p1 = p1.next;
            lenA++;
        }
        while( p2!= null){//链表B的长度
            p2 = p2.next;
            lenB++;
        }
        p1 =headA;
        p2 =headB;
        int len = lenA - lenB;//求出长度差
        if(len <= 0){
            p1 = headB;
            p2 = headA;
            len =lenB-lenA;
        }
        while(len != 0){
            p1 = p1.next;//较长的链表往后走他们相差的步数
            len--;
        }
        while(p1!=p2){//一起走
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;//相遇时就是起始节点的位置
    }
    public static void main5(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(7);
        myLinkedList.addLast(9);
        myLinkedList.addLast(15);
        myLinkedList.addLast(9);
        myLinkedList.addLast(7);
        myLinkedList.display();
        System.out.println(myLinkedList.chkPalindrome());

    }
    public boolean hasCycle() {
        if(head == null){//判断链表是否为空
            return false;
        }
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){

            fast= fast.next.next;//fsat每次走两步的原因是走两步在最快的情况下可以遇到
            slow = slow.next;

            if(fast == slow){//相遇时说明有环
                return true;
            }
        }
        return false;//循环结束，fast和slow还没遇到说明链表无环
    }
    public Node detectCycle() {
        if(this.head == null){//判断链表是否为空
            return null;
        }
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){

            fast= fast.next.next;//fsat每次走两步的原因是走两步在最快的情况下可以遇到
            slow = slow.next;

            if(fast == slow){//相遇时说明有环
                break;
            }
        }
        if(fast == null || fast.next == null) {
            return null;//循环结束，fast和slow还没遇到说明链表无环
        }
        //代码走到这说明有环，并且slow和fsat相遇了
        slow = this.head;
        while(slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
         return fast;
    }
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(7);
        myLinkedList.addLast(9);
        myLinkedList.addLast(15);
        myLinkedList.addLast(38);
        myLinkedList.addLast(48);
        myLinkedList.addLast(15);
        myLinkedList.display();
        System.out.println(myLinkedList.detectCycle());
    }
        /*
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(7);
        myLinkedList.addLast(9);
        myLinkedList.addLast(9);
        myLinkedList.addLast(18);
        myLinkedList.addLast(28);
        myLinkedList.addLast(28);
        myLinkedList.addLast(28);
        myLinkedList.addLast(58);
        myLinkedList.display();
        System.out.println("================");
        myLinkedList.deleteDuplication();
        myLinkedList.display();
    }

         */

}

    /*测试
    public static void main(String[] args) {
        Node node = new Node(67);
        System.out.println(node.val);
        System.out.println(node.next);

    }
     */