package com.algorithm.链表.单链表;

//todo cy final修饰变量共享问题：singleLinkList和singleLinkList2的getHead方法返回的节点
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        //普通添加
        SlinleLinkList singleLinkList = new SlinleLinkList();
        singleLinkList.add(heroNode3);
        singleLinkList.add(heroNode1);
        singleLinkList.add(heroNode4);
        singleLinkList.add(heroNode2);
        //查看链表遍历效果（每个节点打印时都会链上后面节点）
        System.out.println("-----------遍地普通添加-----------");
        singleLinkList.list();

        //顺序添加
        SlinleLinkList singleLinkList2 = new SlinleLinkList();
        singleLinkList2.addByOrder(heroNode1);
        singleLinkList2.addByOrder(heroNode3);
        singleLinkList2.addByOrder(heroNode2);
        singleLinkList2.addByOrder(heroNode4);
        System.out.println("-----------遍地顺序添加-----------");
        singleLinkList2.list();

        //修改节点
        HeroNode heroNode = new HeroNode(2, "修改卢俊义", "修改玉麒麟");
        singleLinkList2.update(heroNode);
        System.out.println("-----------修改节点后遍历-----------");
        singleLinkList2.list();

        //删除节点
        //1.找到需要删除节点的前一个节点
        //2。将前一个节点的next变成next的next（直接指向删除节点的下一个，删除节点无被指向，会被垃圾回收）

        singleLinkList2.delete(1);
        singleLinkList2.delete(2);
        System.out.println("删除2个后：");
        singleLinkList2.list();

        System.out.println("--------test1---------");
        //test1：求单链表有效节点的个数
        int lenght = getLenght(singleLinkList.getHead());
        int lenght2 = getLenght(singleLinkList2.getHead());
        System.out.println("长度：" + lenght);
        System.out.println("长度2：" + lenght2);
        System.out.println("--------test2---------");
        //test2：查找链表倒数第k个节点
        HeroNode descHeroNode = getDescHeroNode(singleLinkList.getHead(), 2);
        HeroNode descHeroNode2 = getDescHeroNode(singleLinkList2.getHead(), 2);
        singleLinkList.list();
        System.out.println("倒数第二个节点" + descHeroNode);
        System.out.println("倒数第二个节点2" + descHeroNode2);
        System.out.println("--------test3---------");


    }

    public static int getLenght(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        int lenght = 0;
        HeroNode cur = heroNode.next;
        while (cur != null) {
            lenght++;
            cur = cur.next;
        }
        return lenght;
    }

    //获取倒数第k个节点
    public static HeroNode getDescHeroNode(HeroNode heroNode, Integer k) {

        if (heroNode.next == null) {
            return null;
        }
        int lenght = getLenght(heroNode);
        if (k > lenght || k <= 0) {
            return null;
        }
        HeroNode temp = heroNode.next;
        for (int i = 0; i < lenght - k; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * @param heroNode
     */
    public static void reversetList(HeroNode heroNode) {
        //如果节点个数只有1个无需反转
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode cur = heroNode.next;//临时节点，用于遍历
        HeroNode next = null;//指向当前节点的下一个节点，防止临时节点修改导致后续节点丢失
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来节点，每遍历一个节点，放到re节点里面
        while (cur != null) { //如果节点的下一个节点是空的，则表示节点已经遍历完成
            next = cur.next;
            reverseHead.next=cur.next;//将cur下一个节点指向新的链表头节点后面
            cur=next;
        }
        //将heroNode.next指向reverseHead
        heroNode.next=reverseHead;


    }
}

//模拟每个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nockName) {
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
class SlinleLinkList {
    //先初始化一个头节点，不存放数据
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {
        //temp表示一个标的节点，初始是头节点
        HeroNode temp = head;
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

        HeroNode temp = head.next;
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

    //第二种添加方法，不论添加顺序，存储数据都是顺序的(升序)
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;//判断添加的节点在链表中是否已经存在
        while (true) {
            if (temp.next == null) {//说明是最后一个节点
                break;
            }
            if (temp.next.no > heroNode.no) {//找到了位置
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//已经存在
                break;
            }
            temp = temp.next;
        }

        //判断flag的值
        if (flag) {
            System.out.println("已存在");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    public void update(HeroNode newHeroNode) {
        HeroNode temp = head.next;
        boolean flag = false;//定义是否找到对应节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号 %d 的节点，不能修改");
        }
    }

    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //到链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到需要删除的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点不存在");
        }
    }
}