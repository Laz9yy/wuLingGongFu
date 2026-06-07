package wuLingGongFu;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    // 判断两个对象是否属于【完全同一个类】
    public static boolean isSameClass(Object obj1, Object obj2) {
    if (obj1 == null || obj2 == null) {
        return false;
    }
    return obj1.getClass() == obj2.getClass();
}
    //主方法
    public static void main(String[] args){
        int max_DiZi_num=1000;//最大弟子的数量
        int max_Source_num=5;//弟子所具备的资源的种类
        Scanner sc=new Scanner(System.in);
        int max_Time=sc.nextInt();//读取最大实验组数
        for(int m=0;m<max_Time;m++){
            int max_Step=sc.nextInt();//读取最大步数
            diZi[] everyDiZis=new diZi[max_DiZi_num];//用数组决定有多少弟子对象 先用最大的 后面看循环结束了 才改
            int max_Num=0;//准备一个记录数组最大长度 即真实弟子人数
            int[] source=new int[5];//声明一个数组待会用来存储弟子的数据
            for(int i=0;;i++){
                char ch=sc.next().charAt(0);//读入输入的字符
                if(ch=='0'){
                    max_Num=i;//记录真实的弟子人数
                    break;
                };//读到字符串为0 直接退出 实例化弟子环节 并记录真实的弟子人数
                for(int j=0;j<max_Source_num;j++){
                    source[j]=sc.nextInt();
                }
                if(ch=='S'){
                    everyDiZis[i]=new shaoLin(source[0],source[1],source[2],source[3],source[4]);
                }
                if(ch=='W'){
                    everyDiZis[i]=new wuDang(source[0],source[1],source[2],source[3],source[4]);
                }
                if(ch=='E'){
                    everyDiZis[i]=new eMei(source[0],source[1],source[2],source[3],source[4]);
                }
            }//实例化各个弟子
            //准备精准数组 用来存储实际的弟子人数  防止后续计算有误时 出现空指针
            diZi[] finalDiZis = new diZi[max_Num];
            System.arraycopy(everyDiZis, 0, finalDiZis, 0, max_Num);
            everyDiZis = finalDiZis;
            //下面用数组用来存储战斗前每个弟子的初始血量
            int[] HP=new int[max_Num];
            //建立一个三维数组 最大为2 代表两张表 主要目的是防止 出现3个弟子都在同一个地方时 数据恢复 找不到 前两个弟子的下标 建立一个二维数组作为地图 其中二位数组的值代表diZi对象的数组的脚标 通过脚标来判断是否会相遇 战斗 遇到了就直接斗 如果又出现了两个以上的 那就恢复之前的数值
            int[][][] map=new int[2][13][13];
            //注意如果上面数组开12的话 实际上是11*11的大小 对于坐标的话 所以最大要开到13
            //下面开始战斗
            for(int i=0;i<max_Step;i++){
                //先清空地图  注意地图的值不能为0 不然脚标为0的弟子就无法参与到后续的战斗中
                // 第一层循环遍历两张地图
                int[][] times=new int[13][13]; //这个是拿来记录地图上 每个点出现的人数的 是为了防止后面回血量那里  如果有多个人出现的话 对前两人的多次恢复 跟着循环一起重置
                for(int a = 0; a < 2; a++){ 
                    // 第二层循环遍历 y 坐标 0~12
                    for(int b = 0; b < 13; b++){
                        // 核心：把 map[a][b] 这一整行，全部填充成 -1
                        Arrays.fill(map[a][b], -1);  
                        }
                }
                for(int j=0;j<max_Num;j++){
                    HP[j]=everyDiZis[j].HP;
                }//先对血量每次保存
                for(int j=0;j<max_Num;j++){
                    //首先战斗的前提是血量大于0
                    if(everyDiZis[j].HP>0){
                        int x=everyDiZis[j].x;
                        int y=everyDiZis[j].y;
                        if(map[0][y][x]==-1){
                            map[0][y][x]=j;//如果数组初始的值是0的话就代表没有弟子走到过这片区域 并存入进入这个区域的弟子的下标 为后续做准备
                            times[y][x]++;
                        }
                        //第一个弟子进入 之后不管后面的
                        //下面处理不为0 初次遇到两个弟子在相同的位置   
                        else if(map[1][y][x]==-1){
                            map[1][y][x]=j;//存入第二个走进这个地方的下标
                            times[y][x]++;
                            int D1=map[0][y][x];
                            int D2=map[1][y][x];
                            if(!(isSameClass(everyDiZis[D1], everyDiZis[D2]))){//判断两个是不是同一个类的 是的话就不管 不是的话 就在底下进行处理
                                int temp=everyDiZis[D1].HP-everyDiZis[D2].fight(); //这里用temp是为了让两人战斗时的血量都为战斗开始时的血量 不然后续会有误差
                                everyDiZis[D2].HP-=everyDiZis[D1].fight();
                                everyDiZis[D1].HP=temp;                        
                            } //对血量进行处理  
                            // 这里只用两次判断是因为 一个格子大于两人以上就不会再战斗了 所以这里用两次判断就可以  但是要注意到后续的恢复血量那里的判断 
                        }//第二个弟子进入
                        else if(times[y][x]==2) {
                            times[y][x]++;
                            //先去判断先前第一次和第二次进入的是不是同一个门派的  是同一个门派的话就不用加血了
                            int D1=map[0][y][x];
                            int D2=map[1][y][x];
                            if(!(isSameClass(everyDiZis[D1], everyDiZis[D2]))){
                                everyDiZis[D1].HP=HP[D1];
                                everyDiZis[D2].HP=HP[D2];
                            }
                        }//出现三次之后恢复血量
                    }
                }//弟子在表里的位置 以及处理方式  这里是初始的填表位置也可能会战斗
                for(int j=0;j<max_Num;j++){
                    if(everyDiZis[j].HP>0){
                        everyDiZis[j].position=everyDiZis[j].walk();
                    }
                }//结束后每个弟子的一次移动
            }
            int[][] result=new int[3][2];//声明结果数组 准备出结果
            for(int i=0;i<max_Num;i++){
                if(everyDiZis[i] instanceof shaoLin&&everyDiZis[i].HP>0){
                    result[0][0]++;
                    result[0][1]+=everyDiZis[i].HP;
                }
                if(everyDiZis[i] instanceof wuDang&&everyDiZis[i].HP>0){
                    result[1][0]++;
                    result[1][1]+=everyDiZis[i].HP;
                }
                if(everyDiZis[i] instanceof eMei&&everyDiZis[i].HP>0){
                    result[2][0]++;
                    result[2][1]+=everyDiZis[i].HP;
                }
            }
            for(int i=0;i<3;i++){
                System.out.printf("%d %d\n",result[i][0],result[i][1]);
            }
            System.out.printf("***\n");
        }
        sc.close();
    }
}
