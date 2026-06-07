package wuLingGongFu;

public abstract class diZi implements MenPai {
    public int neiLi=0;
    public int wuYi=0;
    public int HP=0;
    public int violence=0;
    protected int x=0,y=0;
    protected int[] position=new int[2];
    protected int bushu=1;//作为后续的方向向量
    public diZi(){
        neiLi=0;
        wuYi=0;
        HP=0;
        x=0;
        y=0;
    }
    //声明每个弟子的各项属性
}
