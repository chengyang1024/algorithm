package com.algorithm.链表.单链表;

public class SingleLinkedListDemo2 {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");

        //普通添加
        SlinleLinkList2 singleLinkList = new SlinleLinkList2();
        singleLinkList.add(heroNode3);
        singleLinkList.add(heroNode1);
        singleLinkList.add(heroNode4);
        singleLinkList.add(heroNode2);
        //查看链表遍历效果（每个节点打印时都会链上后面节点）
        System.out.println("反转前：");
        singleLinkList.list();
        reversetList(singleLinkList.getHead());
        System.out.println("反转后：");
        singleLinkList.list();

    }



    /**
     * @param heroNode
     */
    public static void reversetList(HeroNode2 heroNode) {
        //如果节点个数只有1个无需反转
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode2 cur = heroNode.next;//临时节点，用于遍历
        HeroNode2 next = null;//指向当前节点的下一个节点，防止临时节点修改导致后续节点丢失
        HeroNode2 reverseHead = new HeroNode2(0, "", "");
        //遍历原来链表，每遍历一个节点，放到re节点里面
        while (cur != null) { //如果节点的下一个节点是空的，则表示节点已经遍历完成
            next = cur.next;
            cur.next=reverseHead.next;//将cur下一个节点指向新的链表头节点后面
            reverseHead.next=cur;
            cur=next;

        }
        //将heroNode.next指向reverseHead
        heroNode.next=reverseHead.next;
//        reverseHead=heroNode.next;

    }
}

//模拟每个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点

    //构造器
    public HeroNode2(int no, String name, String nockName) {
        this.no = no;
        this.name = name;
        this.nickName = nockName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +

                '}';
    }
}

//定义一个SingleLinkList管理我们的英雄
class SlinleLinkList2 {
    //先初始化一个头节点，不存放数据
    private final HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void add(HeroNode2 heroNode) {
        //temp表示一个标的节点，初始是头节点
        HeroNode2 temp = head;
        while (true) {
            //找到链表的最后，然后把最后一个元素的next指向新添加的元素(最后一个节点的next为null)
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;//不需要add，直接链上就行
    }

    //遍历链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //temp位移
            temp = temp.next;
        }
    }






}